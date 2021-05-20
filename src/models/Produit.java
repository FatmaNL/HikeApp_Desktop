/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author LENOVO
 */
public class Produit extends Categorie{
    private int numproduit;
    private String nomproduit;
    private int quantite;
    private double prix;
    private String image;
//    private int cat;
//    private String cat_name;
    
     public Produit(String nomproduit, int quantite, double prix, String image,String nomcategorie) {
        super(nomcategorie);
        this.nomproduit = nomproduit;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
       
    }

    public Produit(int numproduit, String nomproduit, int quantite, double prix, String image,String nomcategorie) {
        super(nomcategorie);
        this.numproduit = numproduit;
        this.nomproduit = nomproduit;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
       
    }

    public void setNumproduit(int numproduit) {
        this.numproduit = numproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumproduit() {
        return numproduit;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

}
