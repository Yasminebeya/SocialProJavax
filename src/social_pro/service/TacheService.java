
package social_pro.service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import social_pro.entites.Message;
import social_pro.entites.Stat;
import social_pro.entites.Tache;
import social_pro.entites.fos_user;
import social_pro.interfaces.IService;
import social_pro.utiles.DataSource;


public class TacheService implements IService<Tache>{
    PreparedStatement ste;
    DataSource datasource;  
    private static TacheService instance;
    
    public TacheService()  
    {
    datasource=DataSource.getInstance();
    }
    
public static TacheService getInstance(){
        if(instance==null) 
            instance=new TacheService();
        return instance;
    }
    @Override
    public void add(Tache t) {
        
           
        try {
            String req="INSERT INTO tache(datedebut,datefin,description,etat) VALUES(?,?,?,?)";
            ste=datasource.getConnection().prepareStatement(req);
            ste.setDate(1, t.getDatedebut());
            ste.setDate(2, t.getDatefin());
             ste.setString(3, t.getDescription());   
            ste.setString(4, t.getEtat());          
            int x= ste.executeUpdate();
            //System.out.println("nb de ligne ajouter" +x);
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }           
       
    }
      @Override
     public void convocation(Message c) {
        
           
        try {
            String req="INSERT INTO message(objet,text,user_id,datedenvoye) VALUES(?,?,?,?)";
            ste=datasource.getConnection().prepareStatement(req);
            ste.setString(1, c.getObjet());
            ste.setString(2, c.getText());
             ste.setInt(3, c.getEmploye_id());
            ste.setDate(4, c.getDatedenvoye());              
             ste.executeUpdate();
            //System.out.println("nb de ligne ajouter" +x);
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }           
       
    }
    

