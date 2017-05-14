/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import social_pro.entites.Jaime;
import social_pro.entites.JaimePas;
import social_pro.entites.Publication;
import social_pro.entites.fos_user;
import social_pro.service.JaimeService;
import social_pro.service.PublicationService;
import social_pro.service.UserService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class BackController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     private ObservableList<Publication> Liste_Publication = FXCollections.observableArrayList();
    private ObservableList<Publication> Liste_Re = FXCollections.observableArrayList();
    UserService userService = new UserService();
    public static fos_user loggedUser;
    Publication p = new Publication();
    PublicationService e = new PublicationService();
    JaimeService jaimeService = new JaimeService();
    static File path = new File("");
    private ObservableList<Jaime> Liste_Jaime = FXCollections.observableArrayList();
    private ObservableList<JaimePas> Liste_JaimePas = FXCollections.observableArrayList();
 private ObservableList<Publication> MesPublications = FXCollections.observableArrayList();
    static int id;
    @FXML
    private TableView<Publication> table;

    @FXML
    private TableColumn<Publication, String> TableStatut;

    @FXML
    private TableColumn<Publication, Date> DateAj;

    @FXML
    private TableColumn<Publication, Integer> jaimeCol;
    @FXML
    private TableColumn<Publication, Integer> jaimePasCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         e.listerback(Liste_Publication);
        System.out.println(Liste_Publication);
        table.setItems(Liste_Publication);
        TableStatut.setCellValueFactory(new PropertyValueFactory<>("text"));
        TableStatut.cellFactoryProperty();
        DateAj.setCellValueFactory(new PropertyValueFactory<>("dateAj"));
        DateAj.cellFactoryProperty();
        

        
        table.setEditable(true);
        this.refreshtable();
    }    
    
    @FXML
    private void supprimer(ActionEvent event) {
        Publication p = new Publication();

        p = table.getSelectionModel().getSelectedItem();
      
        PublicationService rs = new PublicationService();
        System.out.println(p.getId());
        rs.delete(p.getId());

        this.refreshtable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("La publication a eté supprimer avec success!");
        alert.show();
        }
        
    
    
    
     public void refreshtable() {

        ObservableList<Publication> list;
        list = FXCollections.observableArrayList(e.lister());
//            tableApprenant.setItems(list);
        table.setItems(list);
    }
        }
    
    

