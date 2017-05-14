/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Blob;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import social_pro.entites.Publication;
import social_pro.service.PublicationService;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.beans.property.BooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import social_pro.entites.Jaime;
import social_pro.entites.fos_user;
import social_pro.service.JaimeService;
import social_pro.service.UserService;
import static social_pro.service.UserService.loggeduser;
import java.sql.ResultSet;
import java.util.List;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TablePosition;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import social_pro.entites.JaimePas;
import social_pro.service.JaimePasService;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Navoxx
 */
public class GererPublicationkController implements Initializable {

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
    private Button BtnPdf;
    @FXML
    private Button pie;
    @FXML
    private Button BtnStat;
 @FXML
    private JFXButton BtnJaime;
    @FXML
    private ImageView imageAff;
    @FXML
    private JFXButton Logout1;

    @FXML
    private Pane details;
 @FXML
    private JFXButton JaimePas;
    @FXML
    private Label StatutDetails;

    @FXML
    private Label StatutDetailYodh;
    @FXML
    private JFXButton MesPub;
    @FXML
    private Label AjoutéParYodh;

    @FXML
    private Label AjoutéLeYodh;
    @FXML
    private ImageView imageView;

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
    @FXML
    private Button updateBtn;
    @FXML
    private Button updateBtn1;
    @FXML
    private Button visualiser;
    @FXML
    private Button addBtn;
    @FXML
    private Button suppBtn;
    
    @FXML
    private TextField StatutText;

    @FXML
    private Button BtnRechercher;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;
    public String path1 = "file:///";

    @FXML
    private Label ajoutépar;
    @FXML
    private Label ajoutéle;
    @FXML
    private Label photodetai;
    @FXML
    private Button Produitimage;
    @FXML
    private ImageView imageYodh;
    @FXML
    private AnchorPane navList2;
    @FXML
    private Pane menu2;
 @FXML
    private JFXButton Retour1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BtnJaime.setVisible(false);
        JaimePas.setVisible(false);
        details.setVisible(false);
        BtnStat.setVisible(false);
        e.listerback(Liste_Publication);
        System.out.println(Liste_Publication);
        table.setItems(Liste_Publication);
        TableStatut.setCellValueFactory(new PropertyValueFactory<>("text"));
        TableStatut.cellFactoryProperty();
        
        jaimeCol.setCellValueFactory(new PropertyValueFactory<>("jaime"));
        jaimeCol.cellFactoryProperty();
        DateAj.setCellValueFactory(new PropertyValueFactory<>("dateAj"));
        DateAj.cellFactoryProperty();
        jaimePasCol.setCellValueFactory(new PropertyValueFactory<>("jaimePas"));
        jaimePasCol.cellFactoryProperty();

        //  ImageP1.setCellValueFactory(new PropertyValueFactory<>("path"));
        // ImageP1.cellFactoryProperty();
        //  ImageView imageview = new ImageView();
        // Image image2=new Image("C:\\Users\\ahmed\\Desktop\\cou.jpg");
        // imageView.setImage(image2);
        // imageview.setFitHeight(50);
        // imageview.setFitWidth(50);
        // ImageP1.setGraphic(imageview);
        table.setEditable(true);
        this.refreshtable();

