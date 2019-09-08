/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;

import static ca.qc.bdeb.prog3.tp2a18.Main.HAUTEUR;
import static ca.qc.bdeb.prog3.tp2a18.Main.LONGUEUR;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1742177
 */
public class Joueur extends Entite {

    private int animation = 0;

    private int osciller = 0;
    private int deltaX = 5;
    private int deltaY = 5;

    private ArrayList<Image> listeAnimationCourir = new ArrayList<>();
    private ArrayList<Image> listeAnimationVoler = new ArrayList<>();

    /**
     * Constructeur Joueur
     *
     * @param x position x du joueur
     * @param y posiiton y du joueur
     * @param spriteSheet source de la sous-image
     */
    public Joueur(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 0, 0);
        listeAnimationCourir.add(spriteSheet.getSubImage(3, 0));
        listeAnimationCourir.add(spriteSheet.getSubImage(4, 0));
        listeAnimationCourir.add(spriteSheet.getSubImage(5, 0));
        listeAnimationVoler.add(spriteSheet.getSubImage(0, 0));
        listeAnimationVoler.add(spriteSheet.getSubImage(1, 0));
        listeAnimationVoler.add(spriteSheet.getSubImage(2, 0));

    }

    /**
     * Gestion du déplacement de la princesse
     *
     * @param listeKeys liste des touches
     */
    public void bouger(ArrayList<KeyCode> listeKeys) {

        animation();
        if (listeKeys.contains(KeyCode.D) && (x + deltaX <= LONGUEUR - width)) {
            x = x + deltaX;
        }
        if (listeKeys.contains(KeyCode.A) && x > 0) {
            x = x - deltaX;
        }
        if (listeKeys.contains(KeyCode.W) && y > 0) {
            y = y - deltaY;
        }
        if (listeKeys.contains(KeyCode.S) && (y + deltaY <= HAUTEUR - height - 32)) {
            y = y + deltaY;
        }

    }

    /**
     * Gestion de l'animation
     */
    private void animation() {
        if (this.y == HAUTEUR - 96) {
          
            if (animation == 0) {
                this.image = listeAnimationCourir.get(0);
            } else if (animation == 20) {
                this.image = listeAnimationCourir.get(1);
            } else if (animation == 40) {
                this.image = listeAnimationCourir.get(2);
            } else if (animation == 60) {
                animation = -1; // Pour remettre à 0 avec le ++
            }

        } else {
            osciller();
            if (animation == 0) {
                this.image = listeAnimationVoler.get(0);
            } else if (animation == 20) {
                this.image = listeAnimationVoler.get(1);
            } else if (animation == 40) {
                this.image = listeAnimationVoler.get(2);
            } else if (animation == 60) {
                animation = -1; // Pour remettre à 0 avec le ++
            }

        }
        animation++;
    }

    /**
     * Fait osciller la princesse
     */
    private void osciller() {
        if (osciller == 0) {
            y = y - 2;
        } else if (osciller == 10) {
            y = y - 2;
        } else if (osciller == 20) {
            y = y + 2;
        } else if (osciller == 30) {
            y = y + 2;
        } else if (osciller == 40) {
            osciller = -1;
        }
        osciller++;

    }

}
