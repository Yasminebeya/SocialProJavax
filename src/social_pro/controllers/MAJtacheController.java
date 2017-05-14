
package social_pro.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import social_pro.entites.Tache;
import social_pro.service.TacheService;


public class MAJtacheController implements Initializable {

    @FXML
    private Button btnRetour;
  
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAnnuler;
     @FXML
    private Pane pane;       
    @FXML
    private Button btnlogout;
    @FXML
    private JFXDatePicker dPdatedebut;
    @FXML
    private JFXDatePicker dPdatefin;
    @FXML
    private JFXTextArea txtdescriptionDiscipline;

    
       TacheService es = new TacheService();
      
      Tache  t = new Tache();
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        t=es.getTacheById(TacheAfféctéesController.id);
       txtdescriptionDiscipline.setText(t.getDescription());
       
       java.sql.Date db = t.getDatedebut();
        dPdatedebut.setValue(db.toLocalDate());
        
        java.sql.Date df = t.getDatefin();
        dPdatefin.setValue(df.toLocalDate());
       
      
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/TacheAfféctées.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void modif(ActionEvent event){
         t.setDescription(txtdescriptionDiscipline.getText());
     
        LocalDate db = dPdatedebut.getValue();
        t.setDatedebut(java.sql.Date.valueOf(db));
        LocalDate df = dPdatefin.getValue();
        t.setDatefin(java.sql.Date.valueOf(df));
        t.setId(ListeTacheAfféctéController.id);
        
        
        int x=TacheAfféctéesController.id;
        es.updateback(x, t);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("modification faite avec succes!");
        alert.showAndWait();
        alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        Stage stage = (Stage) pane.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/TacheAfféctées.fxml"));
                        Scene scene = new Scene(root); stage.setScene(scene); 
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MAJtacheController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/TacheAfféctées.fxml"));
                        Scene scene = new Scene(root); stage.setScene(scene); 
                        stage.show();
        
       
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/authentification.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }
    
}