        table.setOnMouseClicked(event -> {

                   
            visualiserAction(null);
            FadeTransition fade = new FadeTransition(Duration.seconds(1),details);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
            
            TranslateTransition ft = new TranslateTransition(Duration.seconds(1), details);
            ft.setFromX(-100);
            ft.setToX(0);
            ft.play();
            
            TranslateTransition jaime = new TranslateTransition(Duration.seconds(1), BtnJaime);
            ft.setFromX(-100);
            ft.setToX(0);
            ft.play();
            
            BtnJaime.setVisible(true);
            
            TranslateTransition jaimepas = new TranslateTransition(Duration.seconds(1), JaimePas);
            ft.setFromX(-100);
            ft.setToX(0);
            ft.play();
            
                   JaimePas.setVisible(true);

        });

    }

    public void refreshtable() {

        ObservableList<Publication> list;
        list = FXCollections.observableArrayList(e.lister());
//            tableApprenant.setItems(list);
        table.setItems(list);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Publication p = new Publication();

        p = table.getSelectionModel().getSelectedItem();
        if (p.getUserId()==userService.loggeduser.getId()){
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
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Ce n'est pas votre publication!");
        alert.show();
        try {
                File file = new File("C:\\Users\\ahmed\\Documents\\NetBeansProjects\\social_pro123\\src\\social_pro\\images\\warning.mp3");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                try {
                    Player player = new Player(bis);

                    player.play();

                } catch (JavaLayerException ex) {

                }
            } catch (IOException E) {

            }
        }
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {

        Publication p = table.getSelectionModel().getSelectedItem();
  if (p.getUserId()==userService.loggeduser.getId()){
        p = table.getSelectionModel().getSelectedItem();
        id = p.getId();
        System.out.println("id=" + id);
        p = e.getPublicationById(id);

        
              Publication p1 = table.getSelectionModel().getSelectedItem();
     String  path2 = p1.getPath();
       path2= path1 + path2 ;
         Image image=new Image(path2);
      imageView.setImage(image);
        StatutText.setText((p.getText()));
        
  }
else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Ce n'est pas votre publication!");
        alert.show();
        
        try {
                File file = new File("C:\\Users\\ahmed\\Documents\\NetBeansProjects\\social_pro123\\src\\social_pro\\images\\warning.mp3");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                try {
                    Player player = new Player(bis);

                    player.play();

                } catch (JavaLayerException ex) {

                }
            } catch (IOException E) {

            }
        }
    }

    @FXML
    void modifier2(ActionEvent event) throws IOException {
       
        p.setText(StatutText.getText());
         p.setPath(GererPublicationkController.path.getAbsolutePath());
        e.updatefront(id, p);
        this.refreshtable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("modification faite avec succes!");
        alert.show();

    }


    @FXML
    private void ajout(ActionEvent event) {
        table.setVisible(true);
if (validateFields()){
        PublicationService rs = new PublicationService();
        Publication R = new Publication();

        R.setText(StatutText.getText());
        R.setPath(GererPublicationkController.path.getAbsolutePath());

        

            rs.add(R);

            this.refreshtable();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("Ajout fait avec succes!");
            alert.show();
}
            

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
                GererPublicationkController.path = sf.getAbsoluteFile();
                Image i = new Image(sf.toURI().toString());
                imageView.setImage(i);
                System.out.println("aaaaa");
            }

        }
    }

    @FXML
    void Rechercher(ActionEvent event) {
        imageAff.setVisible(false);
        table.setVisible(true);
        LocalDate date3 = date1.getValue();
        Date date4 = java.sql.Date.valueOf(date3);

        LocalDate date5 = date2.getValue();
        Date date6 = java.sql.Date.valueOf(date5);

        if (date4.compareTo(date6) == -1 || date4.compareTo(date6) == 0) {
            e.listerback1(Liste_Re, date4, date6);

            table.setItems(Liste_Re);

            TableStatut.setCellValueFactory(new PropertyValueFactory<>("text"));
            TableStatut.cellFactoryProperty();

            DateAj.setCellValueFactory(new PropertyValueFactory<>("dateAj"));
            DateAj.cellFactoryProperty();

            table.setEditable(true);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre inferieur a la date fin!");
            alert.show();
        }
    }

    @FXML
    void StatAction(ActionEvent event) {
        try {
            Parent pagePieChart = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/BarChart.fxml"));
            Scene scene = new Scene(pagePieChart);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PdfAction(ActionEvent event) {
        table.setOnMouseClicked(event1 -> {
            int x = table.getSelectionModel().getSelectedItem().getId();

        });
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("c:/pdf/" + table.getSelectionModel().getSelectedItem().getId() + ".pdf"));
            document.open();
            document.add(new Paragraph("********************************La publication imprimé*************************************"+"\n"+"\n"+"\n"+"le statut de la publication :" + " " +" "+ table.getSelectionModel().getSelectedItem().getText() + " \n" +"\n"+"Publié par : "+table.getSelectionModel().getSelectedItem().getPubUser()+"\n"+"\n"+ "Ajouté le :" + " " + table.getSelectionModel().getSelectedItem().getDateAj()+"\n"));
            document.addTitle("title");
            document.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("pdf avec success");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("la publication a été imprimer en PDF avec success");
        alert.show();

    }

    @FXML
    void ajoutJaime(ActionEvent event) {
        table.setVisible(true);

        JaimePasService rs1 = new JaimePasService();

        JaimeService rs11 = new JaimeService();
        PublicationService r = new PublicationService();
        Publication p1 = table.getSelectionModel().getSelectedItem();
        Jaime R = new Jaime();

        R.setIdPublication(p1.getId());

        R.setIdUser(userService.loggeduser.getId());
        String x = null;

        rs1.listerJaimePas(Liste_JaimePas);
        rs11.listerJaime(Liste_Jaime);
        System.out.println("" + Liste_Jaime);
        for (Jaime i : Liste_Jaime) {
 for (JaimePas j : Liste_JaimePas) {
           // System.out.println("hahahahahhah" + i.getIdPublication());
           // System.out.println("" + R.getIdPublication());
           // System.out.println("" + i.getIdUser());
           // System.out.println("" + R.getIdUser());

            if (R.getIdUser() == i.getIdPublication() && R.getIdPublication() == i.getIdUser()) {

                x = "true";

            }
            
if (R.getIdUser() == j.getIdPublication() && R.getIdPublication() == j.getIdUser()) {
                    x = "truee";
                   
                }
         }
        }
        if (x == null) {

            rs11.addJaime(R);
            p1.setJaime(p1.getJaime() + 1);
            r.updatefrontJaime(p1.getId(), p1);

            this.refreshtable();

        }

        if (x=="truee"){
            
            rs11.addJaime(R);
            p1.setJaime(p1.getJaime() + 1);
            p1.setJaimePas(p1.getJaimePas() - 1);
            r.updatefrontJaime(p1.getId(), p1);
            r.updatefrontJaimePas(p1.getId(), p1);
           //rs1.delete(p1.getId(), p1.getUserId());
            this.refreshtable();
            
        }
        if (x == "true") {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("deja aimé!");
            alert.show();
            try {
                File file = new File("C:\\Users\\ahmed\\Documents\\NetBeansProjects\\social_pro123\\src\\social_pro\\images\\warning.mp3");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                try {
                    Player player = new Player(bis);

                    player.play();

                } catch (JavaLayerException ex) {

                }
            } catch (IOException E) {

            }

        }
    }

    @FXML
    void JaimePas(ActionEvent event) {
        table.setVisible(true);

        JaimePasService rs11 = new JaimePasService();
        JaimeService rs1 = new JaimeService();

        PublicationService r = new PublicationService();
        Publication p1 = table.getSelectionModel().getSelectedItem();
        JaimePas R = new JaimePas();

        R.setIdPublication(p1.getId());

        R.setIdUser(userService.loggeduser.getId());
        String x = null;

        rs11.listerJaimePas(Liste_JaimePas);
        rs1.listerJaime(Liste_Jaime);
        System.out.println("" + Liste_JaimePas);
        for (JaimePas i : Liste_JaimePas) {
            for (Jaime j : Liste_Jaime) {
               // System.out.println("hahahahahhah" + i.getIdPublication());
              //  System.out.println("" + R.getIdPublication());
               // System.out.println("" + i.getIdUser());
               // System.out.println("" + R.getIdUser());
                
                
 
                if (R.getIdUser() == i.getIdPublication() && R.getIdPublication() == i.getIdUser()) {

                    x = "true";
                }
                if (R.getIdUser() == j.getIdPublication() && R.getIdPublication() == j.getIdUser()) {
                    x = "truee";
                   
                }

            }
        }
        if (x == null) {

            rs11.addJaimePas(R);

            p1.setJaimePas(p1.getJaimePas() + 1);

            r.updatefrontJaimePas(p1.getId(), p1);

            this.refreshtable();

        }
        if (x == "truee") {
            rs11.addJaimePas(R);
            p1.setJaime(p1.getJaime() - 1);
            p1.setJaimePas(p1.getJaimePas() + 1);
            r.updatefrontJaime(p1.getId(), p1);
            r.updatefrontJaimePas(p1.getId(), p1);
           rs1.delete(p1.getId(), p1.getUserId());
            this.refreshtable();
        }
        if (x == "true") {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("deja signalé!");
            alert.show();

        }
        
    }

    @FXML
    void pieChart(ActionEvent event) {

        try {
            Parent pagePieChart = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/PieChart.fxml"));
            Scene scene = new Scene(pagePieChart);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void visualiserAction(ActionEvent event) {
        Publication p5 = table.getSelectionModel().getSelectedItem();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String datestring = dateFormat.format(p5.getDateAj());

        StatutDetailYodh.setText(p5.getText());
        AjoutéLeYodh.setText(datestring);
        AjoutéParYodh.setText(p5.getPubUser());
        
               Publication p1 = table.getSelectionModel().getSelectedItem();
     String  path2 = p1.getPath();
       path2= path1 + path2 ;
     
      System.out.println("kkkk"+path2);
      Image image=new Image(path2);
      imageYodh.setImage(image);
             Publication p =new Publication();         p.setPath(path2);  

        details.setVisible(true);

    }
    
    private boolean validateFields(){
        if( StatutText.getText().isEmpty()){
            
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champ vide");
                alert.setHeaderText(null);
                alert.setContentText(" Remplissez le champ statut !! ");
                alert.showAndWait();
                
                return false;
        }
        return true;
       }
    
@FXML
    void MesPub(ActionEvent event) {
 imageAff.setVisible(false);
        table.setVisible(true);
        String nameUser = userService.loggeduser.getUsername();

       System.out.println(" kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+nameUser);

       
            e.listerUser(MesPublications,nameUser );

           table.setItems(MesPublications);
        TableStatut.setCellValueFactory(new PropertyValueFactory<>("text"));
        TableStatut.cellFactoryProperty();
        DateAj.setCellValueFactory(new PropertyValueFactory<>("dateAj"));
        DateAj.cellFactoryProperty();
        jaimeCol.setCellValueFactory(new PropertyValueFactory<>("jaime"));
        jaimeCol.cellFactoryProperty();
        jaimePasCol.setCellValueFactory(new PropertyValueFactory<>("jaimePas"));
        jaimePasCol.cellFactoryProperty();

            table.setEditable(true);

   
    }
     @FXML
    void Retour1Action(ActionEvent event) {
      try {
             Parent pagePieChart=FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilUser.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     @FXML
    void logout1Action(ActionEvent event) {
try {
             Parent pagePieChart=FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Login.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
