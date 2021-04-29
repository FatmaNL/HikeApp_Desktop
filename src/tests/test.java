/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import models.commande;
import utils.DataSource;
import services.servicecommande;

/**
 *
 * @author Asus
 */
public class test {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          DataSource ds = DataSource.getInstance();
    servicecommande sp = new servicecommande();
        //sp.ajouter(new commande("dd", "ff"));
        
        sp.afficheroccasion();
        //sp.supprimer(new commande("dd"));
       sp.modifier(new commande("dd", "occasionar"),"dd");
        //sp.afficher().forEach(System.out::println);
    }


}
