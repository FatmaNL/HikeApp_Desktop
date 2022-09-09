/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Sentier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Fatma NL
 */
public class ServiceSentier implements IServiceSentier<Sentier> {

    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<Sentier> SentierList = FXCollections.observableArrayList();

    @Override
    public ObservableList<Sentier> getList() {

        String query = "select * from sentier ";
        PreparedStatement pre;
        try {
            pre = cnx.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idsentier");
                String nom = rs.getString("nomsentier");
                String duree = rs.getString("duree");
                String distance = rs.getString("distance");
                String difficulte = rs.getString("difficulte");
                String depart = rs.getString("departsentier");
                String destination = rs.getString("destinationsentier");
                Sentier s = new Sentier(id, nom, duree, distance, difficulte, depart, destination);
                SentierList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTransport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return SentierList;
    }

    @Override
    public void ajouter(Sentier x) {
        try {
            String requete = "INSERT INTO sentier (nomsentier, duree, distance, difficulte, departsentier, destinationsentier) "
                    + "VALUES ('" + x.getNomsentier()+ "','" + x.getDuree()+ "','" + x.getDistance()+ "','" + x.getDifficulte()+ "','" + x.getDepartsentier()+ "','" + x.getDestinationsentier()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Sentier ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Sentier x) {
        try {
            String requete = "DELETE FROM sentier WHERE idsentier =" + x.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Sentier supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Sentier x) {
        try {
            String requete = "UPDATE sentier SET nomsentier='" + x.getNomsentier()+ "',duree='" + x.getDuree()+ "',distance='" + x.getDistance()+ "',difficulte='" + x.getDifficulte()+ "',departsentier='" + x.getDepartsentier()+ "',destinationsentier='" + x.getDestinationsentier()
                    +"' WHERE idsentier=" + x.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("id: "+ x.getId());
            System.out.println("Sentier modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
