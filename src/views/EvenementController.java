/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Evenement;
import entities.Sentier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServiceEvenement;
import services.ServiceSentier;

/**
 * FXML Controller class
 *
 * @author Fatma NL
 */
public class EvenementController implements Initializable {

    public static String selectedEvenement = "";
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDepart;
    @FXML
    private TextField tfDestination;
    @FXML
    private TextField tfParticipants;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfDuree;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfContact;
    @FXML
    private TextArea taInfos;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfCircuit;
    @FXML
    private TextArea taProgramme;
    @FXML
    private TableView<Evenement> tableview;
    @FXML
    private TableColumn<Evenement, String> colNom;
    @FXML
    private TableColumn<Evenement, String> colDepart;
    @FXML
    private TableColumn<Evenement, String> colDestination;
    @FXML
    private TableColumn<Evenement, Integer> colParticipants;
    @FXML
    private TableColumn<Evenement, String> colDate;
    @FXML
    private TableColumn<Evenement, Integer> colDuree;
    @FXML
    private TableColumn<Evenement, String> colProgramme;
    @FXML
    private TableColumn<Evenement, Double> colPrix;
    @FXML
    private TableColumn<Evenement, String> colContact;
    @FXML
    private TableColumn<Evenement, String> colInfos;
    @FXML
    private TableColumn<Evenement, String> colType;
    @FXML
    private TableColumn<Evenement, String> colCircuit;
    @FXML
    private TextField tfRechercher;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnInviter;
    @FXML
    private Button btnExporter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Evenement e = tableview.getSelectionModel().getSelectedItem();
        showEvenement();
    }    

    @FXML
    private void selected(MouseEvent event) {
    }

    @FXML
    private void AjouterEvenement(ActionEvent event) {
    }

    @FXML
    private void ModifierEvenement(ActionEvent event) {
    }

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
    }

    @FXML
    private void Inviter(ActionEvent event) {
    }

    @FXML
    private void Exporter(ActionEvent event) {
    }
    
    private void search(ActionEvent event) {
        Evenement e = new Evenement();
        colNom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nomevenement"));
        colDepart.setCellValueFactory(new PropertyValueFactory<Evenement, String>("depart"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Evenement, String>("destination"));
        colParticipants.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbparticipant"));
        colDate.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateevnement"));
        colDuree.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("duree"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Evenement, Double>("prix"));
        colProgramme.setCellValueFactory(new PropertyValueFactory<Evenement, String>("programme"));
        colContact.setCellValueFactory(new PropertyValueFactory<Evenement, String>("contact"));
        colInfos.setCellValueFactory(new PropertyValueFactory<Evenement, String>("infos"));
        colType.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
        colCircuit.setCellValueFactory(new PropertyValueFactory<Evenement, String>("circuit"));

        ObservableList<Evenement> dataList;
        ServiceEvenement ser = new ServiceEvenement();
        dataList = ser.getList();

        tableview.setItems(dataList);

    }

    public void showEvenement() {
        ServiceEvenement ser = new ServiceEvenement();

        ObservableList<Evenement> list = ser.getList();
        colNom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nomevenement"));
        colDepart.setCellValueFactory(new PropertyValueFactory<Evenement, String>("depart"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Evenement, String>("destination"));
        colParticipants.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbparticipant"));
        colDate.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateevnement"));
        colDuree.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("duree"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Evenement, Double>("prix"));
        colProgramme.setCellValueFactory(new PropertyValueFactory<Evenement, String>("programme"));
        colContact.setCellValueFactory(new PropertyValueFactory<Evenement, String>("contact"));
        colInfos.setCellValueFactory(new PropertyValueFactory<Evenement, String>("infos"));
        colType.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
        colCircuit.setCellValueFactory(new PropertyValueFactory<Evenement, String>("circuit"));
        tableview.setItems(list);

        FilteredList<Evenement> filteredData = new FilteredList<>(list, b -> true);
        tfRechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Evenement cattype) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (cattype.getNomevenement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else {
                    return false;
                }
            });
        });
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }
    
}
