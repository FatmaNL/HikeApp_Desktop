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

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class NewPasswordController implements Initializable {
	@FXML
	private Label r;
	@FXML
	private Button btnenvoyer;
	@FXML
	private TextField tfpassword;
	@FXML
	private Button btnannuler;
	public static String mail="a";
	// Event Listener on Button[#btnenvoyer].onAction
	@FXML
	public void envoyer(ActionEvent event) throws IOException {
		if(tfpassword.getText().isEmpty()){  
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Champs vide !");
            alert.showAndWait();
        }
        else
        	
        { String newPass = CrpytDecPassword.getEncoString(tfpassword.getText());
        CrudUser sc = new CrudUser();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/Login.fxml"));
        LoginController ircc = loader.getController();
        mail=ircc.user;
        int id = sc.getCinbymail(mail);
       
        sc.setNewMotPass(id, newPass);
        FXMLLoader loaderr = new FXMLLoader();
        tfpassword.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loaderr.setLocation(getClass().getResource("../views/Login.fxml"));
        Parent root = loaderr.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);

        prStage.show();
        }
	}
	// Event Listener on Button[#btnannuler].onAction
	@FXML
	public void annuler(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        tfpassword.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/Motdepasseoublie.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
