/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Evenement;
import entities.Sentier;
import entities.Transport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import services.ServiceEvenement;
import services.ServiceSentier;
import services.ServiceTransport;

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
    private DatePicker tfDate;
    @FXML
    private TextField tfDuree;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfContact;
    @FXML
    private TextArea taInfos;
    @FXML
    private ComboBox<String> tfType;
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
    private TableColumn<Evenement, Date> colDate;
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
    @FXML
    private Label lType;
    @FXML
    private Label lCircuit;
    @FXML
    private ComboBox<Transport> cbtransport;
    @FXML
    private Label lSentiers;
    @FXML
    private ListView<Sentier> lvsentier;

    
    @FXML
    private Button nvTransport;
    @FXML
    private Button nvSentier;
    @FXML
    private Button btnimprimer;
    
    //ObservableList<Transport> TransportList = FXCollections.observableArrayList();
    ObservableList<Transport> options;
    ObservableList<Sentier> sentierOptions;
    DatePicker date;
    public static final String dest ="result/event.pdf";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Evenement e = tableview.getSelectionModel().getSelectedItem();
        showEvenement();
        //File file = new File();
        //file.getParentFile().mkdirs();

        try {
            listeTrasport();
            listeSentier();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<String> listtype = FXCollections.observableArrayList("Randonnee", "Camping");
        tfType.setItems(listtype);
        
        
    }

    public void listeTrasport() throws SQLException {
        ServiceTransport sc = new ServiceTransport();
        List<Transport> listeTransport = sc.getList();

        options = FXCollections.observableArrayList(listeTransport).sorted();
        cbtransport.setItems(options);
    }

    public void listeSentier() throws SQLException {
        ServiceSentier sc = new ServiceSentier();
        List<Sentier> listeSentier = sc.getList();

        sentierOptions = FXCollections.observableArrayList(listeSentier).sorted();
        lvsentier.setItems(sentierOptions);

        lvsentier.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void selected(MouseEvent event) {
        Evenement e = tableview.getSelectionModel().getSelectedItem();

        tfNom.setText(e.getNomevenement());
        tfDepart.setText(e.getDepart());
        tfDestination.setText(e.getDestination());
        tfParticipants.setText(String.valueOf(e.getNbparticipant()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        tfDate.setValue(LocalDate.parse(e.getDateevenement(), formatter));
        tfDuree.setText(String.valueOf(e.getDuree()));
        tfPrix.setText(String.valueOf(e.getPrix()));
        taProgramme.setText(e.getProgramme());
        tfContact.setText(e.getContact());
        taInfos.setText(e.getInfos());
        tfType.setValue(e.getType());
        tfCircuit.setText(e.getCircuit());

        int idTransport = e.getIdTransport();
        Transport transport = options.filtered(t -> t.getId() == idTransport)
                .get(0);

        SingleSelectionModel<Transport> selection = cbtransport.getSelectionModel();
        selection.select(transport);

        System.out.println("id = " + e.getId() + " nom= " + e.getNomevenement());
    }

    @FXML
    private void AjouterEvenement(ActionEvent event) {
        if (((tfNom.getText().isEmpty() || tfDepart.getText().isEmpty() || tfDestination.getText().isEmpty()
                || tfParticipants.getText().isEmpty() || tfDuree.getText().isEmpty()
                || tfPrix.getText().isEmpty() || taProgramme.getText().isEmpty() || tfContact.getText().isEmpty()
                || taInfos.getText().isEmpty() || tfType.getValue().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Vérifier les champs vides");

            alert.showAndWait();
        } else {
            ServiceEvenement ser = new ServiceEvenement();
            Evenement e = new Evenement();
            e.setNomevenement(tfNom.getText());
            e.setDepart(tfDepart.getText());
            e.setDestination(tfDestination.getText());
            e.setNbparticipant(parseInt(tfParticipants.getText()));
            e.setDateevenement(tfDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            e.setDuree(parseInt(tfDuree.getText()));
            e.setPrix(parseDouble(tfPrix.getText()));
            e.setProgramme(taProgramme.getText());
            e.setContact(tfContact.getText());
            e.setInfos(taInfos.getText());
            e.setType(tfType.getValue());
            e.setCircuit(tfCircuit.getText());

            SingleSelectionModel<Transport> selectionModel = cbtransport.getSelectionModel();
            Transport transport = selectionModel.getSelectedItem();
            e.setIdTransport(transport.getId());

            ser.ajouter(e);

            showEvenement();

        }
    }

    @FXML
    private void ModifierEvenement(ActionEvent event) {
        if (((tfNom.getText().isEmpty() || tfDepart.getText().isEmpty() || tfDestination.getText().isEmpty()
                || tfParticipants.getText().isEmpty() || tfDuree.getText().isEmpty()
                || tfPrix.getText().isEmpty() || taProgramme.getText().isEmpty() || tfContact.getText().isEmpty()
                || taInfos.getText().isEmpty() || tfType.getValue().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Vérifier les champs vides");

            alert.showAndWait();
        } else {
            Evenement e = tableview.getSelectionModel().getSelectedItem();
            if (e == null) {

                System.out.println("Veillez choisir un Evenement");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("choisir Evenement");
                alert.setHeaderText(null);
                alert.setContentText("Veillez choisir l'Evenement à modifier !");

                alert.showAndWait();

            } else {
                ServiceEvenement sc = new ServiceEvenement();
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Modifier Evenement ..");
                    alert.setHeaderText(null);
                    alert.setContentText("Etes-vous sûr que vous voulez modifier! " + e.getNomevenement());
                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        e.setNomevenement(tfNom.getText());
                        e.setDepart(tfDepart.getText());
                        e.setDestination(tfDestination.getText());
                        e.setNbparticipant(parseInt(tfParticipants.getText()));
                        e.setDateevenement(tfDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        e.setDuree(parseInt(tfDuree.getText()));
                        e.setPrix(parseDouble(tfPrix.getText()));
                        e.setProgramme(taProgramme.getText());
                        e.setContact(tfContact.getText());
                        e.setInfos(taInfos.getText());
                        e.setType(tfType.getValue());
                        e.setCircuit(tfCircuit.getText());

                        SingleSelectionModel<Transport> selectionModel = cbtransport.getSelectionModel();
                        Transport transport = selectionModel.getSelectedItem();
                        e.setIdTransport(transport.getId());

                        sc.modifier(e);

                        showEvenement();
                    }
                } catch (Exception f) {
                    f.printStackTrace();
                }

            }
            showEvenement();
        }
    }

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
        Evenement e = tableview.getSelectionModel().getSelectedItem();
        if (e == null) {

            System.out.println("Veillez choisir un Evenement");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choisir un Evenement");
            alert.setHeaderText(null);
            alert.setContentText("Veillez choisir un Evenement à supprimer !");

            alert.showAndWait();

        } else {
            ServiceEvenement sc = new ServiceEvenement();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Evenement ..");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sûr que vous voulez supprimer " + e.getNomevenement() + "?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    sc.supprimer(e);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Supprimer Evenement");
                    alert1.setHeaderText(null);
                    alert1.setContentText("L'Evenement est supprimé");

                    alert1.showAndWait();
                }
            } catch (Exception f) {
                f.printStackTrace();
            }

        }
        showEvenement();
    }

    @FXML
    private void Inviter(ActionEvent event) {
        try {
            //selectedTransport = (tfType.getText());
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
    private void Exporter(ActionEvent event) {
        Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
         document.open();
         document.add(new Paragraph("A Hello World PDF document."));
         document.close();
         writer.close();
        System.out.println("pdf!");
      } catch (DocumentException | FileNotFoundException e)
      {
      }
//        String path = "";
//        JFileChooser j= new JFileChooser();
//        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        path=j.getSelectedFile().getPath();
//        Document doc = new Document();
//        try {
//            PdfWriter.getInstance(doc, new FileOutputStream(path+"abc.pdf"));
//            doc.open();
//            PdfPTable tab = new PdfPTable(5);
//            tab.addCell("Evenement");
//            tab.addCell("Depart");
//            tab.addCell("Destination");
//            tab.addCell("Participants");
//            tab.addCell("Prix");
//            for (int i = 0; i < tableview.getrow; i++) {
//
//            }
//        } catch (Exception e) {
//        }
//        try {
//           //Class.forName("com.mysql.jdbc.Driver");
//               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev?serverTimezone=UTC", "root", "");
//            Statement stmt = con.createStatement();
//                    /* Define the SQL query */
//                    ResultSet query_set = stmt.executeQuery("SELECT *From evenement");
//                    /* Step-2: Initialize PDF documents - logical objects */
//                    Document my_pdf_report = new Document();
//                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream("mesevenements.pdf"));
//                    my_pdf_report.open();
//                    //we have four columns in our table
//                    PdfPTable my_report_table = new PdfPTable(4);
//                    //create a cell object
//                    PdfPCell table_cell;
//
//                    while (query_set.next()) {
//                                    String dept_id = query_set.getString("nomevenement");
//                                    table_cell=new PdfPCell(new Phrase(dept_id));
//                                    my_report_table.addCell(table_cell);
//                                    String dept_name=query_set.getString("depart");
//                                    table_cell=new PdfPCell(new Phrase(dept_name));
//                                    my_report_table.addCell(table_cell);
//                                    String manager_id=query_set.getString("destination");
//                                    table_cell=new PdfPCell(new Phrase(manager_id));
//                                    my_report_table.addCell(table_cell);
//                                    String location_id=query_set.getString("infos");
//                                    table_cell=new PdfPCell(new Phrase(location_id));
//                                    my_report_table.addCell(table_cell);
//                                    }
//                    /* Attach report table to PDF */
//                    my_pdf_report.add(my_report_table);
//                    my_pdf_report.close();
//
//                    /* Close all DB related objects */
//                    query_set.close();
//                    stmt.close();
//                    con.close();
//
//                    System.out.println("pdf gen!");
//
//
//
//    } catch (FileNotFoundException e) {
//    // TODO Auto-generated catch block
//    e.printStackTrace();
//    } catch (DocumentException e) {
//    // TODO Auto-generated catch block
//    e.printStackTrace();
//    }
        
//        String path = "";
//        JFileChooser j= new JFileChooser();
//        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        path=j.getSelectedFile().getPath();
//        Document doc = new Document();
//        try {
//            PdfWriter.getInstance(doc, new FileOutputStream(path+"abc.pdf"));
//            doc.open();
//            PdfPTable tab = new PdfPTable(5);
//            tab.addCell("Evenement");
//            tab.addCell("Depart");
//            tab.addCell("Destination");
//            tab.addCell("Participants");
//            tab.addCell("Prix");
//            for (int i = 0; i < tableview.getrow; i++) {
//                
//            }
//        } catch (Exception e) {
//        }

           
//        try {
//           //Class.forName("com.mysql.jdbc.Driver");
//               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev?serverTimezone=UTC", "root", "");
//            Statement stmt = con.createStatement();
//                    /* Define the SQL query */
//                    ResultSet query_set = stmt.executeQuery("SELECT *From evenement");
//                    /* Step-2: Initialize PDF documents - logical objects */
//                    Document my_pdf_report = new Document();
//                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream("mesevenements.pdf"));
//                    my_pdf_report.open();            
//                    //we have four columns in our table
//                    PdfPTable my_report_table = new PdfPTable(4);
//                    //create a cell object
//                    PdfPCell table_cell;
//
//                    while (query_set.next()) {                
//                                    String dept_id = query_set.getString("nomevenement");
//                                    table_cell=new PdfPCell(new Phrase(dept_id));
//                                    my_report_table.addCell(table_cell);
//                                    String dept_name=query_set.getString("depart");
//                                    table_cell=new PdfPCell(new Phrase(dept_name));
//                                    my_report_table.addCell(table_cell);
//                                    String manager_id=query_set.getString("destination");
//                                    table_cell=new PdfPCell(new Phrase(manager_id));
//                                    my_report_table.addCell(table_cell);
//                                    String location_id=query_set.getString("infos");
//                                    table_cell=new PdfPCell(new Phrase(location_id));
//                                    my_report_table.addCell(table_cell);
//                                    }
//                    /* Attach report table to PDF */
//                    my_pdf_report.add(my_report_table);                       
//                    my_pdf_report.close();
//
//                    /* Close all DB related objects */
//                    query_set.close();
//                    stmt.close(); 
//                    con.close();       
//                    
//                    System.out.println("pdf gen!");
//
//
//
//    } catch (FileNotFoundException e) {
//    // TODO Auto-generated catch block
//    e.printStackTrace();
//    } catch (DocumentException e) {
//    // TODO Auto-generated catch block
//    e.printStackTrace();
//    }
        
        
    }
        

    private void search(ActionEvent event) {
        Evenement e = new Evenement();
        colNom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nomevenement"));
        colDepart.setCellValueFactory(new PropertyValueFactory<Evenement, String>("depart"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Evenement, String>("destination"));
        colParticipants.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbparticipant"));
        colDate.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("dateevnement"));
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
        colDate.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("dateevnement"));
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

    @FXML
    private void selectTransport(ActionEvent event) {

    }

    @FXML
    private void selectType(ActionEvent event) {
        if (tfType.getValue().equals("Camping")) {
            lCircuit.setVisible(false);
            tfCircuit.setVisible(false);
            lSentiers.setVisible(false);
            lvsentier.setVisible(false);
        } else if (tfType.getValue().equals("Randonnee")) {
            lCircuit.setVisible(true);
            tfCircuit.setVisible(true);
            lSentiers.setVisible(true);
            lvsentier.setVisible(true);
        }
    }

    @FXML
    private void nouveauTransport(ActionEvent event) {
        try {
            Stage logp = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("AddTransport.fxml"));
            Scene scene = new Scene(root);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void nouveauSentier(ActionEvent event) {
        try {
            Stage logp = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Sentier.fxml"));
            Scene scene = new Scene(root);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void imprimer(ActionEvent event) {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);

            Node root = this.tableview;

            job.printPage(root);

            job.endJob();
        }
    }

}
