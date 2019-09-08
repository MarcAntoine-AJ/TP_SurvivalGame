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
public class PlanteMechante extends Ennemi {

    /**
     * Constructeur Plante Méchante
     *
     * @param x position en X
     * @param y position en Y
     * @param spriteSheet source de la sous image
     */
    public PlanteMechante(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 3, 1);
        deltaX = 4;
        listeAnimation.add(spriteSheet.getSubImage(1, 3));
        listeAnimation.add(spriteSheet.getSubImage(2, 3));
        listeAnimation.add(spriteSheet.getSubImage(0, 3));

    }

    /**
     * Gestion de l'animation
     */
    @Override
    protected void animation() {
        if (animation == 0) {
            this.image = listeAnimation.get(0);
        } else if (animation == 35) {
            this.image = listeAnimation.get(1);
        } else if (animation == 70) {
            this.image = listeAnimation.get(2);
        } else if (animation == 90) {
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
        x = x - deltaX;
        if (x < -32) {
            this.detruire = true;
        }
    }

    /**
     * Retourne l'animation
     *
     * @return variable aniamtion
     */
    public int getAnimation() {
        return animation;
    }
}
