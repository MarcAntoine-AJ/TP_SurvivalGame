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
public class Ciel extends Entite{
    
    /**
     *Constructeur du ciel
     * @param x position en x
     * @param y position en y
     * @param spriteSheet source de la sous-image
     */
    public Ciel(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 4, 23);
    }
    
}
