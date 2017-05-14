/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static social_pro.controllers.ListeCongeController.id;
import social_pro.entites.Conge;
import social_pro.service.CongeService;
import social_pro.service.UserService;

public class AjoutCongeController implements Initializable {

   
    @FXML
    private Button btnRetour;

    @FXML
    private Button btnlogout;
    @FXML
    private Button updateBtn1;

    @FXML
    private Button addBtn;

    @FXML
    private JFXDatePicker dPdatedebut;

    @FXML
    private JFXTextArea TxtRaison;

    @FXML
    private JFXTextField nbjP;

    @FXML
    private JFXComboBox ComboType;

    @FXML
    private TableView<Conge> tableconge;

    @FXML
    private TableColumn<Conge, Date> dateP1;

    @FXML
    private TableColumn<Conge, Integer> NbjoursP1;

    @FXML
    private TableColumn<Conge, String> TypeP1;

    @FXML
    private TableColumn<Conge, String> RaisonP1;

    @FXML
    private TableColumn<Conge, String> etatP1;

    @FXML
    private Button suppBtn;

     @FXML
    private Button updateBtn;
       @FXML
    private TextField textsearch;


    int id;

    private ObservableList<Conge> Liste_Conge = FXCollections.observableArrayList();
    private ObservableList<Conge> Liste_rech = FXCollections.observableArrayList();

    CongeService cs = new CongeService();
    ObservableList<String> options = FXCollections.observableArrayList(
            "Repos",
            "Maternite",
            "Maladie"
    );

    Conge c = new Conge();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboType.setItems(options);

        //int id;
        id = UserService.loggeduser.getId();

        cs.listeConge2(Liste_Conge);
        tableconge.setItems(Liste_Conge);

        dateP1.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        dateP1.cellFactoryProperty();

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
        t.setUser(UserService.loggeduser);
        if (dPdatedebut.valueProperty().getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("la date ne doit pas être null");
            alert.showAndWait();
        } else if (TxtRaison.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("veuillez donner la raison");
            alert.showAndWait();
            
            
        } else {
            ts.add(t);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESS");
            alert.setHeaderText(null);
            alert.setContentText("demande de congé avec succés");
            alert.showAndWait();
            TxtRaison.setText("");
            ComboType.setItems(options);
            nbjP.setText("");
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
        TxtRaison.setText("");
        ComboType.setItems(options);
        nbjP.setText("");
        this.refreshtable();

    }

    @FXML
    void supprimer(ActionEvent event) {
        Conge c = tableconge.getSelectionModel().getSelectedItem();
        CongeService cs = CongeService.getInstance();
     

        cs.remove(c.getId());
        this.refreshtable();
    }

    public void refreshtable() {
        ObservableList<Conge> Liste = FXCollections.observableArrayList();
        cs.listeConge2(Liste);
        tableconge.setItems(Liste);
    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnRetour.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Front.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }
    String x;

    @FXML
    void Search(ActionEvent event) {

        tableconge.setVisible(true);
        x = textsearch.getText();
         int id_user = UserService.loggeduser.getId();
        cs.RechercherEtat(Liste_rech, x,id_user);
        tableconge.setItems(Liste_rech);

        dateP1.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        dateP1.cellFactoryProperty();

        NbjoursP1.setCellValueFactory(new PropertyValueFactory<>("nbrjours"));
        NbjoursP1.cellFactoryProperty();

        TypeP1.setCellValueFactory(new PropertyValueFactory<>("type_conge"));
        TypeP1.cellFactoryProperty();

        RaisonP1.setCellValueFactory(new PropertyValueFactory<>("raison"));
        RaisonP1.cellFactoryProperty();

        etatP1.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etatP1.cellFactoryProperty();

        tableconge.setEditable(true);
tableconge.refresh();
    }
      @FXML
    void logOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnRetour.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/authentification.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }
    

}
