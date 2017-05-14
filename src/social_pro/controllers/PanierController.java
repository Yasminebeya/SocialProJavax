/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import static social_pro.controllers.gererProduitController.id;
import static social_pro.controllers.listeDesProduitController.id;
import social_pro.entites.Panier;
import social_pro.entites.Produit;
import social_pro.service.PanierService;
import social_pro.service.ProduitService;

/**
 *
 * @author Navoxx
 */
public class PanierController implements Initializable {

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
    private TableColumn<Produit, ImageView> ImageP;
    @FXML
    private Button showPanier;

    @FXML
    private Button AddPanier;
    @FXML
    private Button btnretour;
    @FXML
    private Button Louer;
    @FXML
    private Label LibProd;

    @FXML
    private Label TypeProd;
@FXML
private HBox ratinghbox;
    @FXML
    private Label QuantProd;

    @FXML
    private Label PrixProd;
   @FXML
    private Pane paneO;
    @FXML
    private ImageView imageView;
      @FXML
    private Rating rating;
    
    public String path1 = "file:///";

    private final ObservableList<Produit> Liste_Produit = FXCollections.observableArrayList();
    private final ObservableList<Panier> Liste_Panier = FXCollections.observableArrayList();
    ProduitService es = new ProduitService();
    PanierService ps = new PanierService();
    static int id;

/*private Rating rating;*/
    
    
    public void selectProduit() {
        Produit p = tableproduit.getSelectionModel().getSelectedItem();
        id = p.getId();
    }

    @FXML
    void AddtoPanier(ActionEvent event) {
        Produit p = tableproduit.getSelectionModel().getSelectedItem();
        id = p.getId();

        ps.addProduit(id);
        Alert al = new Alert(Alert.AlertType.INFORMATION, "Produit Ajouter avec Succes", ButtonType.OK);

        al.show();
    }

    @FXML
    void AffichePanier(ActionEvent event) throws IOException {
        Stage stage = (Stage) showPanier.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Monpanier.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

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
          /* rating = new Rating();*/
        tableproduit.setEditable(true);
paneO.setVisible(false);
        tableproduit.setOnMouseClicked(event -> {

            LibProd.setText(String.valueOf(tableproduit.getSelectionModel().getSelectedItem().getLibelle()));
            TypeProd.setText(String.valueOf(tableproduit.getSelectionModel().getSelectedItem().getTypeproduit()));
            PrixProd.setText(String.valueOf(tableproduit.getSelectionModel().getSelectedItem().getPrix()));
            QuantProd.setText(String.valueOf(tableproduit.getSelectionModel().getSelectedItem().getQuantite()));

         double s=tableproduit.getSelectionModel().getSelectedItem().getRating2();
            System.out.println("    dazzaaz"+s);
            
           
            rating.ratingProperty().setValue(s);
            
            String path = tableproduit.getSelectionModel().getSelectedItem().getPath();
            path = path1 + path;

            Image image = new Image(path);
            imageView.setImage(image);

            Produit p = new Produit();
            p.setPath(path);
            
            
            FadeTransition fade = new FadeTransition(Duration.seconds(1),paneO);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
            
            TranslateTransition ft = new TranslateTransition(Duration.seconds(1),paneO);
            ft.setFromX(-100);
            ft.setToX(0);
            ft.play();
            
//            Publication p =new Publication();
//          p.setPath(path2);  
paneO.setVisible(true);
        });


            
     
    
       
     
  
    }

    @FXML
    void Louer(ActionEvent event) throws IOException {
       Produit p = tableproduit.getSelectionModel().getSelectedItem();
        id = p.getId();
        System.out.println("idddddddddd="+id);
        Stage stage = (Stage) Louer.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/LouerProduit.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }
    
    
   

    @FXML
    void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnretour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilUser.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

}
