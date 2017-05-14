/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Rating;
import static social_pro.controllers.listeDesProduitController.id;
import social_pro.entites.Produit;
import social_pro.service.ProduitService;
import social_pro.utiles.DataSource;

/**
 *
 * @author Navoxx
 */
public class gererProduitController implements Initializable {

    @FXML
    private Text ajouterTache;
    @FXML
    private Button choisirPhoto;

    @FXML
    private ImageView imageView;

    @FXML
    private TableView<Produit> tableproduit;

    @FXML
    private TableColumn<Produit, String> LibelleP1;

    @FXML
    private TableColumn<Produit, String> TypeP1;

  

    @FXML
    private TableColumn<Produit, Integer> QuantiteP1;

    @FXML
    private TableColumn<Produit, Image> ImageP1;
    @FXML
    private Button BtnStat;
    @FXML
    private Button updateBtn;
    @FXML
    private Button updateBtn1;
    @FXML
    private Button addBtn;
    @FXML
    private Button suppBtn;
 @FXML
    private Button btnlogout;
    @FXML
    private TextField LibelleP;

    @FXML
    private TextField TypeP;

    @FXML
    private TextField PrixP;

    @FXML
    private TextField QuantiteP;

    @FXML
    private TextField ImageP;

    /*
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnLogout1; */
    @FXML
    private Button btnsupp;
    
    @FXML
    private Button ExportToXL;
    @FXML 
    private HBox ratinghbox;

