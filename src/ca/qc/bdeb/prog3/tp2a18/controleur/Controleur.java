/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.controleur;

import static ca.qc.bdeb.prog3.tp2a18.Main.HAUTEUR;
import static ca.qc.bdeb.prog3.tp2a18.Main.LONGUEUR;
import ca.qc.bdeb.prog3.tp2a18.modele.Modele;
import ca.qc.bdeb.prog3.tp2a18.vue.Jeu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Massi
 */
public class Controleur {
 private Modele modele = new Modele();

    /**
     *Constructeur du controleur
     */
    public Controleur() {

        try {
            AppGameContainer app;
            app = new AppGameContainer(new Jeu("La revanche de Princesse Peach", this, modele));
            app.setDisplayMode(LONGUEUR, HAUTEUR, false);
            app.setShowFPS(false);
            app.setVSync(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     *Gestion collision entre les ennemis et le joueur
     */
    public void collisionEnnemiJoueur() {
        modele.calculPointsDeVie();

    }

    /**
     *Gestion collision entre balle et ennemi
     */
    public void collisionBalleEnnemi() {
        modele.calculPointsEnnemi();
    }

    /**
     *Fin de partie
     */
    public void reset() {
        modele.reset();
    }

    /**
     *Gestion du bonus vie
     */
    public void bombeVie(){
        modele.ajoutVie();
    }

    /**
     * Gestion collision entre un bonus et un joueur
     */
    public void collisionBonusJoueur(){
        modele.calculPointsBonus();
    }
    
}
