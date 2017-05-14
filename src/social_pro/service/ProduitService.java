/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package social_pro.service;

import static java.lang.Integer.parseInt;
import social_pro.utiles.DataSource;
import social_pro.entites.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import service.PiInteface;
import java.sql.Date;

/**
 *
 * @author Navoxx
 */
public class ProduitService implements PiInteface<Produit>{
    PreparedStatement ste;
    DataSource mc ;
    public ProduitService(){
        mc =  DataSource.getInstance();
    }
      

    @Override
    public void ajouter(Produit t) {
        try {
            String req ="INSERT INTO Produit(typeproduit,libelle,prix,description,quantite,path,rating2) values (?,?,?,?,?,?,?)";
            ste= mc.getConnection().prepareStatement(req);
            ste.setString(1,t.getTypeproduit());
            ste.setString(2,t.getLibelle());
            ste.setDouble(3, t.getPrix());
            ste.setString(4, t.getDescription());
            ste.setInt(5,t.getQuantite());
            ste.setString(6, t.getPath());
            ste.setDouble(7,t.getRating2());
           ste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @Override
   
    public void supprimer(int id) {
        String req ="DELETE FROM Produit WHERE id=?";
        try {
                  
            //String req ="delete FROM publication WHERE id=?";
            
            ste= mc.getConnection().prepareStatement(req);
          
            ste.setInt(1,id);
  

            ste.executeUpdate();
            
            
        } catch (SQLException ex) {
   Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       
    }

   @Override
    public void modifier(int id, Produit t) {
        String req ="update produit SET typeproduit= ?,libelle= ?,prix= ?,description= ?,quantite= ?,rating2= ?,path=? WHERE id="+id ;

        try{
            ste= mc.getConnection().prepareStatement(req);
            ste.setString(1, t.getTypeproduit());
            ste.setString(2,t.getLibelle());
            ste.setDouble(3, t.getPrix());
            ste.setString(4, t.getDescription());
            ste.setInt(5,t.getQuantite());
            ste.setDouble(6,t.getRating2());
            ste.setString(7,t.getPath());
                                               
            ste.executeUpdate();
            System.out.println("produit modifier");
        }
        catch(SQLException ex){
            System.out.println("erreur lors de la modification"+ ex.getMessage());
        }
    }

    @Override
    public List<Produit> lister() {
        

         List<Produit> list = new ArrayList<>();   
      try {
          String req = "SELECT * FROM produit";
          ste = mc.getConnection().prepareStatement(req);
          ResultSet rs = ste.executeQuery();
          while(rs.next()){
              Produit p = new Produit("");
              p.setId(rs.getInt(1));
              p.setTypeproduit(rs.getString(2));
              p.setLibelle(rs.getString(3));
             p.setPrix(rs.getDouble(4));
             p.setDescription(rs.getString(5));
             
             p.setQuantite(rs.getInt(6));
             p.setPath(rs.getString(7));
             p.setRating2(rs.getDouble(11));

              list.add(p);
              
                for (Produit e : list){
              System.out.println(e.getId());
                    System.out.println(e.getTypeproduit());
               System.out.println(e.getLibelle());
                System.out.println(e.getPrix());
                      System.out.println(e.getDescription());
                 System.out.println(e.getQuantite());
                    System.out.println(e.getPath());
                    System.out.println(e.getRating2());
               }
          }
      
      
      
      
      } catch (SQLException ex) {
          Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return list;
        
    
    }
    
     public void listerback (ObservableList<Produit> Liste_Produit)
    {
        try {
            String req="select * FROM `produit` ";
            ste = mc.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while(rs.next()){
                Liste_Produit.add(new Produit(rs.getInt("id"),rs.getString("typeproduit"),rs.getString("libelle"),rs.getDouble("prix"),rs.getInt("quantite"),rs.getString("path"),rs.getDouble("rating2"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
     public Produit getProduitById(int id) 
    {
        Produit e=new Produit();
                try{                    
            String req="Select * from produit where id=?";        
            ste = mc.getConnection().prepareStatement(req);
          
            ste.setInt(1, id);
            ResultSet rs= ste.executeQuery();
            
            
       
        if(rs.first())
        {
               e.setTypeproduit(rs.getString("typeproduit"));
               e.setLibelle(rs.getString("libelle"));
               e.setPrix(rs.getDouble("prix"));
               e.setQuantite(rs.getInt("quantite"));
       
               
             return e;
        }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
                return null;
                
                
    }
     
     
    
    public ResultSet recherche(String v) {

        ResultSet result = null;
        String sql = "SELECT * FROM produit WHERE id LIKE '%" + v + "%' OR libelle LIKE '%" + v + "%' or typeproduit LIKE '%" + v + "%' ORDER BY prix ASC ";
        try {

             ste= mc.getConnection().prepareStatement(sql);

            result = ste.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;

    }
     
     
     
    }

     
     
     
     
     
     
    
    
    
    

