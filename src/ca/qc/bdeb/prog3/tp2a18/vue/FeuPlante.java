/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;

import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Massi
 */
public class FeuPlante extends Ennemi {

    /**
     *Constructeur
     * @param x position en x
     * @param y position en y
     * @param spriteSheet source de la sous-image
     */
    public FeuPlante(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 5, 3);
        deltaY = 5;
        deltaX = 4;
        listeAnimation.add(spriteSheet.getSubImage(3, 5));
        listeAnimation.add(spriteSheet.getSubImage(4, 5));
        listeAnimation.add(spriteSheet.getSubImage(5, 5));

    }

    /**
     *Gestion de l'animation
     */
    @Override
    protected void animation() {
        if (animation == 0) {
            this.image = listeAnimation.get(0);
        } else if (animation == 10) {
            this.image = listeAnimation.get(1);
        } else if (animation == 20) {
            this.image = listeAnimation.get(2);
        } else if (animation == 30) {
            animation = -1; // Pour remettre à 0 avec le ++
        }
        animation++;
    }

    /**
     * Gestion du déplacement
     */
    @Override
    public void bouger() {
        animation();
        y = y - deltaY;
        x = x-deltaX;
        if(y<-32){
            this.detruire = true;
        }
    }

}
