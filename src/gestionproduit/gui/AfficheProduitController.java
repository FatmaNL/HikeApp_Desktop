/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gestionproduit.models.Categorie;
import gestionproduit.models.Produit;
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

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficheProduitController implements Initializable {

    @FXML
    private TableView<Produit> table;

    @FXML
    private TableColumn<Produit, String> nomprod;
    @FXML
    private TableColumn<Produit, String> quanprod;
    @FXML
    private TableColumn<Produit, String> prixprod;
    @FXML
    private TableColumn<Produit, String> catprod;
    @FXML
    private TableColumn<Produit, String> editprod;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produit produit = null;

    ObservableList<Produit> ProduitList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();
    }

    @FXML
    private void ajout(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
    }

    @FXML
    private void refresh() {
        try {
            ProduitList.clear();

            query = "SELECT * FROM `produit`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProduitList.add(new Produit(resultSet.getInt("numproduit"),
                        resultSet.getString("nomproduit"),
                        resultSet.getInt("quantite"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("image"),
                        resultSet.getString("cat_name")));
                table.setItems(ProduitList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AfficheCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDate() {
        connection = DataSource.getInstance().getcnx();
        refresh();


        nomprod.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
        quanprod.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixprod.setCellValueFactory(new PropertyValueFactory<>("prix"));
        catprod.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
        //add cell of button edit 
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFoctory = (TableColumn<Produit, String> param) -> {
            // make cell containing buttons
            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                produit = table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `produit` WHERE numproduit  =" + produit.getNumproduit();
                                connection = DataSource.getInstance().getcnx();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refresh();

                            } catch (SQLException ex) {
                                Logger.getLogger(AfficheCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            produit = table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("AjoutProduit.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AjoutProduitController addProduitController = loader.getController();
                            addProduitController.setUpdate(true);
                            addProduitController.setTextField(produit.getNumproduit(), produit.getNomproduit(), produit.getQuantite(), produit.getPrix());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                        refresh();
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editprod.setCellFactory(cellFoctory);
        table.setItems(ProduitList);
    }

}
