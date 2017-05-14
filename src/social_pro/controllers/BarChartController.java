/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import social_pro.entites.Stat;
import social_pro.service.TacheService;
import social_pro.utiles.DataSource;

/**
 * FXML Controller class
 *
 * @author Navoxx
 */
public class BarChartController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;
    
    
    @FXML
    private JFXButton BtnCharger ;
       @FXML
    private Button btnRetour ;
       
        @FXML
    private Button btnlogout;
         @FXML
    private Pane pane;
    PreparedStatement ste;
    DataSource mc;

    /**
     * Initializes the controller class.
     */
    
    
     public BarChartController(){
        mc =  DataSource.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChargerChart(ActionEvent event) throws SQLException {
        XYChart.Series<String,Integer> series = new XYChart.Series<>();

        TacheService tc= new TacheService();
        List<Stat> listStat = tc.getNbreTacheByEtat();
        try {
            for (Stat stat : listStat) {
              series.getData().add(new XYChart.Data<>(stat.getEtat(),stat.getNbreTaches()));
              
            }
            barChart.getData().add(series);

        } catch (Exception e) {
        }

    }
    
     @FXML
    private void logout(ActionEvent event) throws IOException {
         Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/authentification.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }
      @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/AjouterTache.fxml")); 
        Scene scene = new Scene(root); stage.setScene(scene);
        stage.show(); 
    }

}
