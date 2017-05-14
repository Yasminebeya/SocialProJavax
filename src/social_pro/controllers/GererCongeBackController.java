/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static social_pro.controllers.ListeCongeController.id;
import social_pro.entites.Conge;
import social_pro.entites.User;
import social_pro.entites.fos_user;
import social_pro.service.CongeService;
import social_pro.service.UserService;

public class GererCongeBackController implements Initializable {

    @FXML
    private TableView<Conge> tableconge;

    @FXML
    private TableColumn<Conge, Date> DateP1;

    @FXML
    private TableColumn<Conge, Integer> NbjoursP1;

    @FXML
    private TableColumn<Conge, String> TypeP1;

    @FXML
    private TableColumn<Conge, String> RaisonP1;

    @FXML
    private TableColumn<Conge, String> etatP1;

    @FXML
    private Button addBtn;

    @FXML
    private TextField nbjP;

    @FXML
    private Button updateBtn;

    @FXML
    private Button suppBtn;

    @FXML
    private Button updateBtn1;

    @FXML
    private DatePicker dPdatedebut;

    @FXML
    private ComboBox ComboType;

    @FXML
    private TextArea TxtRaison;
    @FXML
    private Button AccepterBtn;

    @FXML
    private Button ReffuserBtn;
    @FXML
    private Button updateBtnstat;
    @FXML
    private Button IdPdf;
    @FXML
    private Button updateBtnserach;
    @FXML
    private TextField textsearch;
    @FXML
    private GridPane pane;

    @FXML
    private Label dated;

    @FXML
    private Label nbjour;

    @FXML
    private Label typec;

    @FXML
    private Label raison;
      @FXML
    private Label usercon;

    @FXML
    private Label etat;
    @FXML
    private Button btnRetour;
      @FXML
    private TableColumn<Conge,String> UserP1;

    int id;

    private ObservableList<Conge> Liste_Conge = FXCollections.observableArrayList();
    private ObservableList<Conge> Liste_rech = FXCollections.observableArrayList();

    CongeService cs = new CongeService();

    Conge c = new Conge();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*int id;
         id = utilisateurService.loggedUser.getId();
         ObservableList<Conge> Liste_Conge =cs.conn(id);
         tableconge.setItems(Liste_Conge);*/
        cs.listeConge(Liste_Conge);

        tableconge.setItems(Liste_Conge);

        DateP1.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        DateP1.cellFactoryProperty();

        NbjoursP1.setCellValueFactory(new PropertyValueFactory<>("nbrjours"));
        NbjoursP1.cellFactoryProperty();

        TypeP1.setCellValueFactory(new PropertyValueFactory<>("type_conge"));
        TypeP1.cellFactoryProperty();

        RaisonP1.setCellValueFactory(new PropertyValueFactory<>("raison"));
        RaisonP1.cellFactoryProperty();

        etatP1.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etatP1.cellFactoryProperty();
        
        
        
        
        

        tableconge.setEditable(true);
        pane.setVisible(false);
        tableconge.setOnMouseClicked(event -> {

            visualiserAction();
            FadeTransition fade = new FadeTransition(Duration.seconds(1), pane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();

            TranslateTransition ft = new TranslateTransition(Duration.seconds(1), pane);
            ft.setFromX(-100);
            ft.setToX(0);
            ft.play();
    

        });

    }

    public void visualiserAction() {
        Conge p5 = tableconge.getSelectionModel().getSelectedItem();

        
        java.sql.Date df = p5.getDatedebut();

        dated.setText(df.toString());


        etat.setText(p5.getEtat());
       nbjour.setText(Integer.toString(p5.getNbrjours()));
        typec.setText(p5.getType_conge());
        raison.setText(p5.getRaison());
         int congeId = tableconge.getSelectionModel().getSelectedItem().getId();
        Conge conge = cs.getCongeById(congeId);
        UserService us = new UserService();
        User user = us.getUserbyId(conge.getUser().getId());
        usercon.setText(user.getUsername());

        pane.setVisible(true);
    }

    @FXML
    void ajout(ActionEvent event) {
        Conge t;
        CongeService ts = new CongeService();

        java.sql.Date dPdate = new java.sql.Date(dPdatedebut.getValue().getYear() - 1900, dPdatedebut.getValue().getMonthValue() - 1, dPdatedebut.getValue().getDayOfMonth());

        t = new Conge();
        t.setDatedebut(dPdate);
        t.setNbrjours(Integer.parseInt(nbjP.getText()));

        t.setType_conge((String) ComboType.getValue());
        t.setRaison(TxtRaison.getText());
        t.setEtat("En attente");

        if (dPdatedebut.valueProperty().getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("la date ne doit pas être null");
            alert.showAndWait();
        } else if (TxtRaison.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("veuillez bien remplir le formulaire");
            alert.showAndWait();
        } else {
            ts.add(t);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESS");
            alert.setHeaderText(null);
            alert.setContentText("demande de congé avec succés");
            alert.showAndWait();
            this.refreshtable();
        }

    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
        Conge c = tableconge.getSelectionModel().getSelectedItem();

        Stage stage = (Stage) updateBtn1.getScene().getWindow();
        id = c.getId();
        c = cs.getCongeById(id);
        java.sql.Date df = c.getDatedebut();
        System.out.println("dddddddddd" + df);
        dPdatedebut.setValue(df.toLocalDate());

        nbjP.setText(Integer.toString(c.getNbrjours()));

        ComboType.setValue(c.getType_conge());
        TxtRaison.setText(c.getRaison());

    }

