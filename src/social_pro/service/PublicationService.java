/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.service;

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
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import social_pro.entites.Publication;
import social_pro.interfaces.IService;
import social_pro.interfaces.IService_1;

/**
 *
 * @author ahmed
 */
public class PublicationService implements IService_1<Publication> {

    private PreparedStatement ste;
    private Connection connection;
        private ObservableList<Publication> liste = FXCollections.observableArrayList();

    public PublicationService() {

        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Publication t) {
        String req = "INSERT INTO publication(text,date,path,user_id,pubusername) VALUES(?,?,?,?,?)";

        try {

            ste = connection.prepareStatement(req);
            ste.setString(1, t.getText());
            ste.setDate(2, t.getDateAj());

          /*  Blob image = null;
            if (t.getPathJava() != null) {
                image = connection.createBlob();
                image.setBytes(1, t.getPathJava());
            }*/
            System.out.println("eee"+t.getPath());
            ste.setString(3,t.getPath());
            ste.setInt(4, t.getUserId());
             ste.setString(5, t.getPubUser());
            ste.executeUpdate();

            System.out.println("Publication ajouter avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM publication WHERE id=?";
        try {

            //String req ="delete FROM publication WHERE id=?";
            ste = connection.prepareStatement(req);

            ste.setInt(1, id);

            ste.executeUpdate();
            System.out.println("Publication supprimer avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updatefront(int id, Publication p) {
        String req = "UPDATE publication SET text =?,path=? WHERE id=" + id;
        try {
            System.out.println("idddddddddddd" + id);
            ste = connection.prepareStatement(req);
            ste.setString(1, p.getText());
            ste.setString(2, p.getPath());
           
            
            ste.executeUpdate();
            System.out.println("successsssssssssss");
        } catch (Exception ex) {

        }
    }

    public List<Publication> lister() {

        List<Publication> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM publication";
            ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

                Publication p = new Publication(rs.getInt("id"), rs.getString("text"), rs.getDate("date"), rs.getString("path"),rs.getInt("jaime"),rs.getInt("jaimepas"),rs.getString("pubusername"),rs.getInt("user_id"));

                list.add(p);

                for (Publication e : list) {
                    System.out.println(e.getId());
                    System.out.println(e.getText());
                    System.out.println(e.getDateAj());
                    System.out.println(e.getPathJava());

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

   

    public void listerback(ObservableList<Publication> Liste_Publication)  {
        String req = "select * FROM `publication` ";
        try {

            ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {

                Liste_Publication.add(new Publication( rs.getInt("id"),rs.getString("text"), rs.getDate("date"), rs.getString("path"),rs.getInt("jaime"),rs.getInt("jaimepas"),rs.getString("pubusername"),rs.getInt("user_id")));
               /* Blob image = rs.getBlob("PathJava");
                if (image != null) {

                    byte[] imageBytes = image.getBytes(1, (int) image.length());

                    p.setPathJava(imageBytes); */

                }

              /* Liste_Publication.add(p); */
            
        }  catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex); {
                    }
        }
    
    }
    

    public Publication getPublicationById(int id) {
        Publication e = new Publication();
        try {
            String req = "Select * from publication where id=?";
            ste = connection.prepareStatement(req);

            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();

            if (rs.first()) {
                e.setText(rs.getString("text"));

                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ObservableList<Publication> Rechercher(ObservableList<Publication> liste,Date date1, Date date2) {
        ObservableList<Publication> rech = FXCollections.observableArrayList();
        try {
            String req = "select * FROM `publication` where date BETWEEN 'date1' and 'date2'";
            ste = connection.prepareStatement(req);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

                Publication p = new Publication(rs.getInt("id"), rs.getString("text"), rs.getDate("date"));

                liste.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
}


    

    public void listerback1(ObservableList<Publication> Liste_Publication,Date date4,Date date6)  {
      String req = "select * FROM `publication` where date >= '"+ date4 +"' AND date <= '"+ date6 +"'  ";

         try {

            ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {

                Liste_Publication.add(new Publication(rs.getInt("id"), rs.getString("text"), rs.getDate("date")));

               

                }

            
            
        }  catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex); {
                    }
        }
    
    }
     public void updatefrontJaime(int id, Publication p) {
        String req = "UPDATE publication SET jaime =? WHERE id=" + id;
        try {
            System.out.println("idddddddddddd" + id);
            ste = connection.prepareStatement(req);
            ste.setInt(1, p.getJaime());
           
            
            ste.executeUpdate();
            System.out.println("successsssssssssss");
        } catch (Exception ex) {

        }
    }
     
      public void updatefrontJaimePas(int id, Publication p) {
        String req = "UPDATE publication SET jaimePas =? WHERE id=" + id;
        try {
            System.out.println("idddddddddddd" + id);
            ste = connection.prepareStatement(req);
            ste.setInt(1, p.getJaimePas());
           
            
            ste.executeUpdate();
            System.out.println("successsssssssssss");
        } catch (Exception ex) {

        }
    }
      
      
       public void listerUser(ObservableList<Publication> MesPublications,String nameUser)  {
        String req = "select * FROM `publication` where pubusername= '"+ nameUser +"'";
        try {

            ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {

                MesPublications.add(new Publication( rs.getInt("id"),rs.getString("text"), rs.getDate("date"), rs.getString("path"),rs.getInt("jaime"),rs.getInt("jaimepas"),rs.getString("pubusername")));
               

                }

            
        }  catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex); {
                    }
        }
    
    }

}
