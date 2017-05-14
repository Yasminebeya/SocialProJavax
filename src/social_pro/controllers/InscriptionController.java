/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.controlsfx.control.Notifications;
import social_pro.entites.BCrypt;
import social_pro.entites.User;
import social_pro.service.ControleSaisie;
import social_pro.service.EvenementService;
import social_pro.service.UserService;
import sun.misc.BASE64Encoder;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField username_canonical;
    @FXML
    private TextField email;
    @FXML
    private TextField email_canonical;
    @FXML
    private PasswordField password;
    @FXML
    private DatePicker datenaissance;
    @FXML
    private TextField tel;
    @FXML
    private TextField poste;
    @FXML
    private TextField departement;
    @FXML
    private Button annuler;
    @FXML
    private Button inscription;
    @FXML
    //private TextField sexe;
    private ComboBox sexe;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private ComboBox role;
    @FXML
    private Text emailAlert;

    UserService us = new UserService();
    private ObservableList<String> options = FXCollections.observableArrayList("Homme", "Femme");
    private ObservableList<String> role1 = FXCollections.observableArrayList("simple user", "chef");
    @FXML
    private ProgressBar pwdProg;
    @FXML
    private Text Mot_passAlert;
    @FXML
    private PasswordField confirmation;
    @FXML
    private Text ConfirmationAlert;
    @FXML
    private Text nomAlert;
    @FXML
    private Text prenomAlert;
    @FXML
    private Text Tel_Alert;
    @FXML
    private Text datealert;
    @FXML
    private Text usernameAlert;
    @FXML
    private Text userCanonicalAler;
    @FXML
    private Text emailCanonicalAlert;
    @FXML
    private Text emailConfirm;
    @FXML
    private Text usernameConfim;
    @FXML
    private ImageView image;
    @FXML
    private Label LImage;
    @FXML
    private Button imageUpload;

    File file;

    @FXML
    private void goInscri(ActionEvent event) throws IOException {

        try {
            User s;

            boolean inscrire = true;

            nomAlert.setText("");
            prenomAlert.setText("");
            datealert.setText("");
            emailAlert.setText("");
            Mot_passAlert.setText("");
            ConfirmationAlert.setText("");

            s = new User(nom.getText(), prenom.getText(), cin.getText(), (String) sexe.getValue(), username.getText(), username_canonical.getText(), email.getText(), email_canonical.getText(), us.hashPassword(password.getText()), new java.sql.Date(datenaissance.getValue().getYear() - 1900, datenaissance.getValue().getMonthValue() - 1, datenaissance.getValue().getDayOfMonth()), Integer.parseInt(tel.getText()), poste.getText(), departement.getText(), "", "http://localhost/pijava/uploads/" + username.getText() + ".jpg");
                    if(role.getValue()=="simple user"){
                        s.setRoles("a:0:{}");
        }
                    else if (role.getValue()=="chef")
                    {
                    s.setRoles("a:1:{i:0;s:9:\"ROLE_CHEF\";}");
                    }
            if (nom.getText().isEmpty()) {
                nomAlert.setText("Veuillez saisir votre nom ");
                inscrire = false;
            }
            if (prenom.getText().isEmpty()) {
                prenomAlert.setText("Veuillez saisir votre prenom");
                inscrire = false;
            }

            if (username.getText().isEmpty()) {
                usernameAlert.setText("Veuillez saisir votre username");
                inscrire = false;
            } else if (!username.getText().equals(username_canonical.getText())) {
                usernameConfim.setText("Les deux usernames ne correspondent pas");
                inscrire = false;
            } else if (us.getIdByUsername(username.getText()) != -1) {
                usernameAlert.setText("Username existe dÃ©jÃ ");
                inscrire = false;
            }

            if (datenaissance.valueProperty().getValue() == null) {
                datealert.setText("Veuillez selectionner votre date de naissance");
                inscrire = false;
            } else if (new Date().before(java.sql.Date.valueOf(datenaissance.valueProperty().getValue()))) {
                datealert.setText("Verifier la date saisie");
                inscrire = false;
            }
            boolean exact = false;
            for (int i = 1; i < email.getText().length() - 1; i++) {
                if (email.getText().charAt(i) == '@') {
                    exact = true;
                }
            }
            if (!exact) {
                emailAlert.setText("Ceci n'est pas un email");
                inscrire = false;
            } else {
                emailAlert.setText("");
            }
            if (email.getText().isEmpty()) {
                emailAlert.setText("Veuillez saisir votre e-mail ");
                inscrire = false;
            } else if (!email.getText().equals(email_canonical.getText())) {
                emailConfirm.setText("Les deux emails ne correspondent pas");
                inscrire = false;
            }

            if (password.getText().isEmpty()) {
                Mot_passAlert.setText("Veuillez saisir votre mot de passe");
                inscrire = false;
            } else if (!password.getText().equals(confirmation.getText())) {
                ConfirmationAlert.setText("Les deux mots de passes ne correspondent pas");
                inscrire = false;

            }
            if (inscrire) {
           
                us.add(s);
                try {
                    sendPost();
                } catch (Exception ex) {
                    Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Notifications notificationBuilder = Notifications.create()
                        .title("Votre inscription a été prise en compte")
                        .text("Veuillez attendre la confirmation de l'administrateur")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Un SMS de validation vous sera envoyé ");
                            }
                        });
                notificationBuilder.showInformation();

                Stage stage = (Stage) inscription.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onlyNumber(InputEvent event) {
        switch (cin.getText().charAt(cin.getLength() - 1)) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                break;

            default:
                String ref = cin.getText();
                cin.setText("");
                cin.insertText(0, ref.substring(0, ref.length() - 1));

                break;
        }
    }

    @FXML
    private void onlyNumber2(InputEvent event) {
        switch (tel.getText().charAt(tel.getLength() - 1)) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                break;
            case '+':
                if (tel.getLength() > 1) {
                    String ref = tel.getText();
                    tel.setText("");
                    tel.insertText(0, ref.substring(0, ref.length() - 1));

                }
                break;
            default:
                String ref = tel.getText();
                tel.setText("");
                tel.insertText(0, ref.substring(0, ref.length() - 1));

                break;
        }
    }

    @FXML
    private void usernameVerifAction(InputEvent event) {
        try {
            if (username.getText().isEmpty()) {
                usernameAlert.setText("Veuillez saisir votre username ");
            } else if (us.getIdByUsername(username.getText()) != -1) {
                usernameAlert.setText("Username existe déjà");
            } else {
                usernameAlert.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void emailVerifAction(InputEvent event) {
        try {
            boolean exact = false;
            for (int i = 1; i < email.getText().length() - 1; i++) {
                if (email.getText().charAt(i) == '@') {
                    exact = true;
                }
            }
            if (!exact) {
                emailAlert.setText("Ceci n'est pas un email");
            } else {
                emailAlert.setText("");
            }
            if (email.getText().isEmpty()) {
                emailAlert.setText("Veuillez saisir votre e-mail ");
            } else if (us.getIdByEmail(email.getText()) != -1) {
                emailAlert.setText("Cet email existe déjà");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void passwordVerifAction(InputEvent event) {
        ConfirmationAlert.setText("");
        int maj = 0, min = 0, number = 0, caracSp = 0, letter = 0;
        int securityCount = 0;
        securityCount = password.getText().length(); // la longueur du mot de passe, max point de sécurité 7
        if (securityCount > 7) {
            securityCount = 7;
        }
        for (int i = 0; i < password.getText().length(); i++) // voir combien de maj,min,chiffre et carac. spé  
        {
            if (Character.isUpperCase(password.getText().charAt(i))) {
                maj++;
            } else if (Character.isLowerCase(password.getText().charAt(i))) {
                min++;
            } else if (Character.isDigit(password.getText().charAt(i))) {
                number++;
            } else if (Character.isWhitespace(password.getText().charAt(i))) {
                caracSp++;
            } else {
                caracSp++;
            }
        }
        letter = maj + min;
        if (maj < min) {
            min = maj;
        }
        if (min > 5)// voir le nombre d'alternance entre maj et min / max point 5
        {
            securityCount += 5;
        } else {
            securityCount += min;
        }
        if (letter < number) //voir le nombre d'alternance entre chiffre et lettre max point 5
        {
            number = letter;
        }
        if (number > 5) {
            securityCount += 5;
        } else {
            securityCount += number;
        }
        if (caracSp > 4) // nombre de carac spé, max point 4 
        {
            securityCount += 4;
        } else {
            securityCount += number;
        }
        /*
         selon les points acquis : 
         1-7 : mot de passe faible
         8-14 : mot de passe moyen
         14-21 : mot de passe fort  
         */
        pwdProg.setVisible(true);
        if (securityCount != 0) {
            if (securityCount < 8) {
                Mot_passAlert.setText("Faible");

            } else if (securityCount < 15) {
                Mot_passAlert.setText("Moyen");
            } else {
                Mot_passAlert.setText("Fort");
            }
        } else {
            Mot_passAlert.setText("");
        }
        pwdProg.setProgress((double) securityCount / 21);

    }

    @FXML
    private void goAuth(ActionEvent event) throws IOException {
        Stage stage = (Stage) annuler.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sexe.setItems(options);
        role.setItems(role1);
        LImage.setVisible(false);
    }

    @FXML
    private void processUpload(ActionEvent event) throws Exception {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(exjpg, exjpg2, expng);
        fileChooser.setTitle("Choose an image File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                Image img = new Image(file.toURI().toString());
                image.setImage(img);
                image.setFitHeight(150);
                image.setFitWidth(150);
                LImage.setText(file.getName());

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \n  please choise another image");

            }
        }
    }

    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    // HTTP POST request
    private void sendPost() throws Exception {

      
        String url = "http://localhost/pijava/uploadImage.php";
       
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        //get image from fileChooser and convert tp BufferedImage
      
        BufferedImage image = ImageIO.read(file);
        //encode Image to base64 with jpg extension
    String param = encodeToString(image, "jpg");

       
        HttpClient client = new DefaultHttpClient();
        
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        // Parameteres
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

        urlParameters.add(new BasicNameValuePair("image", param));
        urlParameters.add(new BasicNameValuePair("username", username.getText()));

        //set parameteres to httpPost instance
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
      
        HttpResponse response = client.execute(post);
   
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());

    }

}
