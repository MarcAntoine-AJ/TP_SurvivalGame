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
 * @author 1742177
 */
public class Plancher extends Entite implements Bougeable {

    private int deltaX = 4;

    /**
     *Constructeur du plancher
     * @param x position en X
     * @param y position en Y
     * @param spriteSheet source de la sous-image 
     */
    public Plancher(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 0, 11);
    }

    /**
     *Gestion déplacement
     */
    @Override
    public void bouger() {
        if (this.x - deltaX > -32) {
            this.x = x - deltaX;
        } else {
            this.x = LONGUEUR;
        }

    }
}
