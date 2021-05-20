/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import models.commande;
import services.servicecommande;
import utils.JavaMailUtil;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutCommandeController implements Initializable {

    @FXML
    private TextField tfRef;
    @FXML
    private Button btn;
    @FXML
    private TextField tfEtat;
    @FXML
    private TextField tfDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCommande(ActionEvent event) throws IOException, MessagingException {
        if (tfRef.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"le champ est vide");
        }
        else
        {
        servicecommande sc;
        sc = new servicecommande();
        sc.ajouter(new commande(tfRef.getText() ,tfEtat.getText() ));
        JOptionPane.showMessageDialog(null,"commande ajoutée");
         FXMLLoader loader = new FXMLLoader();
        tfRef.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../gui/afficheCommande.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
JavaMailUtil.sendMail("heni.guirat2@gmail.com");

    }
    }
}
