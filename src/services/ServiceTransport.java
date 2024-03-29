/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Transport;
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
public class ServiceTransport implements IService<Transport>{
    
    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<Transport> TransportList = FXCollections.observableArrayList();
    
    @Override
    public ObservableList<Transport> getList(){

        String query="select * from transport ";
        PreparedStatement pre;
        try {
            pre = cnx.prepareStatement(query);
             ResultSet rs =pre.executeQuery();
            while(rs.next())
            {    
            int id =rs.getInt("idtransport");
            String type = rs.getString("type");
            int volumemax = rs.getInt("volumemax");
            int nombre_transports = rs.getInt("nombre_transports");
                        Transport t = new Transport (id,type,volumemax, nombre_transports);
            TransportList.add(t);
            }   } 
        catch (SQLException ex) {
            Logger.getLogger(ServiceTransport.class.getName()).log(Level.SEVERE, null, ex);
        } 
     
     return TransportList;
    }

    @Override
    public void ajouter(Transport x) {
        try {
            String requete = "INSERT INTO transport (type,volumemax,nombre_transports) VALUES ('" + x.getType() + "','" + x.getVolumemax()+ "','" + x.getNombre_transports()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Transport ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }

    @Override
    public void supprimer(Transport x) {
        try {
            String requete = "DELETE FROM transport WHERE idtransport =" + x.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Transport supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Transport x) {
        try {
            String requete = "UPDATE transport SET type='" + x.getType()+ "',volumemax='" + x.getVolumemax()+ "',nombre_transports='" + x.getNombre_transports()+"' WHERE idtransport=" + x.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Transport modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    } 
 
}
