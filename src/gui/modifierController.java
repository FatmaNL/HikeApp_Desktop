/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.commande;
import services.servicecommande;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class modifierController  {

    @FXML
    private TextField tfRef;
    @FXML
    private TextField tfEtat;
    
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     * @param tfRef
     */
       public void setTfRef(String tfRef) {
        this.tfRef.setText(tfRef);
    }

    public void setTfEtat(String tfEtat) {
        this.tfEtat.setText(tfEtat);
    }

   

    @FXML
    private void modifier(ActionEvent event) {
        commande u = new commande(tfRef.getText(),tfEtat.getText());
        	u.setRefcommande(tfRef.getText());
                u.setEtat(tfEtat.getText());

            servicecommande sc = new servicecommande();
            
            sc.modifier(u,tfRef.getText());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(" Modification avec success !");
            alert.showAndWait();
            
    }

 
    
}
