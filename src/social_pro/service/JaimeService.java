/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import social_pro.entites.Jaime;
import social_pro.entites.Publication;
import social_pro.entites.fos_user;
import social_pro.utiles.DataSource;
import java.sql.ResultSet;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import social_pro.utiles.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import social_pro.entites.Publication;
import social_pro.interfaces.IService;
/**
 *
 * @author ahmed
 */
public class JaimeService {
    private PreparedStatement ste;
    private Connection connection;
         public static fos_user loggedUser;

    public JaimeService() {

        connection = DataSource.getInstance().getConnection();

    }
    
    
    public void addJaime(Jaime t) {
        String req = "INSERT INTO jaime(idPublication,idUser) VALUES(?,?)";
        
        try {

            ste = connection.prepareStatement(req);
            ste.setInt(1, t.getIdPublication());
            ste.setInt(2, t.getIdUser());
           
            System.out.println("eee");
            ste.executeUpdate();

            System.out.println("Jaime ajouter avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public void listerJaime(ObservableList<Jaime> Liste_Jaime)  {
        String req = "select * FROM `jaime` ";
        try {

            ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {

                Liste_Jaime.add(new Jaime( rs.getInt("idPublication"), rs.getInt("idUser")));


                }

            
        }  catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex); {
                    }
        }
    
    }
   
  public void delete(int idPub,int idUser) {
        String req = "DELETE FROM jaime WHERE idPublication=? AND idUser=?";
        try {

            //String req ="delete FROM publication WHERE id=?";
            ste = connection.prepareStatement(req);

            ste.setInt(1, idPub);
            ste.setInt(2, idUser);
            ste.executeUpdate();
           

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
