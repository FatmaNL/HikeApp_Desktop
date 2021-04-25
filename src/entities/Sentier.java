/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Fatma NL
 */
public class Sentier {
    private int id;
    private String nomsentier;
    private String duree;
    private String distance;
    private String difficulte;
    private String departsentier;
    private String destinationsentier;

    public Sentier() {
    }

    public Sentier(int id, String nomsentier, String duree, String distance, String difficulte, String departsentier, String destinationsentier) {
        this.id = id;
        this.nomsentier = nomsentier;
        this.duree = duree;
        this.distance = distance;
        this.difficulte = difficulte;
        this.departsentier = departsentier;
        this.destinationsentier = destinationsentier;
    }

    public Sentier(String nomsentier, String duree, String distance, String difficulte, String departsentier, String destinationsentier) {
        this.nomsentier = nomsentier;
        this.duree = duree;
        this.distance = distance;
        this.difficulte = difficulte;
        this.departsentier = departsentier;
        this.destinationsentier = destinationsentier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomsentier() {
        return nomsentier;
    }

    public void setNomsentier(String nomsentier) {
        this.nomsentier = nomsentier;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public String getDepartsentier() {
        return departsentier;
    }

    public void setDepartsentier(String departsentier) {
        this.departsentier = departsentier;
    }

    public String getDestinationsentier() {
        return destinationsentier;
    }

    public void setDestinationsentier(String destinationsentier) {
        this.destinationsentier = destinationsentier;
    }

    @Override
    public String toString() {
        return "Sentier{" + "nomsentier=" + nomsentier + ", duree=" + duree + ", distance=" + distance + ", difficulte=" + difficulte + ", departsentier=" + departsentier + ", destinationsentier=" + destinationsentier + '}';
    }
    

}
