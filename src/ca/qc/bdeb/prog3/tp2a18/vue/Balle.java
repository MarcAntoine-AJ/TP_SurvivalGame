/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;

import static ca.qc.bdeb.prog3.tp2a18.Main.HAUTEUR;
import static ca.qc.bdeb.prog3.tp2a18.Main.LONGUEUR;

/**
 *
 * @author 1742177
 */
public class Balle extends Entite implements Bougeable, Collisionnable {

    private int deltaX = 10;
    private int deltaY;

    /**
     *Constructeur de la balle
     * @param x position en x 
     * @param y position en y
     * @param deltaY variation de la position de la balle en y
     */
    public Balle(float x, float y, int deltaY) {
        super(x, y, 16, 16, "images\\boulet.png");
        this.deltaY = deltaY;
    }

    /**
     * Gestion du déplacement de balle
     */
    @Override
    public void bouger() {

        x = x + deltaX;
        y = y + deltaY;
        if (x > LONGUEUR || y > HAUTEUR - 48 || y < 0) {
            this.detruire = true;
        }

    }

    /**
     * Gestion de la collision
     */
    @Override
    public void collision() {
        this.detruire = true;
    }

}
