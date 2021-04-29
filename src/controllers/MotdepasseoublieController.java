package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CrudUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;

public class MotdepasseoublieController implements Initializable {
	@FXML
    private Label r;
	@FXML
	private Button btnenvoyer;
	@FXML
	private TextField tfcode;
	@FXML
	private Button btnannuler;
	public int code;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/Login.fxml"));
            LoginController ircc = loader.getController();

            code = ircc.codem;

        } catch (Exception ex) {
            Logger.getLogger(MotdepasseoublieController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	// Event Listener on Button[#btnenvoyer].onAction
	@FXML
	public void envoyer(ActionEvent event) throws IOException {
		int codex = Integer.parseInt(tfcode.getText());
        CrudUser sc = new CrudUser();
        String x="x";
        if (tfcode.getText().toString().equals(x)){
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez taper le code de Verification !");
            alert.showAndWait();
        }
        
        else if (code == codex) {
            
            FXMLLoader loader = new FXMLLoader();
            r.getScene().getWindow().hide();
            Stage prStage = new Stage();
            loader.setLocation(getClass().getResource("../views/NewPassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Code incorrecte !");
            alert.showAndWait();
        
    }
	}
	// Event Listener on Button[#btnannuler].onAction
	@FXML
	public void annuler(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        tfcode.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
	}
	
}
