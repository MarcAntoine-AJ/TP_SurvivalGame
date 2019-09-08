/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;

import ca.qc.bdeb.prog3.tp2a18.controleur.Controleur;
import static ca.qc.bdeb.prog3.tp2a18.Main.HAUTEUR;
import static ca.qc.bdeb.prog3.tp2a18.Main.LONGUEUR;
import ca.qc.bdeb.prog3.tp2a18.modele.Modele;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.scene.input.KeyCode;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Le jeu Slick2D
 *
 * @author Marc-Antoine Massinissa Abou Jaoude Merbouche
 */
public class Jeu extends BasicGame implements Observer {

    private Controleur controleur;
    private Modele modele;
    private ArrayList<Bougeable> listeBougeable = new ArrayList<>();
    private ArrayList<Entite> listeEntite = new ArrayList<>();
    private ArrayList<KeyCode> listeKeys = new ArrayList<>();
    private Input input;
    private SpriteSheet spriteMonde;
    private int hauteurArbre;
    private Random r = new Random();
    private Joueur joueur;
    private SpriteSheet spritePeach;
    private SpriteSheet spriteSheetDivers;
    private long millis;
    private Image imageCoeur;
    private int nbPoints;
    private int nbVie;
    private boolean finPartie;
    private long millisFin;
    private long millisEnnemi;
    private long tempsTripleBalle;
    private boolean tripleBalleActive;

    /**
     * Contructeur de Jeu
     *
     * @param gamename Le nom du jeu
     * @param controleur Le controleur du jeu
     * @param modele Le modèle du jeu
     */
    public Jeu(String gamename, Controleur controleur, Modele modele) {

        super(gamename);
        this.modele = modele;
        this.controleur = controleur;
        modele.addObserver(this);

        // …
    }

    /**
     * Initialiser le jeu
     *
     * @param container le container du jeu
     * @throws SlickException si le jeu plante
     */
    public void init(GameContainer container) throws SlickException {

        millisEnnemi = System.currentTimeMillis();
        input = container.getInput();
        nbPoints = modele.getNbPoints();
        nbVie = modele.getNbPointsDeVie();
        finPartie = modele.getFinPartie();
        imageCoeur = new Image("images\\coeur.png");
        spriteMonde = new SpriteSheet("images\\sprites_monde.png", 32, 32);
        spritePeach = new SpriteSheet("images\\sprites_princess.png", 32, 64);
        spriteSheetDivers = new SpriteSheet("images\\sprites_divers.png", 32, 32);
        millis = System.currentTimeMillis();
        creerCiel();
        creerPlancher();
        joueur = new Joueur(32, HAUTEUR - 96, spritePeach);
        listeEntite.add(joueur);
        creerEnnemi(0);
    }

    /**
     * Update du jeu
     *
     * @param container le container du jeu
     * @param delta N/A
     * @throws SlickException Si le update plante
     */
    public void update(GameContainer container, int delta) throws SlickException {
        if (!finPartie) {
            if (System.currentTimeMillis() - millisEnnemi >= 2000) {
                creerEnnemi(r.nextInt(3));
                millisEnnemi = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - tempsTripleBalle >= 10000) {
                tripleBalleActive = false;
            }
            for (Bougeable b : listeBougeable) {
                b.bouger();
            }
            getKeys();
            traiterKeys();
            gererCollision();
            detruire();
            feuPlante();
            if (r.nextInt(500) == 1) {
                arbre();
            }
            if (r.nextInt(500) == 1) {
                creerEnnemi(3);
            }
            millisFin = System.currentTimeMillis();
        } else {
            finPartie();

        }

    }

    /**
     * Dessiner le jeu
     *
     * @param container le container du jeu
     * @param g le graphics du container
     * @throws SlickException Si le render plante
     */
    public void render(GameContainer container, Graphics g) throws SlickException {
        for (Entite e : listeEntite) {
            g.drawImage(e.getImage(), e.getX(), e.getY());
        }
        for (int i = 0; i < nbVie; i++) {
            g.drawImage(imageCoeur, 10 + 32 * i, 10);
        }
        //  System.out.println(modele.getNbPoints());
        g.drawString("" + nbPoints, 10, 42);
        if (finPartie) {
            g.drawString("GAME OVER", LONGUEUR / 2, HAUTEUR / 2);
        }
    }

