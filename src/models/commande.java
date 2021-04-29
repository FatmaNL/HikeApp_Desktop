/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class commande {
    
    private String refcommande;
    Date datecommande= new Date(System.currentTimeMillis());

    private String etat;

    public commande(String refcommande, String etat) {
        this.refcommande = refcommande;
        this.etat = etat;
    }

     public commande(String refcommande) {
        this.refcommande = refcommande;
        
    }

    public commande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public void setRefcommande(String refcommande) {
        this.refcommande = refcommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRefcommande() {
        return refcommande;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public String getEtat() {
        return etat;
    }

    @Override
    public String toString() {
        return "commande{" + "refcommande=" + refcommande + ", datecommande=" + datecommande + ", etat=" + etat + '}';
    }
      
    
}
