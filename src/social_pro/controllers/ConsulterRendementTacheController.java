/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import social_pro.entites.Tache;
import social_pro.entites.fos_user;
import social_pro.service.TacheService;


public class ConsulterRendementTacheController implements Initializable {
    

  
@FXML
    private Button btnRetour;
    @FXML
    private Button btnlogout;
    @FXML
    private TableView<Tache> tableRendement;
    @FXML
    private TableColumn<Tache, Date> datedebut;
    @FXML
    private TableColumn<Tache, Date> datefin;
    @FXML
    private TableColumn<Tache, String> description;
    @FXML
    private TableColumn<Tache, String> etat;
      @FXML
     private JFXTextField nom;
       @FXML
    private Pane pane;
   
   

    private ObservableList<Tache> rendement=FXCollections.observableArrayList();
     TacheService ts = new TacheService();
  
   
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    tableRendement.setVisible(false);
    }    


    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AjouterTache.fxml")); 
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
    private void txtNom(ActionEvent event) {   
        
        String nomuser = nom.getText();
        
       int id= ts.nomUser(nomuser);
        rendement=ts.tacheLuser(id);
       
            tableRendement.setVisible(true);
            tableRendement.setItems(rendement);
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datedebut.cellFactoryProperty();
        
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        datefin.cellFactoryProperty();
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.cellFactoryProperty();
        
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etat.cellFactoryProperty();
        
       tableRendement.setEditable(true);
     
        
        
        
    }
      

    
}
