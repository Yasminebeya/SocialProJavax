/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import social_pro.entites.Jaime;
import social_pro.entites.JaimePas;
import social_pro.entites.fos_user;
import social_pro.utiles.DataSource;

/**
 *
 * @author ahmed
 */
public class JaimePasService {
    private PreparedStatement ste;
    private Connection connection;
         public static fos_user loggedUser;

    public JaimePasService() {

        connection = DataSource.getInstance().getConnection();

    }
    
    public void addJaimePas(JaimePas t) {
        String req = "INSERT INTO jaimepas(idPublication,idUser) VALUES(?,?)";
        
        try {

            ste = connection.prepareStatement(req);
            ste.setInt(1, t.getIdPublication());
            ste.setInt(2, t.getIdUser());
           
            System.out.println("eee");
            ste.executeUpdate();

            System.out.println("JaimePas ajouter avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public void listerJaimePas(ObservableList<JaimePas> Liste_Jaime)  {
        String req = "select * FROM `jaimepas` ";
        try {

            ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {

                Liste_Jaime.add(new JaimePas( rs.getInt("idPublication"), rs.getInt("idUser")));


                }

            
        }  catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex); {
                    }
        }
    
    }
   
   
    
}
