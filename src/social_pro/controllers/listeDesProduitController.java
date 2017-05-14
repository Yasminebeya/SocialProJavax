/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

/**
 *
 * @author Navoxx
 */



import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEvent;
import javafx.stage.Stage;
import social_pro.entites.Produit;
import social_pro.service.ProduitService;





public class listeDesProduitController implements Initializable {

    @FXML
    private Text ajouterTache;

    @FXML
    private TableView<Produit> tableproduit;

    @FXML
    private TableColumn<Produit, String> LibelleP;

    @FXML
    private TableColumn<Produit, String> TypeP;

    @FXML
    private TableColumn<Produit, Double> PrixP;

    @FXML
    private TableColumn<Produit, Integer> QuantiteP;

    @FXML
    private TableColumn<Produit, Image> ImageP; 
    
     @FXML
    private Button updateBtn;
/*
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnLogout1; */
    @FXML
    private Button btnsupp;
     @FXML
    private ImageView imageView;
                
    private ObservableList<Produit> Liste_Produit=FXCollections.observableArrayList();
    
     ProduitService es = new ProduitService();
  static int id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  
        es.listerback(Liste_Produit);
      
        tableproduit.setItems(Liste_Produit);
        
        /*idtache.setCellValueFactory(new PropertyValueFactory<>("id"));
        idtache.cellFactoryProperty();
     */
        LibelleP.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        LibelleP.cellFactoryProperty();
        
        TypeP.setCellValueFactory(new PropertyValueFactory<>("typeproduit"));
        TypeP.cellFactoryProperty();
        
         PrixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        PrixP.cellFactoryProperty();
        
        QuantiteP.setCellValueFactory(new PropertyValueFactory<>("quantite"));
         QuantiteP.cellFactoryProperty();
   
    }
    
  
    
    /* @FXML
    private void supprimer(ActionEvent event)
    {
          Produit prod = tableproduit.getSelectionModel().getSelectedItem();
          
          if (prod == null) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Aucune Publication");
            alert.setContentText("Veuillez Sélectionner une Publication");
            alert.showAndWait();
    }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableproduit.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous vraiment supprimer cette tâche ?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK ) {

                    ProduitService service = new ProduitService();
                    service.supprimer(prod.getId());
                    //System.out.println("ccccccccccccccccc   "+tache.getId());
                    tableproduit.getItems().remove(tableproduit.getSelectionModel().getSelectedItem());
                   tableproduit.refresh();

                }
            });
          }
    }  */
    
    public void refreshtable(){
        
        ObservableList<Produit> list; 
        list = FXCollections.observableArrayList(es.lister());
//            tableApprenant.setItems(list);
             tableproduit.setItems(list);}
    
    @FXML
     private void supprimer(ActionEvent event)
    {
     Produit prod = new Produit();
        prod = tableproduit.getSelectionModel().getSelectedItem();
        ProduitService rs = new ProduitService();
        System.out.println(prod.getId());
        rs.supprimer(prod.getId());
   
        this.refreshtable();
    }
     
     
      @FXML
            void modifier(ActionEvent event) throws IOException {
       Produit p = tableproduit.getSelectionModel().getSelectedItem();
    
       Stage stage = (Stage) updateBtn.getScene().getWindow();
         p =tableproduit.getSelectionModel().getSelectedItem();
        id=p.getId();
     
        
       Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/updateProduit.fxml"));
       
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show(); 
         

    }
     
     
     
      
       @FXML
    private void retour(ActionEvent event) 
    {
          
    }
    
    @FXML
    private void home(ActionEvent event)
    {
          
    }
    
    @FXML
    private void consulter(ActionEvent event)
    {
          
    } 
}
                  
    

