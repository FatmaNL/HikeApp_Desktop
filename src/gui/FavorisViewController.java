/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import models.Produit;
import gestionproduit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Favoris;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FavorisViewController implements Initializable {
    @FXML
    private TableView<Produit> table1;
    @FXML
    private TableColumn<Produit, String> idprod;
    @FXML
    private TableColumn<Produit, String> nomprod;
    @FXML
    private TableColumn<Produit, String> quanprod;
    @FXML
    private TableColumn<Produit, String> prixprod;
    @FXML
    private TableColumn<Produit, String> catprod;
    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produit produit = null;

    ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
    ObservableList<Favoris> FavorisList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();
    }

    int getUserId(){
        return 1234;
    }
    
    private void refresh() {
        try {
            FavorisList.clear();
            query = "SELECT * FROM `Favoris`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getInt("IdUser") == getUserId()){
                    FavorisList.add(new Favoris(
                        resultSet.getInt("IdProduit"),
                        resultSet.getInt("IdUser")));
                    table1.setItems(ProduitList);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficheCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ProduitList.clear();
            query = "SELECT * FROM `produit`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                for(int i=0; i<FavorisList.size(); i++){
                    if(resultSet.getInt("numproduit") == FavorisList.get(i).getIdProduit()){
                        ProduitList.add(new Produit(resultSet.getInt("numproduit"),
                        resultSet.getString("nomproduit"),
                        resultSet.getInt("quantite"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("image"),
                        resultSet.getString("cat_name")));
                        table1.setItems(ProduitList);
                        break;
                    }
                }   
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficheCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() {
        connection = DataSource.getInstance().getcnx();
        refresh();
        idprod.setCellValueFactory(new PropertyValueFactory<>("numproduit"));
        nomprod.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
        quanprod.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixprod.setCellValueFactory(new PropertyValueFactory<>("prix"));
        catprod.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
        table1.setItems(ProduitList);
    }
    
}
