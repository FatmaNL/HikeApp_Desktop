/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.gui;

import com.jfoenix.controls.JFXTextField;
import static com.jfoenix.svg.SVGGlyphLoader.clear;
import gestionproduit.models.Categorie;
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
public class AjoutCategorieController implements Initializable {

    @FXML
    private JFXTextField nomfld;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Categorie categorie = null;
    private boolean update;
    int categorieId;
    
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
        String name = nomfld.getText();
 
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
         nomfld.setText(null);
    }

    private void getQuery() {
         if (update == false) {
            
            query = "INSERT INTO `categorie`( `nomcategorie`) VALUES (?)";

        }else{
            query = "UPDATE `categorie` SET "
                    + "`nomcategorie`=? WHERE idcategorie = '"+categorieId+"'";
        }

    }

    private void insert() {
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomfld.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void setTextField(int id, String name) {

        categorieId = id;
        nomfld.setText(name);
     }
     
     void setUpdate(boolean b) {
        this.update = b;

    }

    
}
