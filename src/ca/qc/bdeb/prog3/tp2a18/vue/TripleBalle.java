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
 * @author Massi
 */
public class TripleBalle extends Bonus {

    private int animation = 0;
    private ArrayList<Image> listeAnimation = new ArrayList();

    /**
     *Constructeur
     * @param x position en X
     * @param y position en Y
     * @param spriteSheet source de la sous-image
     */
    public TripleBalle(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 4, 3);
        listeAnimation.add(spriteSheet.getSubImage(3, 4));
        listeAnimation.add(spriteSheet.getSubImage(4, 4));
        listeAnimation.add(spriteSheet.getSubImage(5, 4));
        listeAnimation.add(spriteSheet.getSubImage(6, 4));
    }

    /**
     *Gestion du déplacement
     */
    @Override
    public void bouger() {
        animation();
        x = x-deltaX;
        if(x<-32){
            this.detruire = true;
        }
    }
/**
 *Gestion de l'animation
 */
    private void animation() {
        if (animation == 0) {
            this.image = listeAnimation.get(0);

        } else if (animation == 5) {
            this.image = listeAnimation.get(1);
        } else if (animation == 10) {
            this.image = listeAnimation.get(2);
        } else if (animation == 15) {
            this.image = listeAnimation.get(3);
        } else if (animation == 20) {
            animation = -1;
        }
            animation++;

        }
    
    

    }