    /**
     * Update du patron observateur (MVC)
     *
     * @param o N/A
     * @param arg N/A
     */
    @Override
    public void update(Observable o, Object arg) {
        nbPoints = modele.getNbPoints();
        nbVie = modele.getNbPointsDeVie();
        finPartie = modele.getFinPartie();
    }

    /**
     * Méthode qui supprimes les entités à supprimer
     */
    private void detruire() {
        ArrayList<Entite> listeTempEntite = new ArrayList();
        ArrayList<Bougeable> listeTempBoug = new ArrayList();

        for (Entite e : listeEntite) {
            if (e.getDetruire()) {
                listeTempEntite.add(e);

            }
        }
        for (Bougeable e : listeBougeable) {
            if (((Entite) e).getDetruire()) {
                listeTempBoug.add(e);

            }
        }
        for (Entite e : listeTempEntite) {
            if (e instanceof Ennemi && r.nextInt(10) == 0 && !(e instanceof FeuPlante)) {
                creerBonus((Ennemi) e);
            }
        }

        listeEntite.removeAll(listeTempEntite);
        listeTempEntite.clear();
        listeBougeable.removeAll(listeTempBoug);
        listeTempBoug.clear();
    }

    /**
     * Génère les arbres
     */
    private void arbre() {
        hauteurArbre = r.nextInt(7) + 1;
        for (int a = 2; a < hauteurArbre + 2; a++) {
            Tronc tronc = new Tronc(LONGUEUR, HAUTEUR - (a * 32), spriteMonde);
            listeBougeable.add(tronc);
            listeEntite.add(tronc);
        }
        Feuillage feuillage = new Feuillage(LONGUEUR, HAUTEUR - 32 * (hauteurArbre + 2), spriteMonde);
        listeBougeable.add(feuillage);
        listeEntite.add(feuillage);
    }

    /**
     * Gère les touches au clavier
     */
    private void getKeys() {

       
        if (input.isKeyDown(Input.KEY_D)) {
            if (!listeKeys.contains(KeyCode.D)) {
                listeKeys.add(KeyCode.D);
            }
        } else {
            listeKeys.remove(KeyCode.D);
        }
        if (input.isKeyDown(Input.KEY_A)) {
            if (!listeKeys.contains(KeyCode.A)) {
                listeKeys.add(KeyCode.A);
            }

        } else {
            listeKeys.remove(KeyCode.A);
        }
        if (input.isKeyDown(Input.KEY_W)) {
            if (!listeKeys.contains(KeyCode.W)) {
                listeKeys.add(KeyCode.W);
            }

        } else {
            listeKeys.remove(KeyCode.W);
        }
        if (input.isKeyDown(Input.KEY_S)) {
            if (!listeKeys.contains(KeyCode.S)) {
                listeKeys.add(KeyCode.S);
            }

        } else {
            listeKeys.remove(KeyCode.S);
        }
        if (input.isKeyDown(Input.KEY_SPACE)) {
            if (!listeKeys.contains(KeyCode.SPACE)) {
                listeKeys.add(KeyCode.SPACE);
            }

        } else {
            listeKeys.remove(KeyCode.SPACE);
        }

    }

    /**
     * Traite les touches au clavier
     */
    private void traiterKeys() {
        joueur.bouger(listeKeys);
        tirer();

    }

    /**
     * Tire le nombre de balles/boulets selon la touche
     */
    private void tirer() {
        if (listeKeys.contains(KeyCode.SPACE) && System.currentTimeMillis() - millis >= 500) {

            Balle balle = new Balle(joueur.getX() + 32, joueur.getY() + 32, 0);
            listeEntite.add(balle);
            listeBougeable.add(balle);
            if (tripleBalleActive) {
                Balle balle2 = new Balle(joueur.getX() + 32, joueur.getY() + 32, 5);
                listeEntite.add(balle2);
                listeBougeable.add(balle2);
                Balle balle3 = new Balle(joueur.getX() + 32, joueur.getY() + 32, -5);
                listeEntite.add(balle3);
                listeBougeable.add(balle3);
            }
            millis = System.currentTimeMillis();
        }
    }

