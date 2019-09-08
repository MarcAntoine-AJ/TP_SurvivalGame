/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;

import static ca.qc.bdeb.prog3.tp2a18.Main.LONGUEUR;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Massi
 */
public class EnnemiVolantSin extends Ennemi implements Bougeable {

    /**
     * Constructeur de l'ennemi
     *
     * @param x position en x
     * @param y position en y
     * @param spriteSheet source de la sous-image
     */
    public EnnemiVolantSin(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 2, 2);
        deltaX = 4;
        listeAnimation.add(spriteSheet.getSubImage(2, 2));
        listeAnimation.add(spriteSheet.getSubImage(3, 2));
    }

    /**
     * Gestion du déplacement
     */
    @Override
    public void bouger() {
        animation();
        x = x - deltaX;
        if (x <= LONGUEUR) {
            y = y + (float) ( 15*Math.sin(Math.toDegrees(x/2000)));
           
        }

        if (x < -32) {
            this.detruire = true;
        }
    }

    /**
     * Gestion de l'animation
     */
    @Override
    protected void animation() {
        if (animation == 0) {
            this.image = listeAnimation.get(0);
        } else if (animation == 10) {
            this.image = listeAnimation.get(1);
        } else if (animation == 20) {
            animation = -1; // Pour remettre à 0 avec le ++
        }
        animation++;
    }

}
