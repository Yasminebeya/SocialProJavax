/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import social_pro.entites.Message;
import social_pro.entites.Tache;
import social_pro.service.TacheService;
import social_pro.service.UserService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class LoggedUserT2Controller implements Initializable {


    @FXML
    private Button btnRetour;
    @FXML
    private TableView<Tache> tacheluser;
    @FXML
    private TableColumn<Tache, Date> datedebut;
    @FXML
    private TableColumn<Tache, Date> datefin;
    @FXML
    private TableColumn<Tache, String> description;
    @FXML
    private TableColumn<Tache, String> etat;
    @FXML
    private Button btnConsulter;
    @FXML
    private Label lblDateDebut;
    @FXML
    private Label lblDatefin;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblEtat;
    @FXML
    private ComboBox comobEtat;
    @FXML
    private Button btnModif;
    private Label lblMsg;
    @FXML
    private Button btnSupprimer;
     @FXML
    private TableView<Message> message;
    @FXML
    private TableColumn<Message, String> objet;
    @FXML
    private TableColumn<Message, Date> date;
    @FXML
    private TableColumn<Message, String> text;
    @FXML
    private Button btnCosMsg;
       @FXML
    private JFXTextArea txtAdesc;
    @FXML
    private JFXTextArea TxtAmessage;
     @FXML
    private Pane pane;
       @FXML
    private Button btnlogout;
       
    @FXML
    private JFXButton btnEmail;
   
    
    
    private ObservableList<Tache> Tache_luser=FXCollections.observableArrayList();
    TacheService ts = new TacheService();
     private ObservableList<String> choix=FXCollections.observableArrayList
        (
                "en train de réalisation",
                "réalisée"
        );
    private ObservableList<Message> messaesL=FXCollections.observableArrayList();
  
 
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       lblDateDebut.setVisible(false);
         lblDatefin.setVisible(false);
        lblDescription.setVisible(false);
        lblEtat.setVisible(false);
        comobEtat.setItems(choix);
        TxtAmessage.setEditable(false);
        TxtAmessage.setVisible(false);
        
        txtAdesc.setEditable(false);
        txtAdesc.setVisible(false);
        //************************** taches
         int id;
         id = UserService.loggeduser.getId();
         ObservableList<Tache> Tache_luser=ts.tacheLuser(id);
         tacheluser.setItems(Tache_luser);
         datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();
        
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.cellFactoryProperty();
        
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etat.cellFactoryProperty();
        
       tacheluser.setEditable(true);
       //*************************** messages
          ts.MessageUser(messaesL, id);

            message.setItems(messaesL);
            objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
            objet.cellFactoryProperty();
        
        date.setCellValueFactory(new PropertyValueFactory<>("datedenvoye"));
        date.cellFactoryProperty();
        
        text.setCellValueFactory(new PropertyValueFactory<>("text"));
        text.cellFactoryProperty();
        
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Front.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void consulter(ActionEvent event) {
        Tache t=tacheluser.getSelectionModel().getSelectedItem();
        
        if (t==null)
        {
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Aucue Tâche séléctionée");
            alert.setContentText("Veuillez Sélectionner une Tâche");
            alert.showAndWait();
        }
        else
        {
        java.sql.Date db=t.getDatedebut();
        java.sql.Date df=t.getDatefin();
        String etat=t.getEtat();
        String desc=t.getDescription();
        
        lblDateDebut.setText(db.toString());
        lblDatefin.setText(df.toString());
        txtAdesc.setText(desc);
        lblEtat.setText(etat);
        
        
        
         lblDateDebut.setVisible(true);
         lblDatefin.setVisible(true);
        txtAdesc.setVisible(true);
        lblEtat.setVisible(true);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        Tache t=tacheluser.getSelectionModel().getSelectedItem();
     
             if (t == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucune Tache séléctionnée");
            alert.setContentText("Veuillez Sélectionner une Tâche ");
            alert.showAndWait();
             }
      else
      {   int id=t.getId();
        //System.out.println("aaaaaaaaaaaa---- "+id);
        String etat1 =(String) comobEtat.getValue();    
        
        if (etat1==null)
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acun Sélection");
            alert.setHeaderText("Aucun Etat choisie ");
            alert.setContentText("Veuillez Chisir un Etat ");
            alert.showAndWait();
        }
        else
        {
        ts.updateEtat(t,id,etat1);
        //tacheluser.refresh();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESS");
            alert.setHeaderText(null);
            alert.setContentText("état modifier avec succées");
            alert.showAndWait();
        alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                        lblEtat.setText(etat1);
                     tacheluser.refresh();
                     this.refreshtable();
                }
            });}
    }
    }
    public void refreshtable(){
        TacheService ts= TacheService.getInstance();
      
        int id;
         id = UserService.loggeduser.getId();
         ObservableList<Tache> Tache_luser=ts.tacheLuser(id);
         tacheluser.setItems(Tache_luser);
            tacheluser.setItems(Tache_luser);
    }

    @FXML
    private void supprimer(ActionEvent event) {
         Message m = message.getSelectionModel().getSelectedItem();
          
          if (m == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Aucun message séléctionné");
            alert.setContentText("Veuillez Sélectionner un Message");
            alert.showAndWait();
    }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(message.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous vraiment supprimer ce méssage ?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    TacheService service = new TacheService();
                    service.deleteMsg(m.getId());
              
                    message.getItems().remove(message.getSelectionModel().getSelectedItem());
                    message.refresh();

                }
            });
          }
    }

    @FXML
    private void consulterMsg(ActionEvent event) {
         Message m=message.getSelectionModel().getSelectedItem();
         if (m==null)
         {
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Aucun message séléctionné");
            alert.setContentText("Veuillez Sélectionner un Message");
            alert.showAndWait();
         
         }
         else
         {
       String contenu=m.getText();
       TxtAmessage.setText(contenu);
       TxtAmessage.setVisible(true);
         }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/authentification.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void envoyerEmail(ActionEvent event) throws IOException {
         Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/email.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }
    
}
