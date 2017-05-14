
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import social_pro.entites.Tache;
import social_pro.service.UserService;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class FrontController implements Initializable {

  
  
    @FXML
    private Button btnGestionEvenements;
    @FXML
    private Button btnGestionActualite;
    @FXML
    private Button btnGestionProduits;
    @FXML
    private Button btnGestionTache;
    @FXML
    private Pane paneCrowdFunding;
    @FXML
    private ImageView photoCrowdFunding;
    @FXML
    private Button btnlogout;
    @FXML
    private Button btnGestionConge;
 @FXML
    private Pane pane;

   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
     String loggedUserName = UserService.loggeduser.getUsername();
            /* TrayNotification tray = new TrayNotification("Connexion "," Bonjour " + loggedUserName + " Bienvenue sur Social Pro" ,NotificationType.SUCCESS);
              tray.showAndWait();*/
    }    


    @FXML
    private void gestionTache(ActionEvent event) throws IOException {
        
  
      
        Stage stage = (Stage) btnGestionTache.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/LoggedUserT2.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void gestionConge(ActionEvent event) throws IOException {
         Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/ajoutConge.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gestionEvenements(ActionEvent event) throws IOException {
        
          Stage stage = (Stage) btnGestionEvenements.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLEvents.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GestionProduit(ActionEvent event) {
    }

   

    @FXML
    private void gestionActualite(ActionEvent event) throws IOException {
          Stage stage = (Stage) btnGestionTache.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/gererProduitBack.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Login.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }
    
}
