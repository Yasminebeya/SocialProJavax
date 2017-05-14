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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import social_pro.entites.Tache;
import social_pro.service.TacheService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class ListeTacheAfféctéController implements Initializable {

    @FXML
    private Text ajouterTache;
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
    private Button btnRetour;  
    @FXML
    private Button btnHome;
    @FXML
    private ImageView imgRetour;
    @FXML
    private ImageView imghome;
    @FXML
    private Button btnModif;
     @FXML
    private Pane pane;
    
     static int id;
     static Tache tache;
      

   private ObservableList<Tache> Liste_Tache_nAff=FXCollections.observableArrayList();
   TacheService es = new TacheService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          es.listeTacheAff(Liste_Tache_nAff);

            tachesAffectees.setItems(Liste_Tache_nAff);
            datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();
        
        
        
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();
        
        employe.setCellValueFactory(new PropertyValueFactory<>("matricule_id"));
        employe.cellFactoryProperty();
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.cellFactoryProperty();
        
        tachesAffectees.setEditable(true);
    }    

    @FXML
    private void modifierChapitre(MouseEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Tache t=tachesAffectees.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) btnModif.getScene().getWindow();
        tache=tachesAffectees.getSelectionModel().getSelectedItem();
        id=tache.getId();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/UpdateTache.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene); stage.show(); 
        
    }
    /*Evenement evenement = tableEvents.getSelectionModel().getSelectedItem();
    Stage stage = (Stage) btn_modif.getScene().getWindow(); 
    ev =tableEvents.getSelectionModel().getSelectedItem();
    id=ev.getId();
    Parent root = FXMLLoader.load(getClass().getResource("/social_pro/FXML/FXMLUpdate_Event.fxml"));
    Scene scene = new Scene(root); stage.setScene(scene); stage.show(); 
   */
    
}
