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
public class Categorie {
    private int idcategorie;
    private String nomcategorie;

    public Categorie(int idcategorie, String nomcategorie) {
        this.idcategorie = idcategorie;
        this.nomcategorie = nomcategorie;
    }

    public Categorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
    
}
