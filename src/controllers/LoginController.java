package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.security.crypto.bcrypt.BCrypt;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CrudUser;
import utils.SendMail;


public class LoginController implements Initializable {
	@FXML
	public TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button btnlogin;
	@FXML
	private Label btnregister;
	
	public static int  codem;
	public static String user;
    public static String pass;
	public static String passtest;
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/NewPassword.fxml"));
        NewPasswordController ircc = loader.getController();
        String s="a";
        if (!ircc.mail.equals(s)){
            username.setText(ircc.mail);
        }
	}
	
	@FXML
    void motdepasseoublie(ActionEvent event) throws MessagingException, IOException {
		user = username.getText();
        CrudUser sc = new CrudUser();
   		
        Random r = new Random ();
        codem =r.nextInt(9999-1000+1);
        //System.out.println(codem);
               
        if(isValidEmailAddress(user)){
        	SendMail.send(username.getText(), codem);
        	FXMLLoader loader = new FXMLLoader();
        	username.getScene().getWindow().hide();
        	Stage prStage = new Stage();
        	loader.setLocation(getClass().getResource("../views/Motdepasseoublie.fxml"));
        	Parent root = loader.load();
        	Scene scene = new Scene(root);
        	prStage.setScene(scene);
        	prStage.setResizable(false);
        	prStage.show();}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Adresse Email Non Valide !");
            alert.showAndWait();
        }
    }
	
	@FXML
	public void register(MouseEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
        btnregister.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/Registration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
	}
	// Event Listener on Button[#btnlogin].onAction
	@FXML
	public void login(ActionEvent event) throws IOException {
		user = username.getText();
        pass = password.getText();
        CrudUser sc = new CrudUser();
        
        if (sc.login(user, pass)) {
        	String status = sc.getStatusbyCin(sc.getCinbymail(user));
            String role = sc.getRolebyCin(sc.getCinbymail(user));
            if(status.equals("DESACTIVE")) {
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerte");
                alert.setHeaderText(null);
                alert.setContentText("Votre compte est desactiver !");
                alert.showAndWait();
            }
            else if (role.equals("ROLE_USER")) {
                FXMLLoader loader = new FXMLLoader();
                btnlogin.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("../views/AccueilUser.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
            } else if(role.equals("ROLE_ORGANISATEUR")){
                FXMLLoader loader = new FXMLLoader();
                
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource(""));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
                
            }  else if (role.equals("ROLE_ADMIN")){
                FXMLLoader loader = new FXMLLoader();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("../views/AccueilUser.fxml"));
                btnlogin.getScene().getWindow().hide();
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
            }
        }  else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Verifiez Vos Coordonnees !");
            alert.showAndWait();
        }
	}



	
}
