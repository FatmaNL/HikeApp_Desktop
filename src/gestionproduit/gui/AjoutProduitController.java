/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.gui;

import com.jfoenix.controls.JFXTextField;
import gestionproduit.models.Categorie;
import gestionproduit.models.Produit;
import gestionproduit.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private JFXTextField nomprod;
    @FXML
    private JFXTextField quanprod;
    @FXML
    private JFXTextField prixprod;
    @FXML
    private JFXTextField catprod;
    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produit produit = null;
    private boolean update;
    int produitId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouter(MouseEvent event) {
        connection = DataSource.getInstance().getcnx();
        String name = nomprod.getText();
        String quantite = quanprod.getText();
        String prix = prixprod.getText();
        String categorie =catprod.getText();

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clear();

        }
    }

    @FXML
    private void clear() {
         nomprod.setText(null);
          quanprod.setText(null);
           prixprod.setText(null);
           catprod.setText(null);
    }

    private void getQuery() {
         if (update == false) {
            
            query = "INSERT INTO `produit`( `nomproduit`, `quantite`, `prix`, cat_name) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `produit` SET "
                    + "`nomproduit`=?,"
                    + "`quantite`=?,"
                    + "`prix`=? ,"
                    +"`cat_name`=? WHERE numproduit = '"+produitId+"'";
        }
    }

    private void insert() {
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomprod.getText());
            preparedStatement.setString(2, quanprod.getText());
            preparedStatement.setString(3, prixprod.getText());
            preparedStatement.setString(4, catprod.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void setTextField(int id, String name,int q,double p) {

        produitId = id;
        nomprod.setText(name);
        quanprod.setText(Integer.toString(q));
        prixprod.setText(Double.toString(p));
        
     }
     
     void setUpdate(boolean b) {
        this.update = b;

    }

}