    /**
     * Gère les collisions entre les entités
     */
    private void gererCollision() {

        for (Bougeable b : listeBougeable) {

            if (joueur.getRectangle().intersects(b.getRectangle()) && b instanceof Ennemi) {
                ((Collisionnable) b).collision();
                controleur.collisionEnnemiJoueur();

            } else if (joueur.getRectangle().intersects(b.getRectangle()) && b instanceof Bonus) {
                ((Collisionnable) b).collision();
                controleur.collisionBonusJoueur();
                gestionBonus((Bonus) b);
            }
            for (Bougeable b2 : listeBougeable) {

                if (b != b2 && !(b instanceof FeuPlante) && !(b2 instanceof FeuPlante)) {
                    if (b.getRectangle().intersects(b2.getRectangle())) {
                        if ((b instanceof Balle && b2 instanceof Ennemi || b instanceof Ennemi && b2 instanceof Balle) && !(((Entite) b).getDetruire()) && !(((Entite) b2).getDetruire())) {
                            ((Collisionnable) b).collision();
                            ((Collisionnable) b2).collision();
                            controleur.collisionBalleEnnemi();
                        }
                    }
                }
            }
        }
    }

    /**
     * Élimine les ennemis sur l'écran
     */
    private void bombeMega() {

        for (Entite b : listeEntite) {
            if (b instanceof Ennemi) {
                ((Collisionnable) b).collision();
                controleur.collisionBalleEnnemi();
            }

        }
        for (Bougeable b : listeBougeable) {
            if (b instanceof Ennemi) {
                ((Collisionnable) b).collision();
            }

        }

    }

    /**
     * Crée les bonus
     *
     * @param ennemi ennemi où le bonus apparaît
     */
    private void creerBonus(Ennemi ennemi) {
        int random = r.nextInt(3);
        switch (random) {
            case 0: {
                BombeMega bombe = new BombeMega(ennemi.getX(), ennemi.getY(), spriteSheetDivers);
                listeBougeable.add(bombe);
                listeEntite.add(bombe);
                break;
            }
            case 1: {
                BombeVie bombe = new BombeVie(ennemi.getX(), ennemi.getY(), spriteSheetDivers);
                listeBougeable.add(bombe);
                listeEntite.add(bombe);
                break;
            }
            case 2:
                TripleBalle tripleBalle = new TripleBalle(ennemi.getX(), ennemi.getY(), spriteSheetDivers);
                listeEntite.add(tripleBalle);
                listeBougeable.add(tripleBalle);
                break;
            default:
                break;
        }
    }

    /**
     * Gère les collisions avec les bonus
     *
     * @param b le bonus en collision
     */
    private void gestionBonus(Bonus b) {
        if (b instanceof BombeVie) {
            bombeVie();
        } else if (b instanceof BombeMega) {
            bombeMega();
        } else if (b instanceof TripleBalle) {
            tripleBalleActive = true;
            tempsTripleBalle = System.currentTimeMillis();
        }
    }

    /**
     * Action effectué lors collision avec bombe vie
     */
    private void bombeVie() {
        controleur.bombeVie();
    }

