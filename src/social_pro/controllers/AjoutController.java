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

 
import java.io.File;
import social_pro.entites.Produit;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import social_pro.service.ProduitService;

public class AjoutController {
static File path=new File("");
    @FXML
    private TextField LibelleP;

    @FXML
    private TextField TypeP;
    
    @FXML
    private Button ajoutBtn;
  
    @FXML
    private TextField PrixP;

    @FXML
    private TextField QuantiteP;

    @FXML
    private TextField ImageP;
      @FXML
    private Button choisirPhoto;

    @FXML
    private ImageView imageView;
    
    public void initialize(URL url, ResourceBundle rb) {
        Image img =null ;
        img = new Image(path.toURI().toString());
        imageView.setImage(img);
    }    
    
    @FXML
   public void Ajouter (ActionEvent event) {
  ProduitService Rs = new ProduitService();
       Produit R = new Produit();
       R.setLibelle(LibelleP.getText());
       R.setTypeproduit(TypeP.getText());
       R.setPrix( Double.parseDouble(PrixP.getText()));
       R.setQuantite(parseInt(QuantiteP.getText()));
       R.setPath(AjoutController.path.getAbsolutePath());
     
       
       Produit p = new Produit();
      
       Rs.ajouter(R);
    }
   
     @FXML
    void choisirPhoto(ActionEvent event) {
FileChooser fc = new FileChooser();
        File sf = fc.showOpenDialog(null);
        System.out.println("bbbbb");
        if (sf != null) {
            System.out.println("aaaaa");
            fc.setTitle("Ajouter");
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.jpeg"));
            if (sf != null) {
                AjoutController.path = sf.getAbsoluteFile();
                Image i = new Image(sf.toURI().toString());
                imageView.setImage(i);
                System.out.println("aaaaa");
            }
        }
    }
   

}

   
    

    

