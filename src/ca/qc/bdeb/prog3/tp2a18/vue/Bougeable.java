/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author MGrenon
 */
public interface Bougeable {
    
    /**
     * Déplacement de l'instance
     */
    public void bouger();

    /**
     *Retourne le rectangle de l'instance
     * @return le rectangle de l'instance
     */
    public Rectangle getRectangle();
}

