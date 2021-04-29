package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.User;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;

import javafx.scene.control.PasswordField;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CrudUser;
import utils.CrpytDecPassword;

public class EditerProfileController implements Initializable {
	@FXML
	private ImageView btnretour;
	@FXML
	private TextField tfnom;
	@FXML
	private TextField tfprenom;
	@FXML
	private TextField tfemail;
	@FXML
	private TextField tftelephone;
	@FXML
	private Button btnediter;
	@FXML
	private Button btnannuler;
	@FXML
	private PasswordField tfpassword;
	
	private FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
	private LoginController irc = loader.getController();
	private String email = irc.user;
	CrudUser sc = new CrudUser();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
            CrudUser su = new CrudUser();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail = irc.user;
            String mdp = irc.pass;
            tfnom.setText(su.getNombyCin(su.getCinbymail(mail)));
            tfprenom.setText(su.getPrenombyCin(su.getCinbymail(mail)));
            tfemail.setText(su.getMailbyCin(su.getCinbymail(mail)));
            tftelephone.setText(su.getTelbyCin(su.getCinbymail(mail)));
            tfpassword.setText("");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	
	private static boolean valEmail(String email) {
		String emailregex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailpat = Pattern.compile(emailregex,java.util.regex.Pattern.CASE_INSENSITIVE);
		Matcher m = emailpat.matcher(email);
		return m.find();
	}
	
	// Event Listener on ImageView[#btnretour].onMouseClicked
	@FXML
	public void retour(MouseEvent event) throws IOException {
		 FXMLLoader loader = new FXMLLoader();
	        tfnom.getScene().getWindow().hide();
	        Stage prStage = new Stage();
	        loader.setLocation(getClass().getResource("../views/AccueilUser.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        prStage.setScene(scene);
	        prStage.setResizable(false);
	        prStage.show();
	}
	// Event Listener on Button[#btnediter].onAction
	@FXML
	public void editer(ActionEvent event) throws IOException {
		
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
        else if (tfprenom.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre prenom !");
        alert.showAndWait();}
        else if (tftelephone.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre telephone !");
        alert.showAndWait();}
        else if (tfnom.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrer votre nom !");
        alert.showAndWait();}
        else if (tfemail.getText().equals(email)) {
		User c = new User();
        c.setNom(tfnom.getText());
        c.setPrenom(tfprenom.getText());
        c.setEmail(tfemail.getText());
        c.setTel(Integer.parseInt(tftelephone.getText()));
        c.setPassword(CrpytDecPassword.getEncoString(tfpassword.getText()));
        sc.modifieruser(c, sc.getCinbymail(email));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Modification avec success !");
        alert.showAndWait();
        
        FXMLLoader loader = new FXMLLoader();
        tfnom.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/AccueilUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        }
        else {
        	User c = new User();
            c.setNom(tfnom.getText());
            c.setPrenom(tfprenom.getText());
            c.setEmail(tfemail.getText());
            c.setTel(Integer.parseInt(tftelephone.getText()));
            c.setPassword(CrpytDecPassword.getEncoString(tfpassword.getText()));
            sc.modifieruser(c, sc.getCinbymail(email));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Modification avec success !");
            alert.showAndWait();
            
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
	// Event Listener on Button[#btnannuler].onAction
	@FXML
	public void annuler(ActionEvent event) throws IOException {
		 	FXMLLoader loader = new FXMLLoader();
	        tfnom.getScene().getWindow().hide();
	        Stage prStage = new Stage();
	        loader.setLocation(getClass().getResource("../views/AccueilUser.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        prStage.setScene(scene);
	        prStage.setResizable(false);
	        prStage.show();
	}
	
}
