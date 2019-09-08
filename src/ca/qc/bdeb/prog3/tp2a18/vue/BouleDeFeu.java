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
public class BouleDeFeu extends Ennemi{

    /**
     *Constructeur de la BouleDeFeu
     * @param x position en x
     * @param y position en y
     * @param spriteSheet source de la sous-image
     */
    public BouleDeFeu(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 3, 5);
        deltaX = 5;
        listeAnimation.add(spriteSheet.getSubImage(5, 3));
        listeAnimation.add(spriteSheet.getSubImage(6, 3));
        
    }
    
    /**
     *Animation de Boule de Feu
     */
    @Override
    protected void animation() {
        if (animation == 0) {
            this.image = listeAnimation.get(0);
        } else if (animation == 7) {
            this.image = listeAnimation.get(1);
        } else if (animation == 14) {
            animation = -1; // Pour remettre à 0 avec le ++
        }
        animation++;
    }

    /**
     *Déplacement de la boule de feu
     */
    @Override
    public void bouger() {
        animation();
        x = x-deltaX;
        if (x<-32){
            detruire = true;
        }
    }
    
}
