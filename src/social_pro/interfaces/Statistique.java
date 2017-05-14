/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.interfaces;


import social_pro.entites.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import social_pro.service.EvenementService;

/**
 *
 * @author azerty
 */
public class Statistique extends Application {

    private final ObservableList<PieChart.Data> evenement = FXCollections.observableArrayList();
    private BorderPane root;
    private PieChart pieChart;

    @Override
    public void start(Stage primaryStage) {
        EvenementService PS = new EvenementService();

        primaryStage.setTitle("Pie Chart");
        evenement.addAll(new PieChart.Data("Loisirs", (PS.Calculer("Loisirs") * 100) / PS.Calculertotal()),
                 new PieChart.Data("Profesionnel", (PS.Calculer("Profesionnel") * 100) / PS.Calculertotal())
                
        );

        root = new BorderPane();
        Scene scene = new Scene(root, 600, 500);
        pieChart = new PieChart();
        pieChart.setData(evenement);
        pieChart.setTitle("Les statistiques des évènements selon leurs catégories");
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(true);
        root.setCenter(pieChart);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
