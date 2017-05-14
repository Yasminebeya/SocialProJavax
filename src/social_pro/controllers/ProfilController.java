/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import social_pro.entites.Evenement;
import social_pro.entites.User;
import social_pro.service.UserService;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class ProfilController implements Initializable {

    @FXML
    private Text email;
    @FXML
    private Text password;
    @FXML
    private Text datenaissance;
    @FXML
    private Text tel;
    @FXML
    private Text poste;
    @FXML
    private Text departement;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    @FXML
    private Text sexe;
    @FXML
    private Text username;
    @FXML
    private ImageView userPic;

    @FXML
    private Button btn_retour;

    static int id = UserService.loggeduser.getId();
    String Image = "";
    @FXML
    private Label LImageA;
    @FXML
    private ImageView AfficheImage;

    @FXML
    private Button btnSupp1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            UserService us = new UserService();
            User u;
            
            System.out.println("+++++++++++++" + id);
            u = us.getUserbyId(id);
            nom.setText(u.getNom());
            prenom.setText(u.getPrenom());
            sexe.setText(u.getSexe());
            username.setText(u.getUsername());
            password.setText(u.getPassword());
            // datenaissance.setText(u.getDatenaissance());
            email.setText(u.getEmail());
            tel.setText(Integer.toString(u.getNum_telephone()));
            poste.setText(u.getPoste());
            departement.setText(u.getDepartement());
            URL url2 =null;
            url2 = new URL("http://localhost/pijava/uploads/" + UserService.loggeduser.getUsername() + ".jpg");
            
            System.out.println("URL IMAGE : " + url2.toString());
            //read Image from URL
            BufferedImage bufferedImage = ImageIO.read(url2);
            // convert buffered Image to FX Image using SwingFXUtils extension
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            userPic.setImage(image);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modif(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnModif.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/ModifProfil.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void goEventsList(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Front.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    @FXML
    private void supp(ActionEvent event) throws IOException {

        UserService us = new UserService();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(btnSupp.getScene().getWindow());
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez vous vraiment supprimer votre compte");
        alert.setContentText("NB: Si vous accepter de désactiver votre compte vous ne pouvez pas le récuperer de nouveau ");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                us.remove(UserService.loggeduser.getId());
            }
        });
        Stage stage = (Stage) btnSupp.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
;



  @FXML
    private void desactiver(ActionEvent event) throws IOException {

        UserService us = new UserService();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(btnSupp1.getScene().getWindow());
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez vous vraiment desactiver votre compte");
        alert.setContentText("NB: Si vous accepter de désactiver votre compte vous pouvez le récuperer de nouveau en envoyant une demande à l'administrateur ");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                us.desactiverCompte(UserService.loggeduser.getId());
            }
        });
        Stage stage = (Stage) btnSupp1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/FXML/FXMLAuthentification.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
;

}

