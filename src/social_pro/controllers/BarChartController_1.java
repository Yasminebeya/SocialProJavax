/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import social_pro.utiles.DataSource;

/**
 * FXML Controller class
 *
 * @author Navoxx
 */
public class BarChartController_1 implements Initializable {

    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private Button BtnCharger;
    PreparedStatement ste;
    DataSource mc;

    /**
     * Initializes the controller class.
     */
    
    
     public BarChartController_1(){
        mc =  DataSource.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChargerChart(ActionEvent event) throws SQLException {
        String query = "select libelle,quantite FROM produit ORDER BY quantite asc";
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        try {

            ste = mc.getConnection().prepareStatement(query);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getDouble(2)));
            }
            barChart.getData().add(series);

        } catch (Exception e) {
        }

    }

}
