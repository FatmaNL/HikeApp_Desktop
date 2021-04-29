/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.services;

import gestionproduit.models.Categorie;
import gestionproduit.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceCategorie implements IService<Categorie> {

     Connection cnx = DataSource.getInstance().getcnx();
     
    @Override
    public void ajouter(Categorie t) {
        try {
            String requete = "INSERT INTO categorie (nomcategorie) VALUES ('" + t.getNomcategorie() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Categorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categorie t) {
        try {
            String requete = "UPDATE categorie SET nomcategorie='" + t.getNomcategorie()+ "' WHERE idcategorie=" + t.getIdcategorie();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Categorie t) {
         try {
            String requete = "DELETE FROM categorie WHERE idcategorie=" + t.getIdcategorie();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }

    @Override
    public List<Categorie> afficher() {
         List<Categorie> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Categorie(rs.getInt("idcategorie"),rs.getString("nomcategorie")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
