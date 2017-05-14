/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import social_pro.entites.Evenement;
import social_pro.entites.User;
import social_pro.service.EvenementService;
import social_pro.service.UserService;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class ConfirmEventController implements Initializable {

    @FXML
    private AnchorPane eventsList;
    @FXML
    private TextField search;
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
    private Button btn_confirm;
    @FXML
    private Button btn_delete;

    EvenementService es = new EvenementService();
    private ObservableList<Evenement> Liste_Evenement = FXCollections.observableArrayList();
    User u = new User();
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_add;

    @FXML
    private void ConfirmEvent(ActionEvent event) throws IOException {
        Evenement evenement = tableEvents.getSelectionModel().getSelectedItem();

        if (evenement == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'évènement que vous voulez confirmer !");
            alert.setContentText("Veuillez Sélectionner un évènement");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableEvents.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Voulez vous vraiment confirmer cet évènement");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    es.confirmEvent(evenement.getId());

                    System.out.println("++++++++++++++++++++" + evenement.getId());
                    //tableEvents.refresh();
                }
            });
            Stage stage = (Stage) btn_confirm.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLConfirmEvent.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }

    @FXML
    private void DeleteEvent(ActionEvent event) {
        Evenement evenement = tableEvents.getSelectionModel().getSelectedItem();

        if (evenement == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'évènement à supprimer !");
            alert.setContentText("Veuillez Sélectionner un évènement");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableEvents.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression Evènement");
            alert.setContentText("Voulez vous vraiment supprimer cet évènement");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    es.remove(evenement.getId());
                    System.out.println("++++++++++++++++++++" + evenement.getId());
                    tableEvents.getItems().remove(tableEvents.getSelectionModel().getSelectedItem());
                    tableEvents.refresh();

                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        es.afficher1(Liste_Evenement);

        tableEvents.setItems(Liste_Evenement);

        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        categorie.setCellFactory(TextFieldTableCell.forTableColumn());

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.setCellFactory(TextFieldTableCell.forTableColumn());

        nbre_limite.setCellValueFactory(new PropertyValueFactory<>("nbrelimite"));
        nbre_limite.cellFactoryProperty();

        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();

        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();

        tableEvents.setEditable(true);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLAdminInterface.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    @FXML
    private void AddEvent(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_add.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLAjoutEvent.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }
}
