/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.modele;

import java.util.Observable;

/**
 *
 * @author Marc-Antoine Massi
 */
public class Modele extends Observable {

    private int nbPointsDeVie = 3;
    private int nbPoints = 0;
    private boolean finPartie = false;

    /**
     * Calcul des points de vie
     */
    public void calculPointsDeVie() {
        nbPointsDeVie = nbPointsDeVie - 1;
        gestionFinPartie();
        majObservers();
    }

    /**
     * Calcul des points avec les ennemis
     */
    public void calculPointsEnnemi() {
        nbPoints = nbPoints + 5;
        majObservers();
    }

    /**
     * Calcul points bonus
     */
    public void calculPointsBonus() {
        nbPoints = nbPoints + 25;
        majObservers();
    }

    /**
     * Retourne les points actuels
     *
     * @return nbPoints le nombre de points
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * Retourne les points de vie
     *
     * @return nbPointsDeVie le nombre de points de vie
     */
    public int getNbPointsDeVie() {
        return nbPointsDeVie;
    }

    /**
     * Appelle la vue pour se mettre à jour
     */
    private void majObservers() {
        setChanged();
        notifyObservers();
    }

    /**
     * Gestion de la fin de partie
     */
    private void gestionFinPartie() {
        if (nbPointsDeVie <= 0) {
            finPartie = true;
        }
    }

    /**
     * Remet tout à zéro comme au départ
     */
    public void reset() {
        nbPointsDeVie = 3;
        nbPoints = 0;
        finPartie = false;
        majObservers();
    }

    /**
     * Retourne la valeur de si fin partie ou pas
     *
     * @return finPartie si fin de partie ou pas
     */
    public boolean getFinPartie() {
        return this.finPartie;
    }

    /**
     * Ajoute une vie au joueur
     */
    public void ajoutVie() {
        if (this.nbPointsDeVie < 3) {
            this.nbPointsDeVie++;
        }

        majObservers();
    }

}
