/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.web.WebEvent;
import javafx.stage.Stage;
import social_pro.entites.Panier;
import social_pro.entites.Produit;
import social_pro.service.ProduitService;
import social_pro.service.PanierService;
import social_pro.service.UserService;



public class showPanierController  implements Initializable {
 @FXML
    private TableView  <Produit>  tablePanier ;

    @FXML
    private TableColumn<Produit, String> LibelleP;

    @FXML
    private TableColumn<Produit, String> TypeP;

    @FXML
    private TableColumn<Produit, Double> PrixP;
    @FXML
    private TableColumn<Produit, Double> sommeP;
     @FXML
    private Label SommeP;
    @FXML
    private Button ValiderPanier;
     @FXML
    private Button btnretour;
     private ObservableList<Produit> Liste_Produit=FXCollections.observableArrayList();
    Produit p= new Produit();
    PanierService es = new PanierService();
    ProduitService ps = new ProduitService();
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         int user = UserService.loggeduser.getId();
        es.showPanier(Liste_Produit,user);
      
        tablePanier.setItems(Liste_Produit);
     
        LibelleP.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        LibelleP.cellFactoryProperty();
        
        TypeP.setCellValueFactory(new PropertyValueFactory<>("typeproduit"));
        TypeP.cellFactoryProperty();
        
        PrixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        PrixP.cellFactoryProperty();
        int idu = UserService.loggeduser.getId();
       SommeP.setText(Double.toString(es.CalculerPanier(idu))+"  $");
    }
      @FXML
    void ValiderPanier(ActionEvent event) throws SQLException {
        Panier p = new Panier();
        es.ValiderPanier(p);
        tablePanier.getItems().clear();
 /* tablePanier.setVisible(false); */
  
  SommeP.setVisible(false);
   Alert al = new Alert(Alert.AlertType.INFORMATION, "Votre Panier Valider avec Succes", ButtonType.OK);
      
                al.show();
  
    }
    
    
    @FXML
    void retour(ActionEvent event) throws IOException {
         Stage stage = (Stage) btnretour.getScene().getWindow();
  Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/listeProduitFront.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

     

    }
