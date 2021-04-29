/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.pidevrondo;

import gestionproduit.models.Categorie;
import gestionproduit.models.Produit;
import gestionproduit.services.ServiceCategorie;
import gestionproduit.services.ServiceProduit;
import gestionproduit.utils.DataSource;

/**
 *
 * @author LENOVO
 */
public class PidevRondo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* ServiceCategorie sp = new ServiceCategorie();
        sp.modifier(new Categorie(18,"adidas"));
        sp.afficher().forEach(System.out::println);*/
        
         /*ServiceCategorie sp = new ServiceCategorie();
        sp.supprimer(new Categorie(18,"adidas"));
        sp.afficher().forEach(System.out::println);*/
        
        ServiceProduit sp = new ServiceProduit();
        sp.ajouter(new Produit("tente",4,12.35,"","nike"));
        sp.afficher().forEach(System.out::println);

         
    }
    
}
