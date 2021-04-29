/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Transport;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceTransport;

/**
 * FXML Controller class
 *
 * @author Fatma NL
 */
public class AddTransportController implements Initializable {

    public static String selectedTransport = "";

    @FXML
    private TextField tfType;
    @FXML
    private TextField tfVolume;
    @FXML
    private Button btnAjout;
    @FXML
    private TableView<Transport> tableview;
    @FXML
    private TableColumn<Transport, String> colType;
    @FXML
    private TableColumn<Transport, Integer> colVolume;
    @FXML
    private TableColumn<Transport, Integer> colNbTransports;
    @FXML
    private TextField tfNbTransports;
    @FXML
    private TextField tfrechercher;
    @FXML
    private Button btnContacter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnExporter;
    @FXML
    private TextField tfid;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Transport t = tableview.getSelectionModel().getSelectedItem();
        showTransport();
        tfid.setDisable(true);
    }

    @FXML
    private void AjouterTransport(ActionEvent event) {
        if (((tfType.getText().isEmpty() || tfVolume.getText().isEmpty() || tfNbTransports.getText().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Vérifier les champs vides");

            alert.showAndWait();
        } else {
            ServiceTransport ser = new ServiceTransport();
            Transport t = new Transport();
            t.setType(tfType.getText());
            t.setVolumemax(parseInt(tfVolume.getText()));
            t.setNombre_transports(parseInt(tfNbTransports.getText()));
            ser.ajouter(t);

            showTransport();

        }

    }

    private void search(ActionEvent event) {
        Transport t = new Transport();
        colType.setCellValueFactory(new PropertyValueFactory<Transport, String>("type"));
        colVolume.setCellValueFactory(new PropertyValueFactory<Transport, Integer>("volumemax"));
        colNbTransports.setCellValueFactory(new PropertyValueFactory<Transport, Integer>("nombre_transports"));
        //colid.setCellValueFactory(new PropertyValueFactory<Transport, Integer>("idransport"));

        ObservableList<Transport> dataList;
        ServiceTransport ser = new ServiceTransport();
        dataList = ser.getList();

        tableview.setItems(dataList);

    }

    public void showTransport() {
        ServiceTransport ser = new ServiceTransport();

        ObservableList<Transport> list = ser.getList();
        colType.setCellValueFactory(new PropertyValueFactory<Transport, String>("type"));
        colVolume.setCellValueFactory(new PropertyValueFactory<Transport, Integer>("volumemax"));
        colNbTransports.setCellValueFactory(new PropertyValueFactory<Transport, Integer>("nombre_transports"));
        //colid.setCellValueFactory(new PropertyValueFactory<Transport, Integer>("idransport"));
        tableview.setItems(list);

        FilteredList<Transport> filteredData = new FilteredList<>(list, b -> true);
        tfrechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Transport cattype) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (cattype.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else {
                    return false;
                }
            });
        });
        SortedList<Transport> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }

    @FXML
    private void ModifierTransport(ActionEvent event) {

        if (((tfType.getText().isEmpty() || tfVolume.getText().isEmpty() || tfNbTransports.getText().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Vérifier les champs vides");

            alert.showAndWait();
        } else {
            Transport t = tableview.getSelectionModel().getSelectedItem();
            if (t == null) {

                System.out.println("Veillez choisir un Transport");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("choisir Transport");
                alert.setHeaderText(null);
                alert.setContentText("Veillez choisir le transport à modifier !");

                alert.showAndWait();

            } else {
                ServiceTransport sc = new ServiceTransport();
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Modifier Transport ..");
                    alert.setHeaderText(null);
                    alert.setContentText("Etes-vous sûr que vous voulez modifier! " + t.getType());
                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        t.setType(tfType.getText());
                        t.setVolumemax(parseInt(tfVolume.getText()));
                        t.setNombre_transports(parseInt(tfNbTransports.getText()));

                        sc.modifier(t);
                        
                        showTransport();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            showTransport();
        }
    }

    @FXML
    private void selected(javafx.scene.input.MouseEvent event) {

        Transport t = tableview.getSelectionModel().getSelectedItem();

        tfType.setText(t.getType());
        tfVolume.setText(String.valueOf(t.getVolumemax()));
        tfNbTransports.setText(String.valueOf(t.getNombre_transports()));
        tfid.setText(String.valueOf(t.getId()));
        System.out.println("id = " + t.getId() + " type= " + t.getType());
    }

    @FXML
    private void Mail(ActionEvent event) {
        try {
            selectedTransport = (tfType.getText());
            Stage logp = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("mailing.fxml"));
            Scene scene = new Scene(root);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void SupprimerTransport(ActionEvent event) {
        Transport t = tableview.getSelectionModel().getSelectedItem();
        if (t == null) {

            System.out.println("Veillez choisir un Transport");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choisir un Transport");
            alert.setHeaderText(null);
            alert.setContentText("Veillez choisir un Transport à supprimer !");

            alert.showAndWait();

        } else {
            ServiceTransport sc = new ServiceTransport();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Transport ..");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sûr que vous voulez supprimer " + t.getType() + "?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    sc.supprimer(t);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Supprimer Transport");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Le Transport est supprimée");

                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        showTransport();
    }

    @FXML
    private void Exporter(ActionEvent event) {
    }

}
