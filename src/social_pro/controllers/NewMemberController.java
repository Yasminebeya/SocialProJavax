/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import social_pro.entites.User;
import social_pro.interfaces.IUtilisateur;
import social_pro.service.UserService;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class NewMemberController implements Initializable {

    @FXML
    private AnchorPane MemberList;

    @FXML
    private TableView<User> tableMembers;

    @FXML
    private TableColumn<User, String> cin;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, Integer> etat;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> poste;
    @FXML
    private TableColumn<User, String> departement;

    @FXML
    private TableColumn<User, String> role;
    @FXML
    private Button btn_confirmMember;

    @FXML
    private Button btn_retour;

    @FXML
    private TextField search;

    @FXML
    private Button btn_delete;

    UserService us = new UserService();
    private ObservableList<User> Liste_User = FXCollections.observableArrayList();
    User u = new User();
    @FXML
    private Label nom1;
    @FXML
    private Label email1;
    @FXML
    private Label poste1;
    @FXML
    private Label departement1;
    @FXML
    private Label cin1;
    @FXML
    private Label tel1;
    @FXML
    private Label prenom1;
    @FXML
    private Label role1;
    @FXML
    private ImageView userPic;
    

    @FXML
    private void ConfirmMember(ActionEvent event) throws IOException {
        User user = tableMembers.getSelectionModel().getSelectedItem();
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'utilisateur que vous voulez confirmer !");
            alert.setContentText("Veuillez Sélectionner un utilisateur");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableMembers.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Voulez vous vraiment confirmer cette inscription");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    us.confirmMember(user.getId());
                    System.out.println("++++++++++++++++++++" + user.getId());
                    //tableEvents.refresh();
                }
            });
            Stage stage = (Stage) btn_confirmMember.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLNewMember.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           phoneNumber=user.getNum_telephone();
           String telNumber= Integer.toString(phoneNumber);
           System.out.println("num +++++++++++++++++"+telNumber);
       
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            
        Message message = Message.creator(new PhoneNumber("+21650911705"),
        new PhoneNumber("(201) 380-7485"), 
        "Félicitations ! Vous etes maintenant inscrit dans la plateforme Social Pro !").create();
         System.out.println(message.getSid()); 
        }
    }
    private static int phoneNumber;
 
 
    @FXML
    private void DeleteMember(ActionEvent event) {
        User user = tableMembers.getSelectionModel().getSelectedItem();
       
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'utilisateur que vous voulez ssupprimer !");
            alert.setContentText("Veuillez Sélectionner un membre");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableMembers.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression utilisateur");
            alert.setContentText("Voulez vous vraiment supprimer cet utilisateur");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    us.remove(user.getId());
                    System.out.println("++++++++++++++++++++" + user.getId());
                    tableMembers.getItems().remove(tableMembers.getSelectionModel().getSelectedItem());
                    tableMembers.refresh();
                }
            });
        }
    }
    
 static int id_user;
    @Override
public void initialize(URL url, ResourceBundle rb) {

        cin1.setVisible(false);
        nom1.setVisible(false);
        prenom1.setVisible(false);
        email1.setVisible(false);
        tel1.setVisible(false);
        poste1.setVisible(false);
        departement1.setVisible(false);
        role1.setVisible(false);
        
        us.afficher(Liste_User);

        tableMembers.setItems(Liste_User);

        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        cin.setCellFactory(TextFieldTableCell.forTableColumn());

        username.setCellValueFactory(new PropertyValueFactory<>("nom"));
        username.setCellFactory(TextFieldTableCell.forTableColumn());

        email.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        etat.setCellValueFactory(new PropertyValueFactory<>("enable"));
        etat.cellFactoryProperty();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.cellFactoryProperty();

        poste.setCellValueFactory(new PropertyValueFactory<>("poste"));
        poste.setCellFactory(TextFieldTableCell.forTableColumn());

        departement.setCellValueFactory(new PropertyValueFactory<>("departement"));
        departement.setCellFactory(TextFieldTableCell.forTableColumn());
        
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        role.setCellFactory(TextFieldTableCell.forTableColumn());

        tableMembers.setEditable(true);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilAdmin.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public void buildData2() {
        IUtilisateur su = new UserService();
        Liste_User = FXCollections.observableArrayList();

        try {
            ResultSet r = su.recherche(search.getText());
            while (r.next()) {
                User u = new User();
                u.setId(r.getInt("id"));
                u.setNom(r.getString("nom"));
                u.setEmail(r.getString("email"));
                u.setPrenom(r.getString("prenom"));
                u.setCin(r.getString("cin"));
                u.setPoste(r.getString("poste"));
                u.setDepartement(r.getString("departement"));
                u.setEnable(r.getInt("enable"));
                u.setRoles(r.getString("roles"));

                Liste_User.add(u);
            }
            tableMembers.setItems(null);
            tableMembers.setItems(Liste_User);

        } catch (SQLException ex) {
            Logger.getLogger(NewMemberController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rech(KeyEvent event) {
        tableMembers.refresh();
        UserService us = new UserService();
        buildData2();
    }
    public static final String ACCOUNT_SID = "AC8690552d691d1b8662e7f9e9568c043e";
    public static final String AUTH_TOKEN = "785a98e86a9eb861e4ccbfdd0108c61d";


    @FXML
    private void getUser(MouseEvent event) throws MalformedURLException, IOException {
        
        userPic.setImage(null);
       User user = tableMembers.getSelectionModel().getSelectedItem();
        user=tableMembers.getSelectionModel().getSelectedItem();
        id_user=user.getId();
        u= us.getUserbyId(id_user);
        System.out.println("+++++++++++++++++++++++"+id_user);
        cin1.setText(u.getCin());
        nom1.setText(u.getNom());
        prenom1.setText(u.getPrenom());
        email1.setText(u.getEmail());
        role1.setText(u.getRoles());
        
        //password.setText(u.getPassword());
       // datenaissance.setText(u.getDatenaissance());
        tel1.setText(Integer.toString(u.getNum_telephone()));
        poste1.setText(u.getPoste());
        departement1.setText(u.getDepartement());
        cin1.setVisible(true);
        nom1.setVisible(true);
        prenom1.setVisible(true);
        email1.setVisible(true);
        tel1.setVisible(true);
        
        URL url = new URL("http://localhost/pijava/uploads/"+ u.getUsername() +".jpg");
        System.out.println("URL IMAGE : "+url.toString());
        //read Image from URL
        BufferedImage bufferedImage = ImageIO.read(url);
        // convert buffered Image to FX Image using SwingFXUtils extension
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        userPic.setImage(image);
        
        poste1.setVisible(true);
        departement1.setVisible(true);
        role1.setVisible(true);
        
    }
}
