/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import social_pro.entites.Message;
import social_pro.service.TacheService;

/**
 * FXML Controller class
 *
 * @author smart
 */
public class ConvocationEmpController implements Initializable {

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnEnovyerCon;
    @FXML
    private Button btnAnnulerCon;
      @FXML
    private JFXTextArea txtConv;
          @FXML
    private Button btnlogout;
           @FXML
    private Pane pane;
    TacheService ts = new TacheService();
      
      Message  m ;
      private int employee_id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event)throws IOException {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilChef.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void envoyer(ActionEvent event) {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
          employee_id = AcceuilChefController.mat_id;
          String text=txtConv.getText();
          m=new Message("convocation", text, employee_id, date);
          ts.convocation(m);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(txtConv.getScene().getWindow());
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("Votre Convcation à été envoyer avec succées ");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        Stage stage = (Stage) btnRetour.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilChef.fxml"));
                        Scene scene = new Scene(root); stage.setScene(scene); 
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ConvocationEmpController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    

                }
            });
          
          
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
         Stage stage = (Stage) btnRetour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AcceuilChef.fxml")); 
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
