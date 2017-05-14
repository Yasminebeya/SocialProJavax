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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import social_pro.entites.Conge;
import social_pro.service.CongeService;

public class ListeCongeController implements Initializable {

    @FXML
    private Text ajouterConge;

    @FXML
    private TableView<Conge> table;

    @FXML
    private TableColumn<Conge, Date> id_datedebut;

    @FXML
    private TableColumn<Conge,Integer> id_nb_jours;

    @FXML
    private TableColumn<Conge,String> Type_Conge;

    @FXML
    private TableColumn<Conge,String> Raison;
     @FXML
    private TableColumn<Conge,String> etat;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnLogout1;
     @FXML
    private Button btnsupp;
      @FXML
    private Button btn_modif;

   static int id;
   static Conge conge;
   Date xx;

    @FXML
            private ObservableList<Conge> Liste_Conge=FXCollections.observableArrayList();
    
    CongeService cs =new CongeService();
  
    
    void menuLogout(ActionEvent event) 
    {

    }

    @FXML
    void modifierChapitre(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cs.listeConge(Liste_Conge);
      
        table.setItems(Liste_Conge);
        /*idtache.setCellValueFactory(new PropertyValueFactory<>("id"));
        idtache.cellFactoryProperty();
        
     */
        
        id_datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        id_datedebut.cellFactoryProperty();
        
        id_nb_jours.setCellValueFactory(new PropertyValueFactory<>("nbrjours"));
        id_nb_jours.cellFactoryProperty();
        
         Type_Conge.setCellValueFactory(new PropertyValueFactory<>("type_conge"));
        Type_Conge.cellFactoryProperty();
        
        Raison.setCellValueFactory(new PropertyValueFactory<>("raison"));
         Raison.cellFactoryProperty();
         
         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         etat.cellFactoryProperty();
        
        table.setEditable(true);
    }
     @FXML
    void modifier(ActionEvent event) throws IOException {
       Conge c = table.getSelectionModel().getSelectedItem();
    
       Stage stage = (Stage) btn_modif.getScene().getWindow();
        id=c.getId();
     
         System.out.println("Raison du item selectionné"+c.getRaison());
         System.out.println("type du item selectionné"+c.getType_conge());
       Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/updateConge.fxml"));
       
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show(); 
         

    }
   
     
   
   @FXML
    void supprimer() {
        Conge c = table.getSelectionModel().getSelectedItem();
        CongeService cs = CongeService.getInstance();
       System.out.println("clicked");
 
       cs.remove(c.getId());
        this.refreshtable();
    }
     public void refreshtable(){
        CongeService sc= CongeService.getInstance();
        ObservableList<Conge> list=FXCollections.observableArrayList(sc.getAll()); 
//            tableApprenant.setItems(list);
             table.setItems(list);
    }
}


