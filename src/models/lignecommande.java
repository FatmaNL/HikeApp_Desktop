/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Asus
 */
public class lignecommande {
      private int idlignecommande ;
      private int qtecommande;

    public lignecommande(int idlignecommande, int qtecommande) {
        this.idlignecommande = idlignecommande;
        this.qtecommande = qtecommande;
    }

    public int getIdlignecommande() {
        return idlignecommande;
    }

    public void setIdlignecommande(int idlignecommande) {
        this.idlignecommande = idlignecommande;
    }

    public int getQtecommande() {
        return qtecommande;
    }

    public void setQtecommande(int qtecommande) {
        this.qtecommande = qtecommande;
    }
 
  @Override
    public String toString() {
    return "lignecommande(" +"idlignecommande" + idlignecommande +"qtecommande"+qtecommande ;
}
}
