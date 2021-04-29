package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.CrudUser;
import utils.CrpytDecPassword;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

public class UserDetailsController implements Initializable {
	@FXML
    private TableView<User> detailsuser;
	@FXML
    private TextField tfsearch;
	@FXML
	private TableColumn<User, Integer> colcin;
	@FXML
	private TableColumn<User, String> colnom;
	@FXML
	private TableColumn<User, String> colprenom;
	@FXML
	private TableColumn<User, Integer> colage;
	@FXML
	private TableColumn<User, String> colsexe;
	@FXML
	private TableColumn<User, String> coladresse;
	@FXML
	private TableColumn<User, Integer> coltel;
	@FXML
	private TableColumn<User, String> colemail;
	@FXML
	private TableColumn<User, String> colroles;
	@FXML
	private TableColumn<User, String> colstatus;
	@FXML
    private Button tfupdateu;
	@FXML
    private Button tfrefresh;
	@FXML
    private Button tfdelete;
	@FXML
    private ImageView tflogout;

    @FXML
    private Label lbnom;

    @FXML
    private Label lbprenom;

    @FXML
    private Button btnaccueil;
    
    
	CrudUser cu = new CrudUser();
	ObservableList<User> List;
	ObservableList<User> dataliste;
	int index = -1;
	
	
	
	@FXML
    void accueil(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        lbnom.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/AccueilUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
	
	@FXML
    void logout(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        lbnom.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        LoginController irc = loader.getController();
        irc.username.setText(irc.user);
        prStage.show();
    }
	
	
	@FXML
    void refresh(ActionEvent event) {
		updatetable();
		search_user();
    }
	
	
	@FXML
	void search_user() {
		colcin.setCellValueFactory(new PropertyValueFactory<User,Integer>("cin"));
		colnom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
		colprenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
		colage.setCellValueFactory(new PropertyValueFactory<User,Integer>("age"));
		colsexe.setCellValueFactory(new PropertyValueFactory<User,String>("sexe"));
		coladresse.setCellValueFactory(new PropertyValueFactory<User,String>("adresse"));
		coltel.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
		colemail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
		colstatus.setCellValueFactory(new PropertyValueFactory<User,String>("status"));
	
	
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
		
	}
	public void updatetable() { 
		colcin.setCellValueFactory(new PropertyValueFactory<User,Integer>("cin"));
		colnom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
		colprenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
		colage.setCellValueFactory(new PropertyValueFactory<User,Integer>("age"));
		colsexe.setCellValueFactory(new PropertyValueFactory<User,String>("sexe"));
		coladresse.setCellValueFactory(new PropertyValueFactory<User,String>("adresse"));
		coltel.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
		colemail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
		colroles.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
		colstatus.setCellValueFactory(new PropertyValueFactory<User,String>("status"));
		
		List = cu.afficher();
		
		detailsuser.setItems(List);
		search_user();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updatetable();
		search_user();
		
		try {
            CrudUser su = new CrudUser();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail = irc.user;
            String mdp = irc.pass;
            String roles = su.getRolebyCin(su.getCinbymail(mail));
            lbnom.setText(su.getNombyCin(su.getCinbymail(mail)));
            lbprenom.setText(su.getPrenombyCin(su.getCinbymail(mail)));
        
        } catch (IOException ex) {
            Logger.getLogger(AccueilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
	}
	
	@FXML
    void updateuser(ActionEvent event) throws IOException {
		index = detailsuser.getSelectionModel().getSelectedIndex();
		if(index <= -1 ) {
			return ;
		}
		tfdelete.getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
        
        Stage prStage = new Stage();
        
        loader.setLocation(getClass().getResource("../views/EditUser.fxml"));
        Parent root = loader.load();
        prStage.setTitle("edit user");
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        
        prStage.setResizable(false);
        
        prStage.show();
        
        
        EditUserController edc = loader.getController();
        edc.setTfemail(colemail.getCellData(index).toString());
        edc.setTfcin(colcin.getCellData(index));
        edc.setTfnom(colnom.getCellData(index).toString());
        edc.setTfprenom(colprenom.getCellData(index).toString());
        edc.setTfage(colage.getCellData(index));
        edc.setTfsexe(colsexe.getCellData(index).toString());
        edc.setTfadresse(coladresse.getCellData(index).toString());
        edc.setTftelephone(coltel.getCellData(index));
        edc.setTfroles(colroles.getCellData(index).toString());
        edc.setTfstatus(colstatus.getCellData(index).toString());
        
        
        search_user();
    }
	
	@FXML
    void delete(ActionEvent event) {
		
		  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	      alert.setTitle("Supprimer un compte");
	      alert.setHeaderText("Vous voulez vraiment supprimer ce compte ?");
	      alert.setContentText(null);
	 
	      Optional<ButtonType> option = alert.showAndWait();
	 
	      if (option.get() == null) {
	         this.tfdelete.setText("No selection!");
	      } else if (option.get() == ButtonType.OK) {
	    	cu.supprimer(detailsuser.getSelectionModel().getSelectedItem().getCin());
	  		updatetable();
	  		search_user();
	        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
	        alerte.setTitle("Alerte");
	        alerte.setHeaderText(null);
	        alerte.setContentText("Le compte a ete supprime !");
	        alerte.showAndWait();
	        
	      } else if (option.get() == ButtonType.CANCEL) {
	         
	      } else {
	         this.tfdelete.setText("-");
	      }
		
		

	}
	
}
