/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import social_pro.entites.Tache;
import social_pro.service.TacheService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class UpdateTacheController implements Initializable {

    @FXML
    private DatePicker dPdatedebut;
    @FXML
    private DatePicker dPdatefin;
    @FXML
    private TextArea txtdescriptionDiscipline;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnModifier;
    
 
   TacheService es = new TacheService();
      
      Tache  t = new Tache();

    @Override
    public void initialize(URL url, ResourceBundle rb) {  
       t=es.getTacheById(ListeTacheAfféctéController.id);
       txtdescriptionDiscipline.setText(t.getDescription());
       
       java.sql.Date db = t.getDatedebut();
        dPdatedebut.setValue(db.toLocalDate());
        
        java.sql.Date df = t.getDatefin();
        dPdatefin.setValue(df.toLocalDate());
       
      
       
       
       
    }    


    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void modif(ActionEvent event) {
    
     t.setDescription(txtdescriptionDiscipline.getText());
     
        LocalDate db = dPdatedebut.getValue();
        t.setDatedebut(java.sql.Date.valueOf(db));
        LocalDate df = dPdatefin.getValue();
        t.setDatefin(java.sql.Date.valueOf(df));
        t.setId(ListeTacheAfféctéController.id);
        
        
        int x=ListeTacheAfféctéController.id;
        es.updateback(x, t);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("modification faite avec succes!");
        alert.showAndWait();
        
    
    }
    
}
