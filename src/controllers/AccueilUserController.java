package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CrudUser;

public class AccueilUserController implements Initializable {
	@FXML
	private Label lbnom;
	@FXML
	private Label lbprenom;
	@FXML
    private Button btnediter;
	@FXML
    private Button btngestion;
	@FXML
    private ImageView tflogout;
	
	
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
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
            CrudUser su = new CrudUser();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail = irc.user;
            String mdp = irc.pass;
            String roles = su.getRolebyCin(su.getCinbymail(mail));
            
            if(roles.equals("ROLE_USER")) {
            	btngestion.setVisible(false);
            }
            lbnom.setText(su.getNombyCin(su.getCinbymail(mail)));
            lbprenom.setText(su.getPrenombyCin(su.getCinbymail(mail)));
        
        } catch (IOException ex) {
            Logger.getLogger(AccueilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	
	@FXML
    void editer(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        lbnom.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/EditerProfile.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
	
	
	@FXML
    void gestionutilisateur(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        lbnom.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../views/UserDetails.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
	
	
}
