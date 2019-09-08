/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;


import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1742177
 */
public class Masque extends Ennemi {

    private double t = 0.3;
    private double cX = x-90;  // x-coordinate of center of rotation
    private double cY = y; // y- coordinate of center of rotation

    /**
     *Constructeur du masque
     * @param x position en x de l'ennemi
     * @param y position en y de l'ennemi
     * @param spriteSheet source de la sous image
     */
    public Masque(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 2, 4);
        listeAnimation.add(spriteSheet.getSubImage(4, 2));
        listeAnimation.add(spriteSheet.getSubImage(5, 2));
        listeAnimation.add(spriteSheet.getSubImage(6, 2));
    }

    /**
     *Gestion du déplacement 
     */
    @Override
    public void bouger() {
        animation();

        double r = 90;  

        double omega = 6;  
        t = t + 0.01;  
        

            x = (float) ((float) cX + r * Math.cos(t * omega));
            y = (float) (cY + r * Math.sin(t * omega));
        

        cX = cX-3;

        if (x < -32) {
            this.detruire = true;
        }

    }

    /**
     *Gestion de l'animation
     */
    protected void animation() {
        if (animation == 0) {
            this.image = listeAnimation.get(0);
        } else if (animation == 50) {
            this.image = listeAnimation.get(1);
        } else if (animation == 100) {
            this.image = listeAnimation.get(2);
        } else if (animation == 150) {
            animation = -1;
        }
        animation++;
    }
}
