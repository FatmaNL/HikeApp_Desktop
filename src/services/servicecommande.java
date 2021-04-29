/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.commande;
import utils.DataSource;
/**
 *
 * @author Asus
 */
public class servicecommande implements IService<commande>{
        Connection cnx = DataSource.getInstance().getcnx();
          @Override
   /* public void ajouter(commande t) {
        try {
            String requete = "INSERT INTO commande (refcommande,etat) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getRefcommande());
            pst.setString(2, t.getEtat());
            pst.setDate(3, (Date) t.getDatecommande());
            pst.executeUpdate();
            System.out.println("commande ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }*/
          
          public void ajouter(commande t) {
        try {
            String requete = "INSERT INTO commande (refcommande,datecommande,etat) VALUES ('" + t.getRefcommande() + "','" + t.getDatecommande() + "','"+ t.getEtat() + "')" ;
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("commande ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(String t) {
        try {
            String requete = "DELETE FROM commande WHERE refcommande=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t);
            pst.executeUpdate();
            System.out.println("commande suprimée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(commande t,String ref) {
        try {
            String requete = "UPDATE commande SET refcommande=?,etat=? WHERE refcommande=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getRefcommande());
            pst.setString(2, t.getEtat());
            pst.setString(3, ref);
            pst.executeUpdate();
            System.out.println("commande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<commande> afficher() {
        ObservableList<commande> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM commande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new commande( rs.getString(1), rs.getString("etat")) );
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public List<commande> afficheroccasion(){
         List<commande> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new commande( rs.getString(1), rs.getString("etat")) );
            }

        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       Stream<commande> fil= list.stream().filter(e -> e.getEtat().equals("occasion"));
       List<commande> fill =fil.collect(Collectors.toList());
       fill.forEach(System.out::println);
       return fill;
    }
/*@Override
    public commande rechercherParRef(String refcommande) {
        User user = null;
        String req = "SELECT * FROM commande WHERE refcommande LIKE '"+nom+"'";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
                commande = new commande(rs.getStrin(1));
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return user;
    }*/

    
}
