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
public abstract class Bonus extends Entite implements Bougeable, Collisionnable {

 
    protected int deltaX = 4;

    /**
     *Constructeur des Bonus
     * @param x position en x
     * @param y position y 
     * @param spriteSheet source de la sous image
     * @param ligne ligne dans la spriteSheet
     * @param colonne colonne dans la spriteSheet
     */
    public Bonus(float x, float y, SpriteSheet spriteSheet, int ligne, int colonne) {
        super(x, y, spriteSheet, ligne, colonne);
    }
    
    /**
     *Gestion des collisions
     */
    @Override
    public void collision() {
        this.detruire = true;
    }

}
