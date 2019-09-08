/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Marc-Antoine Abou Jaoude Massinissa Merbouche
 */
public class BombeMega extends Bonus {

    private int animation = 0;
    private ArrayList<Image> listeAnimation = new ArrayList();

    /**
     *Constructeur du bonus
     * @param x position en x
     * @param y position en y
     * @param spriteSheet la source de la sous-image
     */
    public BombeMega(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 3, 3);
        listeAnimation.add(spriteSheet.getSubImage(3, 3));
        listeAnimation.add(spriteSheet.getSubImage(4, 3));

    }

    /**
     *Gestion du déplacement
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
 * Gestion de l'animation
 */
    private void animation() {
        if (animation == 0) {
            this.image = listeAnimation.get(0);

        } else if (animation == 5) {
            this.image = listeAnimation.get(1);
        } else if (animation == 10) {
            animation = -1; // Pour remettre à 0 avec le ++
        }
        animation++;

    }

}
