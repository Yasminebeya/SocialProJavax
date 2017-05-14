/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static social_pro.controllers.EventsListController.liste_Evs;
import social_pro.entites.Evenement;
import social_pro.interfaces.IEvenement;
import social_pro.service.EvenementService;
import social_pro.service.UserService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class EventsController implements Initializable {

    EvenementService es = new EvenementService();
    private Button btn_statistique;
    public static Evenement eMap;

    @FXML
    private Button btn_retour;

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

    private ObservableList<Evenement> Liste_Evennement = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Evenement> Liste_Event = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Evenement, String> lieu;
    @FXML
    private TableColumn<Evenement, Integer> nbrparticipants;
    @FXML
    private Button btn_participe;
    @FXML
    private Button btn_pastEvents;
    @FXML
    private Button btn_mesEves;
    @FXML
    private Button myEvents;
    @FXML
    private TextField search1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementService e1 = new EvenementService();

        e1.afficher(Liste_Evennement);

        tableEvents.setItems(Liste_Evennement);

        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        lieu.setCellFactory(TextFieldTableCell.forTableColumn());

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
    private void goEventsList(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Front.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    private void goStatistique(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_statistique.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLStatistique.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    @FXML
    private void participe(ActionEvent event) throws IOException {
        Evenement evenement = tableEvents.getSelectionModel().getSelectedItem();
      
        System.out.println(evenement.getId() + "llllll");
        System.out.println(UserService.loggeduser.getId());
        liste_Evs = es.getparticipatedevents(UserService.loggeduser.getId());

        if (evenement == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'évènement dont vous voulez participer !");
            alert.setContentText("Veuillez Sélectionner un évènement");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableEvents.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Voulez vous vraiment participer à cet évènement");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean exists = false;
                    for (Evenement e : liste_Evs) {
                        System.out.println(e.getId() + "test");
                        if (e.getId() == evenement.getId()) {
                            exists = true;

                            Notifications notificationBuilder = Notifications.create()
                                    .title("Vous avez déjà participé à cet évènement")
                                    .text("Veuillez sélectionner un autre évènement")
                                    .graphic(null)
                                    .hideAfter(Duration.seconds(10))
                                    .position(Pos.BOTTOM_RIGHT)
                                    .onAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            System.out.println("");
                                        }
                                    });
                            notificationBuilder.showInformation();

                        }
                    }

                    if (!exists) {
                        es.participer(evenement.getId(), UserService.loggeduser.getId());
                    }
                    Notifications notificationBuilder = Notifications.create()
                            .title("Vous avez participé à un évènement")
                            .text("Nous ésperons que ça sera benifique pour vous")
                            .graphic(null)
                            .hideAfter(Duration.seconds(10))
                            .position(Pos.BOTTOM_RIGHT)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("");
                                }
                            });
                    notificationBuilder.showInformation();
                }
            });

            Stage stage = (Stage) btn_participe.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLEvents.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }
    
       private ObservableList<Evenement> Liste_event=FXCollections.observableArrayList();
    public void refreshtable(){
        EvenementService ev= EvenementService.getInstance().getInstance();
          
          ev.afficher(Liste_event);
        tableEvents.setItems(Liste_event);   
    }

    

     @FXML
        private void goPastEvents(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_pastEvents.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLPastEventsList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void mesEves(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_mesEves.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/EventsList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

    @FXML
    private void myEvents(ActionEvent event) throws IOException {
         Stage stage = (Stage) myEvents.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLMyEvents.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
    
         
     
    public void buildData2() {
        IEvenement su = new EvenementService();
        Liste_Event = FXCollections.observableArrayList();

        try {
            ResultSet r = su.recherche(search1.getText());
            while (r.next()) {
                Evenement e = new Evenement();
                e.setId(r.getInt("id"));
                e.setNom(r.getString("nom"));
                e.setCategorie(r.getString("categorie"));
                e.setDescription(r.getString("description"));
                e.setNbrelimite(r.getInt("nbrelimite"));
                e.setNbrparticipants(r.getInt("nbrparticipants"));
                e.setDatedebut(r.getDate("datedebut"));
                e.setDatefin(r.getDate("datefin"));
                e.setFlag(r.getInt("flag"));
                

                Liste_Event.add(e);

            }
            tableEvents.setItems(null);
            tableEvents.setItems(Liste_Event);
            

        } catch (SQLException ex) {
            Logger.getLogger(EventsListAdminController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rech(KeyEvent event) {
        tableEvents.refresh();
        EvenementService es = new EvenementService();
        buildData2();
    }

    
       @FXML
    private void VoirMap(ActionEvent actionEvent) throws IOException {
        Parent page3 = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/MapApi_1.fxml"));
        Scene scene = new Scene(page3);
    //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