    @Override
    public void delete(int id) {
        try {  
             String req="DELETE FROM tache WHERE id=?";
             ste=datasource.getConnection().prepareStatement(req);
                 ste.setInt(1, id);
             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

   @Override
    public void updateback(int id, Tache t) {
          String req = "UPDATE `tache`  SET `datedebut`=?,`datefin`=?,`description`=? WHERE `id`=?";
        try {
            ste = datasource.getConnection().prepareStatement(req);
           
            ste.setDate(1, t.getDatedebut());
            ste.setDate(2, t.getDatefin());
            ste.setString(3, t.getDescription());
            ste.setInt(4, id);
            ste.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
       
       
   

    @Override
    public List<Tache> getAll() {
       List<Tache> list= new ArrayList<>();
        try {
            
            String req="SELECT * FROM tache";
            ste=datasource.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while(rs.next())
            {
                Tache t  = new Tache();
              
                  t.setId(rs.getInt("id"));
                  t.setDatedebut(rs.getDate("datedebut"));
                  t.setDatefin(rs.getDate("datefin"));
                  t.setDescription(rs.getString("description"));
                  t.setEtat(rs.getString("etat"));
                                   
                  list.add(t);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;
    }
    
  

    @Override
    public void updatefront(int id, Tache t) {
         String req = "UPDATE `tache`  SET `datedebut`=?,`datefin`=?,`description`=?,`etat`=?  WHERE `id`=?";
        try {
            ste = datasource.getConnection().prepareStatement(req);
           
            ste.setDate(1,  t.getDatedebut());
            ste.setDate(2,  t.getDatefin());
            ste.setString(3, t.getDescription());
            ste.setString(4, t.getEtat());
            ste.setInt(5, id);
            ste.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public int nomUser(String nom)
            
    {
        int id =0;
        String req="select `id` from `fos_user` where username=? ";
        try {
            ste = datasource.getConnection().prepareStatement(req);
            ste.setString(1, nom);
              ResultSet rs= ste.executeQuery();
              while (rs.next())
              {
                  id=rs.getInt("id");
              }
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
    @Override
     public void listeTacheNonAff(ObservableList<Tache> Liste_Tache)
    {
        try {
            String req="select * FROM `Tache` where datefin> CURRENT_DATE and user_id is NULL ORDER BY datedebut ASC  ";
             ste = datasource.getConnection().prepareStatement(req);
            ResultSet rs= ste.executeQuery();
            
 
            while(rs.next()){
                Liste_Tache.add(new Tache(rs.getInt("id"),rs.getDate("datedebut"),rs.getDate("datefin"),rs.getString("description"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
      public void listeTacheNonRealise(ObservableList<Tache> tacheNonRalisee)
    {
    
        try {
            String req="select * FROM `Tache`,`fos_user` where datefin<CURRENT_DATE and user_id is not NULL and ((etat like '%pas encore réalisée%') or (etat like '%en train de réalisation%') ) and tache.user_id = fos_user.id";
             ste = datasource.getConnection().prepareStatement(req);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                tacheNonRalisee.add(new Tache(rs.getInt("id"),
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        rs.getString("description"),
                        rs.getInt("user_id"),
                        rs.getString("username"))); 
               // System.out.println("nom "+rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Override
      public void listeTacheAff(ObservableList<Tache> Liste_Tache)
    {
        try {
            String req="select * FROM `Tache`,`fos_user` where datefin> CURRENT_DATE and user_id is not NULL and fos_user.id=tache.user_id ORDER BY datedebut ASC  ";
             ste = datasource.getConnection().prepareStatement(req);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Liste_Tache.add(new Tache(rs.getInt("id")
                        ,rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        rs.getString("description"),
                        rs.getInt("user_id"),
                        rs.getString("username"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
      public void listeMatEmp(ObservableList<fos_user> Liste_Matricule)
    {
        try {
            String req="select username FROM `fos_user` where ((id <> 1) and (id <>13))";
             ste = datasource.getConnection().prepareStatement(req);
            ResultSet rs= ste.executeQuery();
            
 
            while(rs.next()){
                Liste_Matricule.add(new fos_user(rs.getString("username"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
      @Override 
     public Tache getTacheById(int id)
    {
                try{
                    
            String req="Select * from tache where id=?";        
            ste = datasource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet rs= ste.executeQuery();
        if(rs.first())
        {
              Tache t=new Tache();
                t.setId(rs.getInt("id"));
                t.setDatedebut(rs.getDate("datedebut"));
                t.setDatefin(rs.getDate("datefin"));
                t.setDescription(rs.getString("description"));
                  return t;
        }
       }
       catch (SQLException e){
             e.printStackTrace();
            
        }
                return null;
    }
      @Override 
    public void listeTacheLUser(ObservableList<Tache> Liste_Tache_aff,int mat_id)
    {
        try {
            String req="select * FROM `Tache` where `user_id`=?";
             ste = datasource.getConnection().prepareStatement(req);
             ste.setInt(1, mat_id);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Liste_Tache_aff.add(new Tache(rs.getInt("id"),rs.getDate("datedebut"),rs.getDate("datefin"),rs.getString("description"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public ObservableList<Tache> tacheLuser(int mat_id)
    {ObservableList<Tache> taches = FXCollections.observableArrayList();
    String x="pas encore rélisée";
    String x2="en train de réalisation";
        try {
            String req="select * FROM `Tache` where `user_id`=? and datefin>CURRENT_DATE() and( (etat like '%pas encore réalisée%') or (etat like '%en train de réalisation%'))";
             ste = datasource.getConnection().prepareStatement(req);
             ste.setInt(1, mat_id);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                 Tache t = new Tache();
                 t.setId(rs.getInt("id"));
                 t.setDatedebut(rs.getDate("datedebut"));
                  t.setDatefin(rs.getDate("datefin"));
                  t.setDescription(rs.getString("description"));
                  t.setEtat(rs.getString("etat"));
                  taches.add(t);
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taches;
    }
       @Override 
     public void affecter(Tache t,int id) {
        try {  
             String req="UPDATE `tache`  SET `user_id`=? WHERE `id`=?";
             ste=datasource.getConnection().prepareStatement(req);
             ste.setInt(1, t.getMatricule_id());
                 ste.setInt(2, id);
             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       @Override 
     public void updateEtat(Tache t,int id,String etat) {
        try {  
             String req="UPDATE `tache`  SET `etat`=? WHERE `id`=?";
             ste=datasource.getConnection().prepareStatement(req);
             ste.setString(1, etat);
                 ste.setInt(2, id);
             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
       @Override 
     public void MessageUser(ObservableList<Message> Liste_Message,int emp_id)
    {
        try {
            String req="select * FROM `Message` where `user_id`=?";
             ste = datasource.getConnection().prepareStatement(req);
             ste.setInt(1, emp_id);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Liste_Message.add(new Message(rs.getInt("id"),rs.getString("objet"),rs.getString("text"),rs.getInt("user_id"),rs.getDate("datedenvoye"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      @Override  
 public void deleteMsg(int id) {
        try {  
             String req="DELETE FROM message WHERE id=?";
             ste=datasource.getConnection().prepareStatement(req);
                 ste.setInt(1, id);
             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TacheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
     @Override 
public List<Stat> getNbreTacheByEtat(){
        String query = "select count(id) FROM tache where etat='réalisée'";
        String query1 = "select count(id) FROM tache where etat='pas encore réalisée'";
        String query2 = "select count(id) FROM tache where etat='en train de réalisation'";
        List<Stat> listStat = new ArrayList<>();
        try {

            
            ste = datasource.getConnection().prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Stat stat =new Stat();
                stat.setNbreTaches(rs.getInt(1));
                stat.setEtat("réalisée");  
                listStat.add(stat);
            }
            
             ste = datasource.getConnection().prepareStatement(query1);
             rs = ste.executeQuery();
            while (rs.next()) {
                Stat stat =new Stat();
                stat.setNbreTaches(rs.getInt(1));
                stat.setEtat("pas encore réalisée"); 
                 listStat.add(stat);
            }
            
             ste = datasource.getConnection().prepareStatement(query2);
             rs = ste.executeQuery();
            while (rs.next()) {
                Stat stat =new Stat();
                stat.setNbreTaches(rs.getInt(1));
                stat.setEtat("en train de réalisation");  
                 listStat.add(stat);
            }
            
           
           return listStat;
}
catch(SQLException ex){
    ex.printStackTrace();
}
        return null;
}







}
