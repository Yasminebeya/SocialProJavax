
package social_pro.controllers;

import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static social_pro.controllers.ListeTacheAfféctéController.tache;
import social_pro.entites.Tache;
import social_pro.service.TacheService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class TacheAfféctéesController implements Initializable {

    @FXML
    private Button btnRetour;
    @FXML
    private TableView<Tache> tachesAffectees;
    @FXML
    private TableColumn<Tache, Date> datedebut;
    @FXML
    private TableColumn<Tache, Date> datefin;
    @FXML
    private TableColumn<Tache, Integer> employe;
    @FXML
    private TableColumn<Tache, String> description;
    @FXML
    private Button btnConsulter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnMAJ;
    @FXML
    private Label lblDateDeb;
    @FXML
    private Label lblDatefi;
    @FXML
    private Label lblEmp;
      @FXML
    private Button btnlogout;
    @FXML
    private JFXTextArea Desc;
 
  
     @FXML
    private Pane pane;
    
     private ObservableList<Tache> Liste_Tache_Aff=FXCollections.observableArrayList();
   TacheService ts = new TacheService();
   
     static int id;
     static Tache tache;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //********************
        Desc.setEditable(false);
        lblDateDeb.setVisible(false);
        lblDatefi.setVisible(false);
        lblEmp.setVisible(false);
        Desc.setVisible(false);
        //**********************
        
        ts.listeTacheAff(Liste_Tache_Aff);

            tachesAffectees.setItems(Liste_Tache_Aff);
            datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();
        
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();
        
        employe.setCellValueFactory(new PropertyValueFactory<>("username"));
        employe.cellFactoryProperty();
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.cellFactoryProperty();
        
        tachesAffectees.setEditable(true);

    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AjouterTache.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }


    @FXML
    private void consulter(ActionEvent event) {
    
     Tache t=tachesAffectees.getSelectionModel().getSelectedItem();
    //t.getDatedebut();
       // System.out.println(".......  "+t.getDatedebut());
     if (t==null)
     {
      Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tache");
            alert.showAndWait();
     }
     else
     {
        
        java.sql.Date db=t.getDatedebut();
        java.sql.Date df=t.getDatefin();
        String Des=t.getDescription();
       String emp=t.getUsername();
      
        
        lblDateDeb.setText(db.toString());
        lblDatefi.setText(df.toString());
        Desc.setText(Des);
        lblEmp.setText(emp);
        
        
        
          lblDateDeb.setVisible(true);
          lblDatefi.setVisible(true);
        Desc.setVisible(true);
        lblEmp.setVisible(true);
    
     }
    }
   

    @FXML
    private void supprimer(ActionEvent event) {
         Tache tache = tachesAffectees.getSelectionModel().getSelectedItem();
          
          if (tache == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tache");
            alert.showAndWait();
    }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tachesAffectees.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous vraiment supprimer cette tâche ?! ");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    TacheService service = new TacheService();
                    service.delete(tache.getId());
                  tachesAffectees.getItems().remove(tachesAffectees.getSelectionModel().getSelectedItem());
                    tachesAffectees.refresh();

                }
            });
          }
        
    }

    @FXML
    private void MAJ(ActionEvent event) throws IOException {
          Tache t=tachesAffectees.getSelectionModel().getSelectedItem();
                   if (t == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tâche à modifier");
            alert.showAndWait();
    }
          else {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        tache=tachesAffectees.getSelectionModel().getSelectedItem();
        id=tache.getId();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/MAJtache.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene); stage.show(); 
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