    private Rating rating;
  @FXML
    private TextField search1;
    private ObservableList<Produit> Liste_Produit = FXCollections.observableArrayList();
    Produit p = new Produit();
    ProduitService es = new ProduitService();
    listeDesProduitController liste;
    static int id;
    static File path = new File("");
    private FileInputStream fis ;
     PreparedStatement ste;
    DataSource mc;
     public String path1 = "file:///";
     public gererProduitController(){
        mc =  DataSource.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        es.listerback(Liste_Produit);

        tableproduit.setItems(Liste_Produit);

        /*idtache.setCellValueFactory(new PropertyValueFactory<>("id"));
        idtache.cellFactoryProperty();
         */
        LibelleP1.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        LibelleP1.cellFactoryProperty();

        TypeP1.setCellValueFactory(new PropertyValueFactory<>("typeproduit"));
        TypeP1.cellFactoryProperty();
        

        QuantiteP1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        QuantiteP1.cellFactoryProperty();

        tableproduit.setEditable(true);
        
              rating = new Rating();
  
    ratinghbox.getChildren().add(rating);
     rating.setUpdateOnHover(true);

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
    public void refreshtable() {

        ObservableList<Produit> list;
        list = FXCollections.observableArrayList(es.lister());
//            tableApprenant.setItems(list);
        tableproduit.setItems(list);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Produit prod = new Produit();
        prod = tableproduit.getSelectionModel().getSelectedItem();
        ProduitService rs = new ProduitService();
        System.out.println(prod.getId());
        rs.supprimer(prod.getId());

        this.refreshtable();
         Alert al = new Alert(Alert.AlertType.INFORMATION, "Produit Supprimer avec Succes", ButtonType.OK);
      
                al.show();
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {

        Produit p = tableproduit.getSelectionModel().getSelectedItem();

        p = tableproduit.getSelectionModel().getSelectedItem();
        id = p.getId();
        System.out.println("id=" + id);
        p = es.getProduitById(id);

        LibelleP.setText((p.getLibelle()));
        TypeP.setText(p.getTypeproduit());
        QuantiteP.setText(Integer.toString(p.getQuantite()));
        PrixP.setText(Double.toString(p.getPrix()));
        Produit  p1 = tableproduit.getSelectionModel().getSelectedItem();
        String path2 = p1.getPath();
        path2=path1+path2;
        System.out.println("ll"+path2);
        Image image =new Image(path2);
        imageView.setImage(image);
        
        

    }

    @FXML
    void modifier2(ActionEvent event) throws IOException {
    if (validateLibelle() & validateQuantite() & validateFields() & validatePrix() & validateType()) {
        p.setLibelle(LibelleP.getText());
        p.setTypeproduit(TypeP.getText());
        p.setPrix(Double.parseDouble(PrixP.getText()));
        p.setQuantite(parseInt(QuantiteP.getText()));
         double d =  rating.ratingProperty().getValue();
        p.setRating2(d);
p.setPath(gererProduitController.path.getAbsolutePath());
        es.modifier(id, p);
        this.refreshtable();

         Alert al = new Alert(Alert.AlertType.INFORMATION, "Produit Modifier avec Succes", ButtonType.OK);
      
                al.show();
                }
    }

    @FXML
    private void retour(ActionEvent event) {

    }

    @FXML
    private void home(ActionEvent event) {

    }

    @FXML
    private void consulter(ActionEvent event) {

    }

    @FXML
    private void ajout(ActionEvent event) {
     if (validateLibelle() & validateQuantite() & validateFields() & validatePrix() & validateType()) {
        ProduitService Rs = new ProduitService();
        Produit R = new Produit();
        R.setLibelle(LibelleP.getText());
        R.setTypeproduit(TypeP.getText());
        R.setPrix(Double.parseDouble(PrixP.getText()));
        R.setQuantite(parseInt(QuantiteP.getText()));
        R.setPath(AjoutController.path.getAbsolutePath());
        
      double d =  rating.ratingProperty().getValue();
        R.setRating2(d);
        Produit p = new Produit();

        Rs.ajouter(R);

           this.refreshtable();
           Alert al = new Alert(Alert.AlertType.INFORMATION, "Produit Ajouter avec Succes", ButtonType.OK);
      
                al.show();
}}

    

    @FXML
    void choisirPhoto(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File sf = fc.showOpenDialog(null);
   
        if (sf != null) {
         
            fc.setTitle("Ajouter");
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.jpeg"));
            if (sf != null) {
                AjoutController.path = sf.getAbsoluteFile();
                Image i = new Image(sf.toURI().toString());
                imageView.setImage(i);
              
            }

        }
    }

    @FXML
    void ShowChart(ActionEvent event) throws IOException {
        Stage stage = (Stage) BtnStat.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/BarChart.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    
    @FXML
    void ExportExel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
 String req = "SELECT * FROM produit";
          ste = mc.getConnection().prepareStatement(req);
          ResultSet rs = ste.executeQuery();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Produits informations");
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Libelle");
        header.createCell(2).setCellValue("Type");
        header.createCell(3).setCellValue("Prix");
        header.createCell(4).setCellValue("Quantite");
        int index = 1 ;
        while (rs.next()){
         HSSFRow row = sheet.createRow(index);
         row.createCell(0).setCellValue(rs.getString("id"));
             row.createCell(1).setCellValue(rs.getString("libelle"));
              row.createCell(2).setCellValue(rs.getString("typeproduit"));
               row.createCell(3).setCellValue(rs.getString("prix"));
                row.createCell(4).setCellValue(rs.getString("quantite"));
                index ++ ;
        }
        
        FileOutputStream fileOut = new FileOutputStream("ProduitInformation.xls");
        wb.write(fileOut);
        fileOut.close();
        Alert al = new Alert(Alert.AlertType.INFORMATION, "Fichier EXEL Cree avec succes", ButtonType.OK);
      
                al.show();
        
    }
                
    
    
    
     private boolean validateLibelle(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(LibelleP.getText());
        if(m.find() && m.group().equals(LibelleP.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Libelle Invalide");
                alert.setHeaderText(null);
                alert.setContentText("Entrez un Libelle valide ");
                alert.showAndWait();
            
            return false;            
        }
    }
     private boolean validateType(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(TypeP.getText());
        if(m.find() && m.group().equals(TypeP.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type Invalide");
                alert.setHeaderText(null);
                alert.setContentText("Entrez un Type valide ");
                alert.showAndWait();
            
            return false;            
        }
    }
    
      private boolean validateQuantite(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(QuantiteP.getText());
        if(m.find() && m.group().equals(QuantiteP.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Quantite invalide");
                alert.setHeaderText(null);
                alert.setContentText("Entrez une Quantite valide ");
                alert.showAndWait();
            
            return false;            
        }
    }
            private boolean validatePrix(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(PrixP.getText());
        if(m.find() && m.group().equals(PrixP.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Prix invalide");
                alert.setHeaderText(null);
                alert.setContentText("Entrez un Prix valide ");
                alert.showAndWait();
            
            return false;            
        }
    }
      
      
      
       private boolean validateFields(){
        if( LibelleP.getText().isEmpty()){
            
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champ vide");
                alert.setHeaderText(null);
                alert.setContentText(" Entrez un Libelle ");
                alert.showAndWait();
                
                return false;
        }
        return true;
       }
  
       public void loadData() {
     ProduitService su = new ProduitService();
        Liste_Produit = FXCollections.observableArrayList();

       
            ResultSet r = su.recherche(search1.getText());
        try {
            while (r.next()) {
                Produit e = new Produit();
                e.setId(r.getInt("id"));
                e.setLibelle(r.getString("libelle"));
                e.setTypeproduit(r.getString("typeproduit"));
              
                e.setQuantite(r.getInt("quantite"));
                e.setPrix(r.getDouble("prix"));
                
                

                Liste_Produit.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(gererProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            tableproduit.setItems(null);
            tableproduit.setItems(Liste_Produit);
     
    }

        @FXML
  public void rech(KeyEvent event) {
 tableproduit.refresh();
       ProduitService es = new ProduitService();
       loadData();
    }
 
  
  
   @FXML
    void logout(ActionEvent event) throws IOException {
  Stage stage = (Stage) btnlogout.getScene().getWindow();
  Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }
    
  
  
  
}
