/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.controllers;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.Date;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static social_pro.controllers.PanierController.id;
import static social_pro.controllers.gererProduitController.id;
import social_pro.entites.Produit;
import social_pro.service.PanierService;
import social_pro.service.ProduitService;

/**
 *
 * @author Navoxx
 */
public class LouerController {

    @FXML
    private DatePicker DateD;
    @FXML
    private DatePicker DateF;
    @FXML
    private Button BtnLouer;
    @FXML
    private Button btnretour;

    Produit p = new Produit();
    ProduitService cs = new ProduitService();
    PanierService ps = new PanierService();
    LouerController liste;

    @FXML
    private void Louer2(ActionEvent event) {

        LocalDate date = DateD.getValue();
        Date date1 = java.sql.Date.valueOf(date);
        LocalDate date2 = DateF.getValue();
        Date date3 = java.sql.Date.valueOf(date2);
        p.setDatedebut(date1);
        p.setDatefin(date3);
        int d = PanierController.id;
        
        ps.Louer(d, p);
        Alert al = new Alert(Alert.AlertType.INFORMATION, "Reservation effectuer", ButtonType.OK);
        al.show();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnretour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/social_pro/Fxml/listeProduitFront.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

}
