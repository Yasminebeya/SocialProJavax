/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Navoxx
 */
public class AcceuilAdminController implements Initializable {

    @FXML
    private Pane paneCrowdFunding;
    @FXML
    private Button btntache;
    @FXML
    private ImageView photoCrowdFunding;
    @FXML
    private Pane paneCrowdSourcing;
    @FXML
    private Pane paneProfils;
    @FXML
    private Button btnevenement;
    @FXML
    private ImageView AccederProjets;
    @FXML
    private Button btnconge;
    @FXML
    private ImageView AccederForum;
    @FXML
    private Button btnactualite;
    @FXML
    private ImageView photoCrowdProfils;
    @FXML
    private Button btnproduit;
    @FXML
    private ImageView photoCrowdSourcing;
    @FXML
    private Button btnlogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showtache(ActionEvent event) throws IOException {
        Stage stage = (Stage) btntache.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLNewMember.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showevenement(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnevenement.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLEventsListAdmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showactualite(ActionEvent event) {
    }

    @FXML
    private void showproduit(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnproduit.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/listeProduitFront.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    @FXML
     private void logout(ActionEvent event) throws IOException {
     Stage stage = (Stage) btnlogout.getScene().getWindow();
     Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Login.fxml"));
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show(); 
     }  
}
