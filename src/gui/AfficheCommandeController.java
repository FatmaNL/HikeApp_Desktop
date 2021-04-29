/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.commande;
import services.servicecommande;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficheCommandeController implements Initializable {
   @FXML
    private TableView<commande> affch;
    @FXML
    private TableColumn<commande, String> ref;
    @FXML
    private TableColumn<commande, String> etat;
    @FXML
    private TableColumn<commande, Date> dtc;
    ObservableList<commande> List;
    servicecommande scc = new servicecommande();
    @FXML
    private Button supp;
      @FXML
    private Button ajt;
          @FXML
    private Button modf;
             @FXML
    private TextField rech;
          
            int index = -1;

    @FXML
    void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        affch.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../gui/AjoutCommande.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();

    }

    @FXML
    void supprimer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	      alert.setTitle("Supprimer une commande");
	      alert.setHeaderText("Vous voulez vraiment supprimer cette commande ?");
	      alert.setContentText(null);
	 
	      Optional<ButtonType> option = alert.showAndWait();
	 
	      if (option.get() == null) {
	         this.supp.setText("No selection!");
	      } else if (option.get() == ButtonType.OK) {
	    	scc.supprimer(affch.getSelectionModel().getSelectedItem().getRefcommande());
	  		updatetable();
	        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
	        alerte.setTitle("Alerte");
	        alerte.setHeaderText(null);
	        alerte.setContentText("La commande a éte supprimée !");
	        alerte.showAndWait();
	        
	      } else if (option.get() == ButtonType.CANCEL) {
	         
	      } else {
	         this.supp.setText("-");
	      }

    }
                @FXML
    void modifier(ActionEvent event) throws IOException {
index = affch.getSelectionModel().getSelectedIndex();
		if(index <= -1 ) {
			return ;
		}
		
		FXMLLoader loader = new FXMLLoader();
        
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../gui/modifier.fxml"));
        Parent root = loader.load();
        
        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        
        modifierController mdc = loader.getController();
        mdc.setTfRef(ref.getCellData(index));
        mdc.setTfEtat(etat.getCellData(index));
        updatetable();
        
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      updatetable();
        
    }    

    /* public void recherche(){
        ref.setCellValueFactory(new PropertyValueFactory<commande,String>("refcommande"));  
    
        dataliste = cu.afficher();
        detailsuser.setItems(dataliste);
        FilteredList<User> filterdata = new FilteredList<>(dataliste, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterdata.setPredicate(person -> {
                                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(person.getCin()).toLowerCase().indexOf(lowerCaseFilter ) != -1 ) {
                    return true; 
                } else if (person.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                }
                else if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                }
                else if(person.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) 
                        return true;
                else
                    return false;
            });
        });
        
         
        SortedList<User> sortedData = new SortedList<>(filterdata);
        sortedData.comparatorProperty().bind(detailsuser.comparatorProperty());
        detailsuser.setItems(sortedData);
        
    }*/
    public void updatetable() {
          ref.setCellValueFactory(new PropertyValueFactory<commande,String>("refcommande"));
        etat.setCellValueFactory(new PropertyValueFactory<commande,String>("etat"));
        dtc.setCellValueFactory(new PropertyValueFactory<commande,Date>("datecommande"));
        List = scc.afficher();
        affch.setItems(List);
        
    }
       @FXML
    private Button refresh;
          @FXML
    void refresh(ActionEvent event) {
        updatetable() ;

    }
       @FXML
    private Button fxoc;

     @FXML
    void filter(ActionEvent event) {
       
 ref.setCellValueFactory(new PropertyValueFactory<commande,String>("refcommande"));
        etat.setCellValueFactory(new PropertyValueFactory<commande,String>("etat"));
        dtc.setCellValueFactory(new PropertyValueFactory<commande,Date>("datecommande"));
  
        ObservableList<commande> ob = FXCollections.observableArrayList(scc.afficheroccasion());
        affch.setItems(ob);
    }
    
}
