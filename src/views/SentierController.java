/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Sentier;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServiceSentier;

/**
 * FXML Controller class
 *
 * @author Fatma NL
 */
public class SentierController implements Initializable {

    public static String selectedSentier = "";
    @FXML
    private Button btnAjout;
    @FXML
    private TableView<Sentier> tableview;
    @FXML
    private TableColumn<Sentier, String> colNom;
    @FXML
    private TableColumn<Sentier, String> colDuree;
    @FXML
    private TableColumn<Sentier, String> colDistance;
    @FXML
    private TableColumn<Sentier, String> colDifficulte;
    @FXML
    private TableColumn<Sentier, String> colDepart;
    @FXML
    private TableColumn<Sentier, String> colDestination;
    @FXML
    private TextField tfrechercher;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDuree;
    @FXML
    private TextField tfDistance;
    @FXML
    private TextField tfDifficulte;
    @FXML
    private TextField tfDepart;
    @FXML
    private TextField tfDestination;
    @FXML
    private TextField tfid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Sentier s = tableview.getSelectionModel().getSelectedItem();
        showSentier();
        tfid.setDisable(true);
    }

    @FXML
    private void AjouterSentier(ActionEvent event) {
        if (((tfNom.getText().isEmpty() || tfDuree.getText().isEmpty() || tfDistance.getText().isEmpty()
                || tfDifficulte.getText().isEmpty() || tfDepart.getText().isEmpty() || tfDestination.getText().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Vérifier les champs vides");

            alert.showAndWait();
        } else {
            ServiceSentier ser = new ServiceSentier();
            Sentier s = new Sentier();
            s.setNomsentier(tfNom.getText());
            s.setDuree(tfDuree.getText());
            s.setDistance(tfDistance.getText());
            s.setDifficulte(tfDifficulte.getText());
            s.setDepartsentier(tfDepart.getText());
            s.setDestinationsentier(tfDestination.getText());
            ser.ajouter(s);

            showSentier();

        }
    }

    @FXML
    private void selected(MouseEvent event) {
        Sentier s = tableview.getSelectionModel().getSelectedItem();

        tfNom.setText(s.getNomsentier());
        tfDuree.setText(s.getDuree());
        tfDistance.setText(s.getDistance());
        tfDifficulte.setText(s.getDifficulte());
        tfDepart.setText(s.getDepartsentier());
        tfDestination.setText(s.getDestinationsentier());
        tfid.setText(String.valueOf(s.getId()));
        System.out.println("id = " + s.getId() + " nom= " + s.getNomsentier());
    }

    @FXML
    private void ModifierSentier(ActionEvent event) {
        if (((tfNom.getText().isEmpty() || tfDuree.getText().isEmpty() || tfDistance.getText().isEmpty()
                || tfDifficulte.getText().isEmpty() || tfDepart.getText().isEmpty() || tfDestination.getText().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Vérifier les champs vides");

            alert.showAndWait();
        } else {
            Sentier s = tableview.getSelectionModel().getSelectedItem();
            if (s == null) {

                System.out.println("Veillez choisir un Sentier");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("choisir Sentier");
                alert.setHeaderText(null);
                alert.setContentText("Veillez choisir le sentier à modifier !");

                alert.showAndWait();

            } else {
                ServiceSentier sc = new ServiceSentier();
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Modifier Sentier ..");
                    alert.setHeaderText(null);
                    alert.setContentText("Etes-vous sûr que vous voulez modifier! " + s.getNomsentier());
                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        s.setNomsentier(tfNom.getText());
                        s.setDuree(tfDuree.getText());
                        s.setDistance(tfDistance.getText());
                        s.setDifficulte(tfDifficulte.getText());
                        s.setDepartsentier(tfDepart.getText());
                        s.setDestinationsentier(tfDestination.getText());

                        sc.modifier(s);

                        showSentier();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            showSentier();
        }
    }

    @FXML
    private void SupprimerSentier(ActionEvent event) {
        Sentier s = tableview.getSelectionModel().getSelectedItem();
        if (s == null) {

            System.out.println("Veillez choisir un Sentier");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choisir un Sentier");
            alert.setHeaderText(null);
            alert.setContentText("Veillez choisir un Sentier à supprimer !");

            alert.showAndWait();

        } else {
            ServiceSentier sc = new ServiceSentier();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Sentier ..");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sûr que vous voulez supprimer " + s.getNomsentier() + "?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    sc.supprimer(s);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Supprimer Sentier");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Le Sentier est supprimée");

                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        showSentier();
    }

    private void search(ActionEvent event) {
        Sentier s = new Sentier();
        colNom.setCellValueFactory(new PropertyValueFactory<Sentier, String>("nomsentier"));
        colDuree.setCellValueFactory(new PropertyValueFactory<Sentier, String>("duree"));
        colDistance.setCellValueFactory(new PropertyValueFactory<Sentier, String>("distance"));
        colDifficulte.setCellValueFactory(new PropertyValueFactory<Sentier, String>("difficulte"));
        colDepart.setCellValueFactory(new PropertyValueFactory<Sentier, String>("departsentier"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Sentier, String>("destinationsentier"));

        ObservableList<Sentier> dataList;
        ServiceSentier ser = new ServiceSentier();
        dataList = ser.getList();

        tableview.setItems(dataList);

    }

    public void showSentier() {
        ServiceSentier ser = new ServiceSentier();

        ObservableList<Sentier> list = ser.getList();
        colNom.setCellValueFactory(new PropertyValueFactory<Sentier, String>("nomsentier"));
        colDuree.setCellValueFactory(new PropertyValueFactory<Sentier, String>("duree"));
        colDistance.setCellValueFactory(new PropertyValueFactory<Sentier, String>("distance"));
        colDifficulte.setCellValueFactory(new PropertyValueFactory<Sentier, String>("difficulte"));
        colDepart.setCellValueFactory(new PropertyValueFactory<Sentier, String>("departsentier"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Sentier, String>("destinationsentier"));
        tableview.setItems(list);

        FilteredList<Sentier> filteredData = new FilteredList<>(list, b -> true);
        tfrechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Sentier cattype) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (cattype.getNomsentier().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else {
                    return false;
                }
            });
        });
        SortedList<Sentier> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }

}
