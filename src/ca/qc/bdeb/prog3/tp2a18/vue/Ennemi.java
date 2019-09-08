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
 * @author 1742177
 */
public abstract class Ennemi extends Entite implements Bougeable, Collisionnable {
    protected int deltaX;
    protected int deltaY;
    protected ArrayList<Image> listeAnimation = new ArrayList();
    protected int animation = 0;

    /**
     * Constructeur
     * @param x position en x
     * @param y position en y
     * @param spriteSheet source de la sous-image
     * @param ligne ligne dans sprite
     * @param colonne colonne dans sprite
     */
    public Ennemi(float x, float y, SpriteSheet spriteSheet, int ligne, int colonne) {
        super(x, y, spriteSheet, ligne, colonne);
    }

    /**
     * Animation à recoder
     */
    protected abstract void animation();

    /**
     *Gestion de collision
     */
    @Override
    public void collision() {
        this.detruire = true;
    }
;

}
