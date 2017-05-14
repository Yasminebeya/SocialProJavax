/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import social_pro.service.EmailSender;
import social_pro.service.TacheService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class EmailController implements Initializable {
    @FXML
    private Button btnRetour;
 
    @FXML
    private Button btnlogout;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField sujet;
    @FXML
    private JFXPasswordField passw;
    @FXML
    private JFXTextArea msg;
    @FXML
    private JFXButton envoyer;
    @FXML
    private JFXButton annuler;
             @FXML
    private Pane pane;

    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
     
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
          Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/LoggedUserT2.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
        
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/authentification.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/LoggedUserT2.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
        
    }

    @FXML
    private void envoyer(ActionEvent event) {
        EmailSender emailS = new EmailSender();
          String[] to={"babdeljawed.marwa@gmail.com"};
       if  ( emailS.sendMail(adresse.getText().trim(),passw.getText(),msg.getText(),msg.getText(), to ))
       {
           
       }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(adresse.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Email Envoyer Avec Succées ");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        Stage stage = (Stage) pane.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/LoggedUserT2.fxml"));
                        Scene scene = new Scene(root); stage.setScene(scene); 
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
    }
    
}
