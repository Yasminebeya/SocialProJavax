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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import social_pro.entites.Evenement;
import social_pro.service.EvenementService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class PastEventsListController implements Initializable {

   
        @FXML
    private Button btn_statistique;   
      
        @FXML
    private Button btn_retour;
    
     @FXML
    private TableView<Evenement> tableEvents;
     
      @FXML
    private TableColumn<Evenement,String> categorie;
     
    @FXML
    private TableColumn<Evenement,String> nom;
    @FXML
    private TableColumn<Evenement,String> description;
    @FXML
    private TableColumn<Evenement,Integer> nbre_limite;
    
    @FXML
    private TableColumn<Evenement,Date> datedebut;
    
     @FXML
    private TableColumn<Evenement,Date> datefin;
    
     
    
   
     private ObservableList<Evenement> Liste_Evennement=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Evenement, Integer> id;
    @FXML
    private TableColumn<Evenement, Integer> nbrparticipants;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               EvenementService e1=new EvenementService();
               
               
        e1.afficher2(Liste_Evennement);
        
        tableEvents.setItems(Liste_Evennement);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.cellFactoryProperty();
            
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        categorie.setCellFactory(TextFieldTableCell.forTableColumn());
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.setCellFactory(TextFieldTableCell.forTableColumn());
            
        nbre_limite.setCellValueFactory(new PropertyValueFactory<>("nbrelimite"));
        nbre_limite.cellFactoryProperty();
        
        nbrparticipants.setCellValueFactory(new PropertyValueFactory<>("nbrparticipants"));
        nbrparticipants.cellFactoryProperty();
        
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();
        
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();
     
        tableEvents.setEditable(true);
    }   
    
        @FXML
    private void goEventsList(ActionEvent event) throws IOException
    {
           Stage stage = (Stage) btn_retour.getScene().getWindow();
       Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLEventsListAdmin.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    
    }
    
        @FXML
    private void goStatistique(ActionEvent event) throws IOException
    {
        Parent page3 = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLStatistique.fxml"));
        Scene scene = new Scene(page3);
    //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    
    }
    }    
    

