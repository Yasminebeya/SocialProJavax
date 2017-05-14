/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static social_pro.controllers.EventsListController.liste_Evs;
import social_pro.entites.Evenement;
import social_pro.entites.Participant;
import social_pro.entites.User;
import social_pro.service.EvenementService;
import social_pro.service.UserService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class MyEventsController implements Initializable {

    static Evenement ev;
    static Participant p;
     static Participant par;

    @FXML
    static TableColumn<?, ?> id;
    static int id_user;

    static int idUser;
    static User u;
    private TextField search;
    private Button btn_addEvent;
    @FXML
    private TableView<Evenement> tableEvents;
    @FXML
    private TableColumn<Evenement, String> categorie;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, Integer> nbre_limite;
    @FXML
    private TableColumn<Evenement, Date> datedebut;
    @FXML
    private TableColumn<Evenement, Date> datefin;
    @FXML
    private Button btn_participe;
    @FXML
    private Button btn_retour;
    private Evenement catego;

    private ObservableList<Evenement> Liste_Evenement = FXCollections.observableArrayList();

    EvenementService es = new EvenementService();
    Evenement e = new Evenement();
    @FXML
    private TableColumn<Evenement, Integer> nbrparticipants;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

         //      btn_modif.setVisible(false);
      //          id_user=ev.getUser_id();
        //     System.out.println("id eli amal l ev   "+id_user);
        //  idUser= AuthentificationController.id_u;
     //          if (id_user == idUser){
        //              btn_modif.setVisible(true);
        //          }
        es.myEvents(Liste_Evenement);

        tableEvents.setItems(Liste_Evenement);
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        categorie.setCellFactory(TextFieldTableCell.forTableColumn());

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        
        nbre_limite.setCellValueFactory(new PropertyValueFactory<>("nbrelimite"));
        nbre_limite.cellFactoryProperty();

        nbrparticipants.setCellValueFactory(new PropertyValueFactory<>("nbrparticipants"));
        nbrparticipants.cellFactoryProperty();
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();

        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();

        tableEvents.setEditable(true);
    }

    @FXML
    private void goPastEvents(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLEvents.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    private ObservableList<Evenement> GetEventByCategorie(String categorie) throws SQLException {
        ObservableList<Evenement> events;
        EvenementService es = new EvenementService();
        List<Evenement> EventList = es.getEvenementByCat(categorie);
        events = FXCollections.observableArrayList();
        for (Evenement e : EventList) {
            events.add(e);
        }
        return events;
    }

    private void search(KeyEvent event) {
        System.out.println("inside  ");
        System.out.println("Titre=" + search.getText());
        try {
            System.out.println(search.getText());
            Liste_Evenement = GetEventByCategorie(search.getText());

        } catch (SQLException ex) {
            Logger.getLogger(MyEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableEvents.setItems(Liste_Evenement);

    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {

      Evenement evenement = tableEvents.getSelectionModel().getSelectedItem();
          
     
    
    if (evenement == null) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'évènement dont vous voulez participer !");
            alert.setContentText("Veuillez Sélectionner un évènement");
            alert.showAndWait();
    }
          else {
             
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableEvents.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Voulez vous vraiment participer à cet évènement");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

             int id_p=AuthentificationController.id_u;
                    System.out.println("*********"+id_p);
                    int idtest = evenement.getId();
              System.out.println("++++++++++++++++++++"+idtest);
               par= es.getParticipantById(idtest);
                    System.out.println(idtest+"nchallah sne");
                ev=es.getEvenementById(par.getIdevenement());
                    System.out.println(ev.toString());
                   par= es.getParticipantById(idtest);
                     int idEv=par.getIdevenement();
                     es.updateEvent(idEv);
                      es.nePlusParticiper(idtest);
 
                    //tableEvents.refresh();

                }
            });
        Stage stage = (Stage) btn_participe.getScene().getWindow();
        
       Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLMyEvents.fxml"));
       

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show(); 
           
          }
    }
}
    
//    }