    /**
     * Crée les ennemis sur l'écran
     *
     * @param type défini le type d'ennemi généré
     */
    private void creerEnnemi(int type) {
        int posYRand;
        switch (type) {
            case 0:
                posYRand = r.nextInt(400) + 190;
                EnnemiVolantSin ennemiVolantSin1 = new EnnemiVolantSin(LONGUEUR, posYRand, spriteSheetDivers);
                listeEntite.add(ennemiVolantSin1);
                listeBougeable.add(ennemiVolantSin1);
                EnnemiVolantSin ennemiVolantSin2 = new EnnemiVolantSin(LONGUEUR + 32, posYRand, spriteSheetDivers);
                listeEntite.add(ennemiVolantSin2);
                listeBougeable.add(ennemiVolantSin2);
                EnnemiVolantSin ennemiVolantSin3 = new EnnemiVolantSin(LONGUEUR + 64, posYRand, spriteSheetDivers);
                listeEntite.add(ennemiVolantSin3);
                listeBougeable.add(ennemiVolantSin3);
                break;
            case 1:
                posYRand = r.nextInt(400) + 100;
                Masque masque1 = new Masque(LONGUEUR + 90, posYRand, spriteSheetDivers);
                listeEntite.add(masque1);
                listeBougeable.add(masque1);
                Masque masque2 = new Masque(LONGUEUR + 122, posYRand, spriteSheetDivers);
                listeEntite.add(masque2);
                listeBougeable.add(masque2);
                Masque masque3 = new Masque(LONGUEUR + 154, posYRand, spriteSheetDivers);
                listeEntite.add(masque3);
                listeBougeable.add(masque3);
                break;
            case 2:
                posYRand = r.nextInt(HAUTEUR - 180);
                BouleDeFeu bouleDeFeu1 = new BouleDeFeu(LONGUEUR, posYRand, spriteSheetDivers);
                listeEntite.add(bouleDeFeu1);
                listeBougeable.add(bouleDeFeu1);
                BouleDeFeu bouleDeFeu2 = new BouleDeFeu(LONGUEUR, posYRand + 110, spriteSheetDivers);
                listeEntite.add(bouleDeFeu2);
                listeBougeable.add(bouleDeFeu2);
                break;
            case 3:
                PlanteMechante planteMechante = new PlanteMechante(LONGUEUR, HAUTEUR - 64, spriteSheetDivers);
                listeEntite.add(planteMechante);
                listeBougeable.add(planteMechante);
                break;
            default:
                break;
        }

    }

    /**
     * Crée le ciel
     */
    private void creerCiel() {
        for (int i = 0; i < HAUTEUR - 32; i = i + 32) {
            for (int j = 0; j < LONGUEUR; j = j + 32) {
                Ciel ciel = new Ciel(j, i, spriteMonde);
                listeEntite.add(ciel);
            }

        }
    }

    /**
     * Crée le plancher
     */
    private void creerPlancher() {
        for (int j = 0; j <= LONGUEUR; j = j + 32) {
            Plancher plancher = new Plancher(j, HAUTEUR - 32, spriteMonde);
            listeEntite.add(plancher);
            listeBougeable.add(plancher);
        }
    }

    /**
     * Crée les ennemis plante
     */
    private void feuPlante() {
        ArrayList<FeuPlante> listeTempFeuPlante = new ArrayList<>();
        for (Bougeable b : listeBougeable) {
            if (b instanceof PlanteMechante) {
                if (((PlanteMechante) b).getAnimation() == 70) {
                    FeuPlante feuPlante = new FeuPlante(((PlanteMechante) b).getX(), ((PlanteMechante) b).getY(), spriteSheetDivers);
                    listeTempFeuPlante.add(feuPlante);
                }
            }
        }
        for (FeuPlante fp : listeTempFeuPlante) {
            listeEntite.add(fp);
            listeBougeable.add(fp);
        }
    }

    /**
     * Gestion de la fin de partie
     */
    private void finPartie() {
        ArrayList<Entite> listeTempEntite = new ArrayList<>();
        ArrayList<Bougeable> listeTempBougeable = new ArrayList<>();
        if (System.currentTimeMillis() - millisFin >= 2000) {
            controleur.reset();
            joueur.setLocation(32, HAUTEUR - 96);
            listeKeys.clear();
            for (Entite e : listeEntite) {
                if (e instanceof Collisionnable) {
                    listeTempEntite.add(e);
                }
            }
            listeEntite.removeAll(listeTempEntite);
            for (Bougeable b : listeBougeable) {
                if (b instanceof Collisionnable) {
                    listeTempBougeable.add(b);
                }
            }
            listeBougeable.removeAll(listeTempBougeable);
            listeTempEntite.clear();
            listeTempBougeable.clear();
            finPartie = false;
            millisEnnemi = System.currentTimeMillis();

        }
    }
}
