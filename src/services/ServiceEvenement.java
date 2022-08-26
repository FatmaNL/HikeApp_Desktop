/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
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
public class ServiceEvenement implements IServiceEvenement<Evenement> {

    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<Evenement> EvenementList = FXCollections.observableArrayList();

    @Override
    public ObservableList<Evenement> getList() {

        String query = "select * from evenement ";
        PreparedStatement pre;
        try {
            pre = cnx.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nomevenement = rs.getString("nomevenement");
                String depart = rs.getString("depart");
                String destination = rs.getString("destination");
                int nbparticipant = rs.getInt("nbparticipant");
                String dateevenement = rs.getString("dateevenement");
                int duree = rs.getInt("duree");
                double prix = rs.getDouble("prix");
                String programme = rs.getString("programme");
                String contact = rs.getString("contact");
                String infos = rs.getString("infos");
                String type = rs.getString("type");
                String circuit = rs.getString("circuit");
                String image = rs.getString("image");
                int idTransport = rs.getInt("id_transport");
                Evenement e = new Evenement(id, nomevenement, depart, destination, nbparticipant, dateevenement, duree, prix, programme, contact, infos, type, circuit, image, idTransport);
                EvenementList.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTransport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return EvenementList;
    }

    @Override
    public void ajouter(Evenement x) {
        try {
            String requete = "INSERT INTO evenement (nomevenement, depart, destination, nbparticipant, dateevenement, duree, prix, programme, contact, infos, type, circuit, id_transport) "
                    + "VALUES ('" + x.getNomevenement()+ "','" + x.getDepart()+ "','" + x.getDestination()+ "','" + x.getNbparticipant()+ "','" + x.getDateevenement()+ "','" + x.getDuree()+  "','" + x.getPrix()+  "','" + x.getProgramme()+  "','" + x.getContact()+  "','" + x.getInfos()+  "','" + x.getType()+  "','" + x.getCircuit()+ "','" + x.getIdTransport()+ "')";
            
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Evenement ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Evenement x) {
        try {
            String requete = "DELETE FROM evenement WHERE id =" + x.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Evenement supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement x) {
        try {
            String requete = "UPDATE evenement SET nomevenement='" + x.getNomevenement()+ "',depart='" + x.getDepart()+ "',destination='" + x.getDestination()
                    + "',nbparticipant='" + x.getNbparticipant()+ "',dateevenement='" + x.getDateevenement()+ "',duree='" + x.getDuree()
                    + "',prix='" + x.getPrix()+ "',programme='" + x.getProgramme()+ "',contact='" + x.getContact()+ "',infos='" + x.getInfos()
                    + "',type='" + x.getType()+ "',circuit='" + x.getCircuit()+ "',id_transport='" + x.getIdTransport()
                    +"' WHERE id=" + x.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("id: "+ x.getId());
            System.out.println("Evenement modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
