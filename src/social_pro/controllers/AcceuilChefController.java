
package social_pro.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static social_pro.controllers.ListeTacheAfféctéController.tache;
import social_pro.entites.Tache;
import social_pro.service.TacheService;
import social_pro.service.UserService;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author smart
 */
public class AcceuilChefController implements Initializable {

    
     @FXML
    private TableView<Tache> tacheNonRealise;
     
     @FXML
    private TableColumn<Tache,Date> datedebut;
   
   @FXML
    private TableColumn<Tache,Date> datefin;
   
   @FXML
    private TableColumn<Tache,String> description;
   
    @FXML
    private TableColumn<Tache,String> employee_id;
     
    
    @FXML
    private Button btnGestionTache;
    @FXML
    private Button btnGestionConge;
    @FXML
    private Pane pane;
    @FXML
    private JFXButton btnSupprimer;
    @FXML
    private JFXButton btnEnvoyerConv;
    
    
    
          @FXML
    private Button btnlogout;
    
        private ObservableList<Tache> tacheNonRalisee=FXCollections.observableArrayList();
        
        TacheService es = new TacheService();
        
        
       static Tache tx;
       static int mat_id;
       private String nom;
   
  
  

    
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     String loggedUserName = UserService.loggeduser.getUsername();
/*     TrayNotification tray = new TrayNotification("Connexion "," Bonjour " + loggedUserName + " Bienvenue sur Social Pro" ,NotificationType.SUCCESS);
     tray.showAndWait();*/
        
        es.listeTacheNonRealise(tacheNonRalisee);
        tacheNonRealise.setItems(tacheNonRalisee);
    
        
         datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();
        
        
        
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();
        
       description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.cellFactoryProperty();
       
       

       
       employee_id.setCellValueFactory(new PropertyValueFactory<>("username"));
       employee_id.cellFactoryProperty();
       
        tacheNonRealise.setEditable(true);
        
        

       
    }  
    
      
     @FXML
    void gestionTache(ActionEvent event) throws IOException{
     
        Stage stage = (Stage) btnGestionTache.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AjouterTache.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }
    
    @FXML
    void gestionConge(ActionEvent event) throws IOException{
         Stage stage = (Stage) btnGestionTache.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/gererCongeBack.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
     
        
    }
    
    @FXML
    void conv(ActionEvent event) throws IOException{
      Tache t=tacheNonRealise.getSelectionModel().getSelectedItem();
      if (t == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tache Non Réalisée ");
            alert.showAndWait();}
      else
      {
        Stage stage = (Stage) btnEnvoyerConv.getScene().getWindow();
        tx=tacheNonRealise.getSelectionModel().getSelectedItem();
        mat_id=tx.getMatricule_id();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/convocationEmp.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene); stage.show();
      }
    }
    
    @FXML
    void supprimer(ActionEvent event){
     
         Tache tache = tacheNonRealise.getSelectionModel().getSelectedItem();
          
          if (tache == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tache a supprimer");
            alert.showAndWait();
    }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tacheNonRealise.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Avez vous déja envoyer une convocation ?! "
                    + "Voulez vous vraiment supprimer cette tâche ?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    TacheService service = new TacheService();
                    service.delete(tache.getId());
                  tacheNonRealise.getItems().remove(tacheNonRealise.getSelectionModel().getSelectedItem());
                    tacheNonRealise.refresh();

                }
            });
          }
        
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
           
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/authentification.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }
    
}