    @FXML
    void modifier2(ActionEvent event) throws IOException {

        LocalDate db = dPdatedebut.getValue();
        c.setDatedebut(java.sql.Date.valueOf(db));
        c.setNbrjours(Integer.parseInt(nbjP.getText()));

        c.setType_conge((String) ComboType.getValue());
        c.setRaison(TxtRaison.getText());
        cs.Update(id, c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("modification faite avec succes!");
        alert.showAndWait();
        this.refreshtable();

    }

    @FXML
    void supprimer(ActionEvent event) {
        Conge c = tableconge.getSelectionModel().getSelectedItem();
        CongeService cs = CongeService.getInstance();
        System.out.println("clicked");

        cs.remove(c.getId());
        this.refreshtable();
    }

    public void refreshtable() {
        CongeService sc = CongeService.getInstance();
        ObservableList<Conge> list = FXCollections.observableArrayList(sc.getAll());
//            tableApprenant.setItems(list);
        tableconge.setItems(list);
    }

    @FXML
    void acceper(ActionEvent event) {
        Conge c = tableconge.getSelectionModel().getSelectedItem();

        // Stage stage = (Stage) updateBtn1.getScene().getWindow();
        id = c.getId();
        System.out.println("id from gereContol back" + id);
        c = cs.getCongeById(id);
        boolean result = false;
        result = cs.validerConge(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        if (result) {
            alert.setContentText("Acceptation faite avec succes!");
        } else {
            alert.setContentText("Acceptation refusée!");
        }
        alert.showAndWait();

        this.refreshtable();

    }

    @FXML
    void refuser(ActionEvent event) {
        Conge c = tableconge.getSelectionModel().getSelectedItem();

//        Stage stage = (Stage) updateBtn1.getScene().getWindow();
        c = cs.getCongeById(c.getId());
        cs.refuserConge(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("Conge Refuser");
        alert.showAndWait();
        this.refreshtable();
    }

    @FXML
    void statistique(ActionEvent event) throws IOException {
        Stage stage = (Stage) updateBtnstat.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/BarChart.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    @FXML
    void imprimer(ActionEvent event) {

        int congeId = tableconge.getSelectionModel().getSelectedItem().getId();
        Conge conge = cs.getCongeById(congeId);
        UserService us = new UserService();
        User user = us.getUserbyId(conge.getUser().getId());
        System.out.println("user name :" + user.getUsername());

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("c:/pdf/" + user.getUsername() + ".pdf"));
            document.open();
            document.add(new Paragraph("\n------------------DEMANDE DE CONGE--------------------------\n\n" + "la date du congé :" + " " + tableconge.getSelectionModel().getSelectedItem().getDatedebut() + " \n" + "le nombre de jours :" + " " + tableconge.getSelectionModel().getSelectedItem().getNbrjours() + " \n " + "le Type du congé :" + " " + tableconge.getSelectionModel().getSelectedItem().getType_conge() + " \n " + "la raison du congé:" + " " + tableconge.getSelectionModel().getSelectedItem().getRaison()));
            document.addTitle("title");
            document.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("pdf avec success");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("la publication a été imprimer en PDF avec success");
        alert.show();

    }
    private String x;

    @FXML
    void Search(ActionEvent event) {

        tableconge.setVisible(true);
        x = textsearch.getText();
        cs.Rechercher(Liste_rech, x);
        tableconge.setItems(Liste_rech);

        DateP1.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        DateP1.cellFactoryProperty();

        NbjoursP1.setCellValueFactory(new PropertyValueFactory<>("nbrjours"));
        NbjoursP1.cellFactoryProperty();

        TypeP1.setCellValueFactory(new PropertyValueFactory<>("type_conge"));
        TypeP1.cellFactoryProperty();

        RaisonP1.setCellValueFactory(new PropertyValueFactory<>("raison"));
        RaisonP1.cellFactoryProperty();

        etatP1.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etatP1.cellFactoryProperty();

        tableconge.setEditable(true);

    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnRetour.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilChef.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }
    @FXML
    void logOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnRetour.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }
    


}
