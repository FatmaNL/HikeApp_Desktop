package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import services.CrudUser;
import utils.CrpytDecPassword;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ChoiceBox;

public class EditUserController implements Initializable {
	
	ObservableList<String> tfsexelist = FXCollections.observableArrayList("Homme", "Femme");
	ObservableList<String> tfroleslist = FXCollections.observableArrayList("ROLE_USER", "ROLE_ORGANISATEUR","ROLE_ADMIN");
	ObservableList<String> tfstatuslist = FXCollections.observableArrayList("ACTIVE","DESACTIVE");
	
	@FXML
	private TextField tfemail;
	@FXML
	private TextField tfcin;
	@FXML
	private TextField tfnom;
	@FXML
	private TextField tfprenom;
	@FXML
	private TextField tfage;
	@FXML
	private TextField tfadresse;
	@FXML
	private TextField tftelephone;
	@FXML
	private Button btnupdate;
	@FXML
	private Button btncancel;
	@FXML
	private ChoiceBox tfsexe;
	@FXML
	private ChoiceBox tfstatus;
	@FXML
	private ChoiceBox tfroles;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tfsexe.setItems(tfsexelist);
		tfroles.setItems(tfroleslist);
		tfstatus.setItems(tfstatuslist);
		
		
	}
	
	public TextField getTfemail() {
		return tfemail;
	}

	public void setTfemail(String email) {
		this.tfemail.setText(email);
	}

	public TextField getTfcin() {
		return tfcin;
	}

	public void setTfcin(Integer cin) {
		this.tfcin.setText(cin.toString());
	}

	public TextField getTfnom() {
		return tfnom;
	}

	public void setTfnom(String nom) {
		this.tfnom.setText(nom);
	}

	public TextField getTfprenom() {
		return tfprenom;
	}

	public void setTfprenom(String prenom) {
		this.tfprenom.setText(prenom);
	}

	public TextField getTfage() {
		return tfage;
	}

	public void setTfage(Integer age) {
		this.tfage.setText(age.toString());
	}

	public TextField getTfadresse() {
		return tfadresse;
	}

	public void setTfadresse(String adresse) {
		this.tfadresse.setText(adresse);
	}

	public TextField getTftelephone() {
		return tftelephone;
	}

	public void setTftelephone(Integer telephone) {
		this.tftelephone.setText(telephone.toString());
	}

	public ChoiceBox getTfsexe() {
		return tfsexe;
	}

	public void setTfsexe(String sexe) {
		this.tfsexe.setValue(sexe);
	}

	public ChoiceBox getTfstatus() {
		return tfstatus;
	}

	public void setTfstatus(String status) {
		this.tfstatus.setValue(status);
	}

	public ChoiceBox getTfroles() {
		return tfroles;
	}

	public void setTfroles(String roles) {
		this.tfroles.setValue(roles);
	}

	private static boolean valEmail(String email) {
		String emailregex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailpat = Pattern.compile(emailregex,java.util.regex.Pattern.CASE_INSENSITIVE);
		Matcher m = emailpat.matcher(email);
		return m.find();
	}
	
	// Event Listener on Button[#btnupdate].onAction
	@FXML
	public void update(ActionEvent event) throws IOException {
		if(tfemail.getText().isEmpty() || valEmail(tfemail.getText()) == false)
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer un email correct !");
        alert.showAndWait();}
        else if (tfcin.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre cin  !");
        alert.showAndWait();}
        else if (Integer.parseInt(tfcin.getText()) < 10000000 )
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer un cin correct !");
        alert.showAndWait();}
        else if (Integer.parseInt(tfcin.getText()) > 99999999 )
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer un cin correct !");
        alert.showAndWait();}
        else if (tfnom.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre nom !");
        alert.showAndWait();}
        else if (tfprenom.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre prenom !");
        alert.showAndWait();}
        else if (tfage.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre age !");
        alert.showAndWait();}
        else if (tfsexe.getValue().toString() == null)
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez choisir votre sexe !");
        alert.showAndWait();}
        else if (tfadresse.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre adresse !");
        alert.showAndWait();}
        else if (tftelephone.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre numero de telephone !");
        alert.showAndWait();}
        else
        {   User u = new User();
        	u.setEmail(tfemail.getText());
            u.setCin(Integer.parseInt(tfcin.getText()));
            u.setNom(tfnom.getText());	
            u.setPrenom(tfprenom.getText());
            u.setAge(Integer.parseInt(tfage.getText()));
            u.setSexe(tfsexe.getValue().toString());
            u.setAdresse(tfadresse.getText());
            u.setTel(Integer.parseInt(tftelephone.getText()));
            u.setRoles(tfroles.getValue().toString());
            u.setStatus(tfstatus.getValue().toString());
            CrudUser sc = new CrudUser();
            
            sc.modifier(u, sc.getCinbymail(tfemail.getText()));
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(" Modification avec success !");
            alert.showAndWait();
            
            
            FXMLLoader loader = new FXMLLoader();
            tfemail.getScene().getWindow().hide();
            Stage prStage = new Stage();
            loader.setLocation(getClass().getResource("../views/UserDetails.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
        }
		
	}
	// Event Listener on Button[#btncancel].onAction
	@FXML
	public void cancel(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        tfadresse.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/UserDetails.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
	}
	
}
