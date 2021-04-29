package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.CrudUser;
import utils.CrpytDecPassword;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCrypt;

import entity.User;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class RegistrationController implements Initializable {

	ObservableList<String> tfsexelist = FXCollections.observableArrayList("Homme", "Femme");
	@FXML
	private TextField tfemail;
	@FXML
	private TextField tfpassword;
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
	private Button btnregister;
	@FXML
	private Button btncancel;
	@FXML
	private ChoiceBox tfsexe;

	private static boolean valEmail(String email) {
		String emailregex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailpat = Pattern.compile(emailregex,java.util.regex.Pattern.CASE_INSENSITIVE);
		Matcher m = emailpat.matcher(email);
		return m.find();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tfsexe.setItems(tfsexelist);
		
	}

	
	// Event Listener on Button[#btnregister].onAction
	@FXML
	public void register(ActionEvent event) throws IOException {
		if(tfemail.getText().isEmpty() || valEmail(tfemail.getText()) == false)
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer un email correct !");
        alert.showAndWait();}
        else if (tfpassword.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre password !");
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
        {  User u = new User();
        u.setEmail(tfemail.getText());
        String password = String.valueOf(tfpassword.getText());
        u.setPassword(CrpytDecPassword.getEncoString(password));
        u.setCin(Integer.parseInt(tfcin.getText()));
        u.setNom(tfnom.getText());
        u.setPrenom(tfprenom.getText());
        u.setAge(Integer.parseInt(tfage.getText()));
        u.setSexe(tfsexe.getValue().toString());
        u.setAdresse(tfadresse.getText());
        u.setTel(Integer.parseInt(tftelephone.getText()));

        CrudUser sc = new CrudUser();
        sc.ajouter(u);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Inscription Avec Success !");
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader();
        tfnom.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();}
	}

	// Event Listener on Button[#btncancel].onAction
	@FXML
	public void cancel(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
        tfnom.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
	}

	
}
