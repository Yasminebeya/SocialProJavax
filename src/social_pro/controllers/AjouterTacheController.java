
package social_pro.controllers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static social_pro.controllers.ListeTacheAfféctéController.tache;
import social_pro.entites.Tache;
import social_pro.entites.fos_user;
import social_pro.service.TacheService;
import social_pro.service.UserService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class AjouterTacheController implements Initializable {

    @FXML
    private Button btnRetour;
    
    @FXML
    private TableView<Tache> tachenonaffecte;
   @FXML
    private TableColumn<Tache,Date> datedebut;
   @FXML
    private TableColumn<Tache,Date> datefin;
   @FXML
    private TableColumn<Tache,String> description;
   @FXML
    private Button btnAffecter;
     @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnMAJ;
 
    @FXML
    private JFXDatePicker dPdatedebut;
    @FXML
    private JFXDatePicker dPdatefin;
    @FXML
    private JFXTextArea txtdescriptionDiscipline;
    @FXML
    private JFXComboBox ComboMatricule;
    @FXML
    private JFXButton btnTacheAff;
    @FXML
    private JFXButton btnRendement;
    @FXML
    private JFXButton btnStat;
    @FXML
    private Button btnlogout;
    @FXML
    private Pane pane;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnAjoutTache;
 
 
     
     int id_selectedT;
     Tache tS;
    
    TacheService ts = new TacheService();
     private ObservableList<Tache> Liste_Tache=FXCollections.observableArrayList();
     private ObservableList<Tache> Liste_Tache2=FXCollections.observableArrayList();
     private ObservableList<Tache> Liste_Tache3=FXCollections.observableArrayList();
       private ObservableList<Tache> Liste_Tache4=FXCollections.observableArrayList();
      private ObservableList<fos_user> Liste_Matricule=FXCollections.observableArrayList();
        private ObservableList<String> lst=FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ts.listeMatEmp(Liste_Matricule);
        for (fos_user user : Liste_Matricule) {
            lst.add(user.getUsername());
        }
        ComboMatricule.setItems(lst);  
        
        
        ts.listeTacheNonAff(Liste_Tache);
      
        tachenonaffecte.setItems(Liste_Tache);
        
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();
        
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.cellFactoryProperty();
        
        tachenonaffecte.setEditable(true);
        
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilChef.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void add(ActionEvent event)  throws Exception {
  
     if(dPdatedebut.valueProperty().getValue()==null && dPdatefin.valueProperty().getValue()==null 
             && txtdescriptionDiscipline.getText().equals(""))
                {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("aucune nouvelle tâche à ajoutée");
            alert.showAndWait();
                }
     else if(dPdatedebut.valueProperty().getValue()==null && dPdatefin.valueProperty().getValue()==null)
                {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("la date ne doit pas être null");
            alert.showAndWait();
                }
      else if
              (dPdatedebut.valueProperty().getValue()==null )
                {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("la date de début  ne doit pas être null");
            alert.showAndWait();
                }
              
       else if
              (dPdatefin.valueProperty().getValue()==null )
                {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("la date de fin  ne doit pas être null");
            alert.showAndWait();
                }
       else if
                   (txtdescriptionDiscipline.getText().equals("") )
                {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("champ description ne doit pas être ");
            alert.showAndWait();
                }
              
   
        else
        {
             java.sql.Date dDebut= new java.sql.Date(dPdatedebut.getValue().getYear()-1900, dPdatedebut.getValue().getMonthValue()-1, dPdatedebut.getValue().getDayOfMonth());
        java.sql.Date dFin= new java.sql.Date(dPdatefin.getValue().getYear()-1900, dPdatefin.getValue().getMonthValue()-1, dPdatefin.getValue().getDayOfMonth());
           
                    
             if(dDebut.after(dFin) )
        {
       
          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier votre Date");
            alert.showAndWait();
        }
             else
             {
             Tache t= new Tache(dDebut,dFin,txtdescriptionDiscipline.getText(),"pas encore réalisée");
                 ts.add(t);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESS");
            alert.setHeaderText(null);
            alert.setContentText("Tâche ajoutée avec succés!");
            alert.showAndWait();
            this.refreshtable();
             }
            
        }
    }
    

  

    @FXML
    private void affecter(ActionEvent event) {
          Tache t1=tachenonaffecte.getSelectionModel().getSelectedItem();
          
          if(t1==null)
          {
               Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tache à affécter");
            alert.showAndWait();
          }
          else
          {
          String combo=(String) ComboMatricule.getValue();
          
         int id= ts.nomUser(combo);
          Tache t ;
             t=new Tache(id);             
          ts.affecter(t,t1.getId());
          this.refreshtable2();
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCESS");
            alert.setHeaderText(null);
            alert.setContentText("Votre Tâche a été affecter avec succées !"
                    + "Voulez vous consulter la liste des tâches afféctées");
            alert.showAndWait();
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        Stage stage = (Stage) dPdatedebut.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/TacheAfféctées.fxml"));
                        Scene scene = new Scene(root); stage.setScene(scene); 
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterTacheController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else
                    {
                    }
            });
            
          }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Tache tache = tachenonaffecte.getSelectionModel().getSelectedItem();
          
          if (tache == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tache");
            alert.showAndWait();
    }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tachenonaffecte.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous vraiment supprimer cette tâche ?! ");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    TacheService service = new TacheService();
                    service.delete(tache.getId());
                  tachenonaffecte.getItems().remove(tachenonaffecte.getSelectionModel().getSelectedItem());
                    tachenonaffecte.refresh();

                }
            });
          }
        
    }
    

    @FXML
    private void MAJ(ActionEvent event) throws IOException {
        
       Tache t=tachenonaffecte.getSelectionModel().getSelectedItem();
                if (t == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tache");
            alert.showAndWait();
    }
          else {
       
      Stage stage = (Stage) btnRetour.getScene().getWindow();
       tache=tachenonaffecte.getSelectionModel().getSelectedItem(); 
       this.id_selectedT=tache.getId();
        tS=ts.getTacheById(id_selectedT);
       txtdescriptionDiscipline.setText(tS.getDescription());
       java.sql.Date db = tS.getDatedebut();
        dPdatedebut.setValue(db.toLocalDate());
        java.sql.Date df = tS.getDatefin();
        dPdatefin.setValue(df.toLocalDate());
                
                
                }
    }

    @FXML
    private void modif(ActionEvent event) {
         
         if (tS==null)
         {
             
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tache");
            alert.showAndWait();
         }
         else
         {
        tS.setDescription(txtdescriptionDiscipline.getText());
        LocalDate db = dPdatedebut.getValue();
        tS.setDatedebut(java.sql.Date.valueOf(db));
        LocalDate df = dPdatefin.getValue();
        tS.setDatefin(java.sql.Date.valueOf(df));
        tS.setId(ListeTacheAfféctéController.id);
        
         if(dPdatedebut.valueProperty().getValue()==null||dPdatefin.valueProperty().getValue()==null)
                {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("la date ne doit pas être null");
            alert.showAndWait();
                }
          else  if(txtdescriptionDiscipline.getText().equals(""))
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("veuillez décrire le fonctionnement de votre tache");
            alert.showAndWait();
            }    
                    
                 
        else
          {
        ts.updateback(id_selectedT, tS);
     
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESS");
            alert.setHeaderText(null);
            alert.setContentText("modification faite avec succes!");
            alert.showAndWait();
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                  this.refreshtable3();
                   dPdatedebut.setPromptText("Date Début");
                    dPdatefin.setPromptText("Date Fin");
                   txtdescriptionDiscipline.setPromptText("Description");

                }
            });
        
          }}
    }

    @FXML
    private void tacheAff(ActionEvent event) throws IOException {
           Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/TacheAfféctées.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void consulterRend(ActionEvent event) throws IOException {
           Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/ConsulterRendementTache.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void consStat(ActionEvent event) throws IOException {
           Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/BarChart.fxml")); 
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

    public void refreshtable(){
        TacheService ts= TacheService.getInstance();
          
          ts.listeTacheNonAff(Liste_Tache2);
        tachenonaffecte.setItems(Liste_Tache2);   
    }
    
     public void refreshtable2(){
        TacheService ts= TacheService.getInstance();
          
          ts.listeTacheNonAff(Liste_Tache3);
        tachenonaffecte.setItems(Liste_Tache3);   
    }
     
     public void refreshtable3(){
        TacheService ts= TacheService.getInstance();
          
          ts.listeTacheNonAff(Liste_Tache4);
        tachenonaffecte.setItems(Liste_Tache4);   
    }

    
}
