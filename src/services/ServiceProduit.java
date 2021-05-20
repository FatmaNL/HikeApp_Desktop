/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import services.IService2;
import models.Categorie;
import models.Produit;
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
public class ServiceProduit implements IService2<Produit>{
    Connection cnx = DataSource.getInstance().getcnx();

    @Override
    public void ajouter(Produit t) {
         try {
            String requete = "INSERT INTO produit (nomproduit,quantite,prix,image,cat,cat_name) VALUES ('" + t.getNomproduit() + "','" + t.getQuantite()+"','" + t.getPrix()+"','" + t.getImage()+"','" + t.getNomcategorie()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("produit ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Produit t) {
       try {
            String requete = "UPDATE produit SET nomproduit='" +t.getNomproduit()+"',quantite='"+t.getQuantite()+ "',prix='"+t.getPrix()+"',image='"+t.getImage()+"',cat_name='"+t.getNomcategorie()+"' WHERE id=" + t.getNumproduit();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Produit t) {
         try {
            String requete = "DELETE FROM produit WHERE id=" + t.getNumproduit();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }

    @Override
    public List<Produit> afficher() {
        List<Produit> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Produit(rs.getInt("numproduit"),rs.getString("nomproduit"),rs.getInt("quantite"),rs.getDouble("prix"),rs.getString("image"),rs.getString("cat_name")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
