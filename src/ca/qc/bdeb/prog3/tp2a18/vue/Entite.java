/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.tp2a18.vue;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author MGrenon
 */
public abstract class Entite {


    protected float x, y, 

    width, height; // position et taille
    protected Image image; // L’image de l’entité
    protected boolean detruire = false; // Ne pas détruire si false

    /**
     * Constructeur d'Entite avec image sur le disque
     * @param x position de l'entité dans l'écran - x
     * @param y position de l'entité dans l'écran - y
     * @param width largeur de l'image
     * @param height hauteur de l'image 
     * @param imagepath chemin d'accès de l'image sur le disque
     */
    public Entite(float x, float y, float width, float height, String imagepath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        try {
            image = new Image(imagepath);
        } catch (SlickException e) {
            System.out.println("Image non trouvée pour " + getClass());
        }
    }

    /**
     * Constructeur d'Entite #2 - Avec SpriteSheet
     * @param x position de l'entité dans l'écran - x
     * @param y position de l'entité dans l'écran - y
     * @param spriteSheet SpriteSheet qui contient l'image
     * @param ligne la ligne dans le SpriteSheet de l'image
     * @param colonne la colonne dans le SpriteSheet de l'image
     */
    public Entite(float x, float y, SpriteSheet spriteSheet, int ligne, int colonne) {
        this.x = x;
        this.y = y;
        this.image = spriteSheet.getSubImage(colonne, ligne);        
        this.width = image.getWidth();
        this.height = image.getHeight();        
        
    }

    /**
     *
     * @return
     */
    public float getX() { // Position en X du coin supérieur gauche de l’entite
        return x;
    }

    /**
     *
     * @return
     */
    public float getY() { // Position en Y du coin supérieur gauche de l’entite
        return y;
    }

    /**
     *
     * @return
     */
    public float getWidth() { // Largeur de l’entite
        return width;
    }

    /**
     *
     * @return
     */
    public float getHeight() { // Hauteur de l’entite
        return height;
    }

    /**
     *
     * @return
     */
    public Image getImage() { // Retourne l’image de l’entité
        return image;
    }

    /**
     *
     * @return
     */
    public Rectangle getRectangle(){ // Retourne le rectangle qui englobe l’entité
       return new Rectangle(x, y, width, height);
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setLocation(float x, float y) { // Pour déplacer l’élément depuis le Jeu 
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return
     */
    public boolean getDetruire(){ // Si l’objet doit être détruit --> true, false sinon
        return detruire;
    }
}

