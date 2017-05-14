package social_pro.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import social_pro.entites.Tache;
import social_pro.entites.User;
import social_pro.entites.fos_user;
import social_pro.service.TacheService;
import social_pro.service.UserService;

public class AuthentificationController implements Initializable {

    TacheService t = new TacheService();

    UserService us = new UserService();

    Media media;
    MediaPlayer mediaPlayer;
    static int id_u;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnlogin;
    @FXML
    private Button inscription;
    @FXML
    private AnchorPane pswOublie;
    @FXML
    private ImageView logoText;
    @FXML
    private Button btn_sound;
    @FXML
    private ImageView SoundOff;
    @FXML
    private ImageView SoundOn;
    @FXML
    private Button btn_on;

    @FXML
    private void goInscri(ActionEvent event) throws IOException {
        Stage stage = (Stage) inscription.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Inscri.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {

        UserService us = new UserService();
        if (us.existeUtilisateur(username.getText())) {

            User user = us.findUtilisateurByLoginMdp(username.getText());
            if (user != null) {
                System.out.println("user is not null");
            } else {
                System.out.println("user is null");
            }

            String passwordStored = user.getPassword();
            passwordStored = passwordStored.substring(4, passwordStored.length());
            passwordStored = "$2a$" + passwordStored;
            System.out.println("++++++ passwordStored" + passwordStored);
            
            System.out.println("+++++++++" + password.getText());

            boolean passwordMatch = us.checkPassword(password.getText(), passwordStored);
            System.out.println(""+passwordMatch);

            if (passwordMatch == true && user.getRoles().contains("ADMIN")) {
                System.out.println("ROLE ADMIN");
                Stage stage = (Stage) btnlogin.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilAdmin.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (passwordMatch == true && user.getRoles().contains("CHEF")) {
                System.out.println("ROLE CHEF");

                Stage stage = (Stage) btnlogin.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilChef.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else if (passwordMatch == true) {
                System.out.println("ROLE MEMBRE");
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/social_pro/Fxml/Front.fxml"));
                Parent root = fxmlloader.load();
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene sene = new Scene(root);
                s.setScene(sene);
                s.show();
            }
            id_u = user.getId();
            System.out.println("haha" + id_u);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ce compte n'existe pas");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez vÃ©rifier vos coordonnÃ©es");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btn_on.setVisible(false);
        SoundOn.setVisible(false);
        String path = "C:\\Users\\Oumaima\\Documents\\NetBeansProjects\\social_pro\\src\\social_pro\\ressources\\Gramatik.mp3";
        path = path.replace("\\", "/");

        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

    }

    @FXML
    public void soundOff(ActionEvent event) {

        mediaPlayer.setRate(0.0);

        btn_on.setVisible(true);
        SoundOn.setVisible(true);
        btn_sound.setVisible(false);
        SoundOff.setVisible(false);

    }

    @FXML
    public void SoundOn(ActionEvent event) {

        mediaPlayer.setRate(1.0);
        btn_on.setVisible(false);
        SoundOn.setVisible(false);

        btn_sound.setVisible(true);
        SoundOff.setVisible(true);

    }

//    @FXML
//    void cnx(ActionEvent event) throws IOException{
//        
//         UserService userService = new UserService();
//        
//        //System.out.println("" + txtUserName.getText());
//        //System.out.println("" + txtPassWord.getText());
//     
//        
//        if (userService.existeUtilisateur(txtUserName.getText()))
//                {
//                     fos_user user = userService.findUtilisateurByLoginMdp(txtUserName.getText());
//                   
//                     
//                       if (user.getPassword().contains(txtPassWord.getText())&&user.getRole().contains("ADMIN")) 
//                       {
//                          System.out.println("ROLE ADMIN");
//                    Stage stage = new Stage();
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/social_pro/Fxml/gererProduitBack_1.fxml"));
//                    Parent root = loader.load();
//                    Scene scene = new Scene(root);
//                    stage.initStyle(StageStyle.UNDECORATED);
//                    stage.setScene(scene);
//                    stage.show();
//                    
//                       }
//                     else  if(user.getPassword().contains(txtPassWord.getText())&&user.getRole().contains("CHEF"))
//                         {
//                             System.out.println("ROLE CHEF");
//                
//                                
//                                Stage stage = (Stage) btncnx.getScene().getWindow();
//                                 Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilChef.fxml")); 
//                                 Scene scene = new Scene(root); stage.setScene(scene);
//                                     stage.show(); 
//                                    
//                         }
//                       
//                     else if (user.getPassword().contains(txtPassWord.getText()))
//                       {
//                           
//                          // System.out.println("ROLE MEMBRE");
//                          Stage stage = (Stage) btncnx.getScene().getWindow();
//                            Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Front.fxml")); 
//                             Scene scene = new Scene(root);
//                             stage.setScene(scene);
//                             stage.show();   
//                           
//                          }
//                     else{
//                             Alert alert = new Alert(Alert.AlertType.WARNING);
//                             alert.setTitle("Connéxion Impossible");
//                             alert.setHeaderText(null);
//                             alert.setContentText("Vérifier Votre mot de passe");
//                             alert.showAndWait();
//                     }
//                          
//                             
//                }
//        else
//        {
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//                             alert.setTitle("Connéxion Impossible");
//                             alert.setHeaderText(null);
//                             alert.setContentText("Vous n'êtes Pas Inscrit Sur SocialPro Veuillez Créer Un Compte");
//                             alert.showAndWait();
//        }
//        
//        
//    }
//    
}
