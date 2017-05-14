/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.controlsfx.control.Notifications;
import social_pro.entites.Evenement;
import social_pro.entites.Participant;
import social_pro.entites.User;
import social_pro.interfaces.IEvenement;
import social_pro.interfaces.IUtilisateur;
import social_pro.service.EvenementService;
import social_pro.service.UserService;



/**
 * FXML Controller class
 *
 * @author smart
 */
public class EventsListAdminController implements Initializable {

     String baz;
    
    static Evenement ev;
    public static Evenement evMap;
    static Participant p;
    static List<Evenement> liste_Evs;

    static int id;
    static int id_user;

    static int idUser;
    static User u;
    @FXML
    private AnchorPane eventsList;
   
    @FXML
    private Button btn_pastEvents;
    @FXML
    private TableView<Evenement> tableEvents;
    @FXML
    private TableColumn<Evenement, String> categorie;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, Integer> nbre_limite;
    @FXML
    private TableColumn<Evenement, Date> datedebut;
    @FXML
    private TableColumn<Evenement, Date> datefin;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_retour;
    @FXML
    private TextField nom1;
    @FXML
    private TextField nbre_limite1;
    @FXML
    private DatePicker datedebut1;
    @FXML
    private DatePicker datefin1;
    @FXML
    private TextArea description1;
    @FXML
    private Button btn_add;
    @FXML
    private ComboBox categorie1;
    @FXML
    private Button btn_confirm;

    private ObservableList<Evenement> Liste_Evenement = FXCollections.observableArrayList();
    private ObservableList<Evenement> Liste_Event = FXCollections.observableArrayList();
    EvenementService es = new EvenementService();
    Evenement e = new Evenement();
    private ObservableList<String> options = FXCollections.observableArrayList("Profesionnel", "Loisirs");
    @FXML
    private TableColumn<Evenement, Integer> flag;
    @FXML
    private Text dateAlert;
     @FXML
    private Text dateAlert1;
    @FXML
    private Text nomAlert;
    @FXML
    private TextField search1;
    @FXML
    private Button btn_retour1;
    @FXML
    private Text nbreAlert;
    @FXML
    private TextField search;
    @FXML
    private Button btnMap;
    @FXML
    private TableColumn<Evenement,String> lieu;
    @FXML
    private Text lieuAlert;
    @FXML 
    private TextField lieu1;
    
     @FXML
     private Button uploadImage;
     @FXML
     private ImageView image;
    

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
             categorie1.setItems(options);
             es.afficher1(Liste_Evenement);
             
             tableEvents.setItems(Liste_Evenement);
             
             categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
             categorie.setCellFactory(TextFieldTableCell.forTableColumn());
             nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
             nom.setCellFactory(TextFieldTableCell.forTableColumn());
             description.setCellValueFactory(new PropertyValueFactory<>("description"));
             description.setCellFactory(TextFieldTableCell.forTableColumn());
             nbre_limite.setCellValueFactory(new PropertyValueFactory<>("nbrelimite"));
             nbre_limite.cellFactoryProperty();
             datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
             datedebut.cellFactoryProperty();
             datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
             datefin.cellFactoryProperty();
             flag.setCellValueFactory(new PropertyValueFactory<>("flag"));
             flag.cellFactoryProperty();
             lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
             lieu.setCellFactory(TextFieldTableCell.forTableColumn());
               
