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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import social_pro.entites.User;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class AdminInterfaceController implements Initializable {

    @FXML
    private Button btnConfirmMember;
    @FXML
    private Button btnConfirmEvents;
    @FXML
    private ImageView logout;
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
    private void confirmMember(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnConfirmMember.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/FXML/FXMLNewMember.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void ConfirmEvents(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnConfirmEvents.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/FXML/FXMLConfirmEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnlogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/FXML/FXMLAuthentification.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
