/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import social_pro.service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class FXMLStatistiqueController implements Initializable {
   

    
    @FXML
    PieChart   pieChart = new PieChart();

    private final ObservableList<PieChart.Data> evenement = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          EvenementService PS = new EvenementService();
       evenement.addAll(new PieChart.Data("Loisirs", (PS.Calculer("Loisirs") * 100) / PS.Calculertotal()),
                 new PieChart.Data("Profesionnel", (PS.Calculer("Profesionnel") * 100) / PS.Calculertotal())
                
        );
       
        pieChart.setData(evenement);
        pieChart.setTitle("Les statistiques des évènements selon leurs catégories");
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(true);
        
       
    }
    }    
    