             tableEvents.setEditable(true);
        
    }
    
    private ObservableList<Evenement> Liste_event=FXCollections.observableArrayList();
    public void refreshtable(){
        EvenementService ev= EvenementService.getInstance().getInstance();
          
          ev.afficher1(Liste_event);
        tableEvents.setItems(Liste_event);   
    }

    @FXML
    private void goPastEvents(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_pastEvents.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLPastEventsList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_retour1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilAdmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void getEvent(MouseEvent event) throws IOException {
        Evenement ev = tableEvents.getSelectionModel().getSelectedItem();
        evMap = tableEvents.getSelectionModel().getSelectedItem();
        ev = tableEvents.getSelectionModel().getSelectedItem();
         int index = tableEvents.getSelectionModel().getSelectedIndex();
        id = ev.getId();
        System.out.println("+++++++id" + id);
        e = es.getEvenementById(id);
        nom1.setText(ev.getNom());
        categorie1.setValue(ev.getCategorie());
        description1.setText(ev.getDescription());
        lieu1.setText(ev.getLieu());
        nbre_limite1.setText(Integer.toString(ev.getNbrelimite()));
        datedebut1.setValue(datedebut.getCellData(index).toLocalDate());
        datefin1.setValue(datefin.getCellData(index).toLocalDate());    
    }

    @FXML
    public void modif(ActionEvent event) throws IOException {
        System.out.println("++++++++++++++++++++++++++++++++modif" + e.getId());
        if (ControleDeSaisie()) {
            e.setNom(nom1.getText());
            e.setCategorie((String) categorie1.getValue());
            e.setDescription(description1.getText());
            e.setNbrelimite(Integer.parseInt(nbre_limite1.getText()));
            e.setLieu(lieu1.getText());
            e.setDatedebut(new java.sql.Date(datedebut1.getValue().getYear() - 1900, datedebut1.getValue().getMonthValue() - 1, datedebut1.getValue().getDayOfMonth()));
            e.setDatedebut(new java.sql.Date(datefin1.getValue().getYear() - 1900, datefin1.getValue().getMonthValue() - 1, datefin1.getValue().getDayOfMonth()));        
            es.update(id, e);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("modification faite avec succes!");
            alert.showAndWait();
            this.refreshtable();
        
        }
    }
    
        @FXML
    private void ConfirmEvent(ActionEvent event) throws IOException {
        Evenement evenement = tableEvents.getSelectionModel().getSelectedItem();

        if (evenement == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'évènement que vous voulez confirmer !");
            alert.setContentText("Veuillez Sélectionner un évènement");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableEvents.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Voulez vous vraiment confirmer cet évènement");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    es.confirmEvent(evenement.getId());

                    System.out.println("++++++++++++++++++++" + evenement.getId());
                    //tableEvents.refresh();
                }
            });
            Stage stage = (Stage) btn_confirm.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLEventsListAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }
    private boolean ControleDeSaisie() {
        if (("".equals(description1.getText())) || ("".equals(nom1.getText())) || ("".equals(nbre_limite1.getText()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention!");
            alert.setHeaderText(null);
            alert.setContentText("Veuiller Remplir tous les champs !");
            alert.showAndWait();
            return false;

        }
        try {
            int x = Integer.parseInt(nbre_limite1.getText());
            return true;

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText("Veuiller saisir un entier dans le champ nombre limite de participants ");
            alert.showAndWait();
            return false;
        }

    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        Evenement evenement = tableEvents.getSelectionModel().getSelectedItem();

        if (evenement == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'évènement à supprimer !");
            alert.setContentText("Veuillez Sélectionner un évènement");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableEvents.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression Evènement");
            alert.setContentText("Voulez vous vraiment supprimer cet évènement");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    es.remove(evenement.getId());
                    System.out.println("++++++++++++++++++++" + evenement.getId());
                    tableEvents.getItems().remove(tableEvents.getSelectionModel().getSelectedItem());
                    tableEvents.refresh();
                }
            });
        this.refreshtable();
        }
    }

    @FXML
    private void goEventsList(ActionEvent event) throws IOException {

        Stage stage = (Stage) btn_retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/FXMLEventsList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     
    public void buildData2() {
        IEvenement su = new EvenementService();
        Liste_Event = FXCollections.observableArrayList();

        try {
            ResultSet r = su.recherche(search1.getText());
            while (r.next()) {
                Evenement e = new Evenement();
                e.setId(r.getInt("id"));
                e.setNom(r.getString("nom"));
                e.setCategorie(r.getString("categorie"));
                e.setDescription(r.getString("description"));
                e.setNbrelimite(r.getInt("nbrelimite"));
                e.setDatedebut(r.getDate("datedebut"));
                e.setDatefin(r.getDate("datefin"));
                e.setFlag(r.getInt("flag"));
                

                Liste_Event.add(e);

            }
            tableEvents.setItems(null);
            tableEvents.setItems(Liste_Event);
            

        } catch (SQLException ex) {
            Logger.getLogger(EventsListAdminController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rech(KeyEvent event) {
        tableEvents.refresh();
        EvenementService es = new EvenementService();
        buildData2();
    }


    @FXML
    private void add(ActionEvent event) {
        boolean addEvent=true;
      
            Evenement e;
             java.sql.Date dNow= new java.sql.Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),LocalDateTime.now().getDayOfMonth());
    
            java.sql.Date dDebut = new java.sql.Date(datedebut1.getValue().getYear() - 1900, datedebut1.getValue().getMonthValue() - 1, datedebut1.getValue().getDayOfMonth());
            java.sql.Date dFin = new java.sql.Date(datefin1.getValue().getYear() - 1900, datefin1.getValue().getMonthValue() - 1, datefin1.getValue().getDayOfMonth());

            this.baz = nom1.getText()+"-"+datedebut1.getValue().toString()+"-"+datefin1.getValue().toString();
            EvenementService ess = new EvenementService();
            e = new Evenement(nom1.getText(), (String) categorie1.getValue(), new java.sql.Date(datedebut1.getValue().getYear() - 1900, datedebut1.getValue().getMonthValue() - 1, datedebut1.getValue().getDayOfMonth()), new java.sql.Date(datefin1.getValue().getYear() - 1900, datefin1.getValue().getMonthValue() - 1, datefin1.getValue().getDayOfMonth()), description1.getText(), Integer.parseInt(nbre_limite1.getText()),lieu1.getText(),"http://localhost/pijava/uploads/" + baz+ ".jpg");
            es.confirmEvent(e.getId());
            System.out.println("++++++++++++"+ e.getId());
            
             if (nom1.getText().isEmpty()) {
            nomAlert.setText("Veuillez saisir le nom de l'évènement ");
            addEvent = false;
             }
             if (lieu1.getText().isEmpty()) {
            lieuAlert.setText("Veuillez saisir le lieu de l'évènement ");
            addEvent = false;
             }
              if (nbre_limite1.getText().isEmpty()) {
            nbreAlert.setText("Veuillez saisir le nombre des places disponibles");
            addEvent = false;
             }
             if(datedebut1.valueProperty().getValue()==null)
            {
            dateAlert.setText("Veuillez selectionner votre date de naissance");
            addEvent = false;
             }
             if(datefin1.valueProperty().getValue()==null)
            {
            dateAlert1.setText("Veuillez selectionner votre date de naissance");
            addEvent = false;
             }
            
             else if (dDebut.after(dFin)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRUER");
                alert.setHeaderText(null);
                alert.setContentText("Vérifier les dates saisies");
                alert.showAndWait();
                addEvent = false;
            }  
             
       
            
            
            if (addEvent)
            {
                ess.add(e);
            try {
                sendPost();
            } catch (Exception ex) {
                Logger.getLogger(EventsListAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                Notifications notificationBuilder = Notifications.create()
                        .title("Votre évènement a été ajouté avec succès")
                        .text("Veuillez confirmer votre évènement")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("");
                            }
                        });
                notificationBuilder.showInformation();
                this.refreshtable();
            }
        }
    
    
    @FXML
    private void onlyNumber(InputEvent event)
    {
       switch(nbre_limite1.getText().charAt(nbre_limite1.getLength()-1))
       {
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
               if(nbre_limite1.getLength()>1)
               {
               String ref = nbre_limite1.getText();
               nbre_limite1.setText("");
               nbre_limite1.insertText(0, ref.substring(0, ref.length()-1));
             
               }
               break;
           default: 
               String ref = nbre_limite1.getText();
               nbre_limite1.setText("");
               nbre_limite1.insertText(0, ref.substring(0, ref.length()-1));
                
               break;
       }    
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

//            BASE64Encoder encoder = new BASE64Encoder();
//            imageString = encoder.encode(imageBytes);
            
            imageString = DatatypeConverter.printBase64Binary(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    // HTTP POST request
    File file;
    private void sendPost() throws Exception {

      
        String url = "http://localhost/pijava/uploadImageEvent.php";
       
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
        urlParameters.add(new BasicNameValuePair("event", baz));

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

   @FXML
    private void VoirMap(ActionEvent actionEvent) throws IOException {
        Parent page3 = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/MapApi.fxml"));
        Scene scene = new Scene(page3);
    //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
