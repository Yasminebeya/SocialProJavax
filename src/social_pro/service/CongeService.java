/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.service;

import static com.oracle.nio.BufferSecrets.instance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import social_pro.entites.Conge;
import social_pro.entites.StatConge;
import social_pro.entites.User;
import social_pro.entites.fos_user;

import social_pro.interfaces.CongeInterface;
import social_pro.service.CongeService;
import social_pro.utiles.DataSource;

/**
 *
 * @author Yass
 */
public class CongeService implements CongeInterface<Conge> {

    PreparedStatement ste;
    DataSource datasource;
    private static CongeService instance;

    public CongeService() {

        datasource = DataSource.getInstance();
    }

    public static CongeService getInstance() {
        if (instance == null) {
            instance = new CongeService();
        }
        return instance;
    }

    @Override
    public void add(Conge t) {
        try {

            String req = "INSERT INTO conge(datedebut,nbrjours,type_conge,raison,etat,user) VALUES(?,?,?,?,?,?)";
            ste = datasource.getConnection().prepareStatement(req);
            /* int idu;
         idu = utilisateurService.loggedUser.getId();*/
            ste.setDate(1, t.getDatedebut());
            ste.setInt(2, t.getNbrjours());
            ste.setString(3, t.getType_conge());
            ste.setString(4, t.getRaison());
            ste.setString(5, t.getEtat());
            ste.setInt(6, UserService.loggeduser.getId());

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remove(int id) {
        String req = "DELETE FROM conge WHERE id=?";
        try {
            // TODO code application logic here

            ste = datasource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void Update(int id, Conge t) {
        try {
            // TODO code application logic here

            String req = "update `conge` SET `datedebut`=?,`nbrjours`=?,`type_Conge`=? ,`raison`=? WHERE `id`=?";
            ste = datasource.getConnection().prepareStatement(req);
            ste.setDate(1, t.getDatedebut());
            ste.setInt(2, t.getNbrjours());
            ste.setString(3, t.getType_conge());
            ste.setString(4, t.getRaison());
            ste.setInt(5, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public List<Conge> getAll() {
        List<Conge> list = new ArrayList<>();
        try {
            // TODO code application logic here

            String req = "SELECT * FROM `conge`";
            ste = datasource.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Conge p = new Conge();
                User user = new User();
                user.setId(rs.getInt("user"));
                p.setId(rs.getInt("id"));
                p.setDatedebut(rs.getDate("datedebut"));
                p.setNbrjours(rs.getInt("nbrjours"));
                p.setType_conge(rs.getString("type_conge"));
                p.setRaison(rs.getString("raison"));
                p.setEtat(rs.getString("etat"));
                p.setUser(user);
                list.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void listeConge(ObservableList<Conge> Liste_Conge) {
        try {
            String req = "select * FROM `Conge`order BY `type_conge` ";
            ste = datasource.getConnection().prepareStatement(req);
            /*int idu;
         idu = utilisateurService.loggedUser.getId();
             ste.setInt(1, idu);*/
            
           
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
               User user = new User();
                
          
                user.setId(rs.getInt("user"));
                Liste_Conge.add(new Conge(rs.getInt("id"),
                         rs.getDate("datedebut"),
                        rs.getInt("nbrjours"),
                        rs.getString("type_conge"),
                        rs.getString("raison"),
                        rs.getString("etat"), user));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listeConge2(ObservableList<Conge> Liste_Conge) {
        try {
            String req = "select * FROM `Conge` where `user`=?";
            ste = datasource.getConnection().prepareStatement(req);
            int idu;
            idu = UserService.loggeduser.getId();
            ste.setInt(1, idu);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
               

                 User user = new User();
                user.setId(rs.getInt("user"));
                Liste_Conge.add(new Conge(rs.getInt("id"),
                         rs.getDate("datedebut"),
                        rs.getInt("nbrjours"),
                        rs.getString("type_conge"),
                        rs.getString("raison"),
                        rs.getString("etat"), user));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listeCongeAttente(ObservableList<Conge> Liste_Conge) {
        try {
            String req = "select * FROM `Conge` where etat = 'En attente' ";
            ste = datasource.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                
                 User user = new User();
                user.setId(rs.getInt("user"));
                Liste_Conge.add(new Conge(rs.getInt("id"),
                         rs.getDate("datedebut"),
                        rs.getInt("nbrjours"),
                        rs.getString("type_conge"),
                        rs.getString("raison"),
                        rs.getString("etat"), user));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listeCongeAccepter(ObservableList<Conge> Liste_Conge) {
        try {
            String req = "select * FROM `Conge` where etat = 'accepter' ";
            ste = datasource.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
              
               User user = new User();
                user.setId(rs.getInt("user"));
                Liste_Conge.add(new Conge(rs.getInt("id"),
                         rs.getDate("datedebut"),
                        rs.getInt("nbrjours"),
                        rs.getString("type_conge"),
                        rs.getString("raison"),
                        rs.getString("etat"), user));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Conge getCongeById(int id) {
        Conge e = new Conge();
       User user = new User();
        try {

            String req = "Select * from conge where id=?";
            ste = datasource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();

            if (rs.first()) {
               

                user.setId(rs.getInt("user"));
             
                e.setId(rs.getInt("id"));
                e.setDatedebut(rs.getDate("datedebut"));
                e.setNbrjours(rs.getInt("nbrjours"));
                e.setType_conge(rs.getString("type_conge"));
                e.setRaison(rs.getString("raison"));
                e.setEtat(rs.getString("etat"));
                e.setUser(user);
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void updateEtat(Conge c) {
        try {
            // TODO code application logic here

            String req = "update `conge` SET `etat`=? WHERE `id`=?";
            ste = datasource.getConnection().prepareStatement(req);
            ste.setString(1, c.getEtat());
            ste.setInt(2, c.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public int calculerNbJoursAccepter(List<Conge> list, int id) {
        int nbJoursAccepter = 0;
        //Conge c= getCongeById(id);
       
        for (Conge conge : list) {
         
            if (conge.getUser().getId() == id && conge.getEtat().equals("accepter")) {
                nbJoursAccepter += conge.getNbrjours();
            }
        }
      
        return nbJoursAccepter;
    }

    @Override
    public boolean validerConge(Conge conge) {
        int nbJours = 0;
        List<Conge> listeConge = getAll();
      
        nbJours = calculerNbJoursAccepter(listeConge, conge.getUser().getId()) + conge.getNbrjours();
        if (nbJours <= 30) {
            conge.setEtat("accepter");
            updateEtat(conge);
            return true;
        } else {
            conge.setEtat("refuser");
            updateEtat(conge);
            return false;
        }

    }

    @Override
    public void refuserConge(Conge conge) {

        conge.setEtat("refuser");
        updateEtat(conge);

    }

    public ObservableList<Conge> conn(int id) {
        ObservableList<Conge> conges = FXCollections.observableArrayList();
        String x = "En attente";
        String x2 = "accepter";
        try {
            String req = "select * FROM `conge` where `id`=? and ( etat like '%En attente%' )";
            ste = datasource.getConnection().prepareStatement(req);

            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Conge t = new Conge();

                t.setDatedebut(rs.getDate("datedebut"));
                t.setNbrjours(rs.getInt("nbrjours"));
                t.setType_conge(rs.getString("type_conge"));
                t.setRaison(rs.getString("raison"));
                t.setEtat(rs.getString("etat"));
                conges.add(t);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conges;
    }

    @Override
    public int getNbreJoursParTypeConge(String typeConge) {
        String query = "select type_conge,nbrjours FROM conge where type_conge =?";
        List<StatConge> listStat = new ArrayList<>();
        int nbreJoursTotal = 0;
        try {

            ste = datasource.getConnection().prepareStatement(query);
            ste.setString(1, typeConge);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                StatConge stat = new StatConge();
                stat.setNbJours(rs.getInt("nbrjours"));
                stat.setTypeConge(rs.getString("type_conge"));
                listStat.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (StatConge statConge : listStat) {
            nbreJoursTotal += statConge.getNbJours();
        }
        return nbreJoursTotal;
    }

    public ObservableList<Conge> Rechercher(ObservableList<Conge> liste, String Tconge) {
        try {
            String req = "select * FROM `conge` where `type_conge`= ?";

            ste = datasource.getConnection().prepareStatement(req);
            ste.setString(1, Tconge);
             //int idu;
           // idu = utilisateurService.loggedUser.getId();
           // ste.setInt(1, idu);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

               User user = new User();
               user.setId(rs.getInt("user"));
                liste.add(new Conge(rs.getInt("id"),
                         rs.getDate("datedebut"),
                        rs.getInt("nbrjours"),
                        rs.getString("type_conge"),
                        rs.getString("raison"),
                        rs.getString("etat"),user));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public ObservableList<Conge> RechercherEtat(ObservableList<Conge> liste, String etat, int id_user) {
        try {
            String req = "select * FROM `conge` where `etat`= '"+ etat + "' and `user`= '"+id_user+"'";
            
            

            ste = datasource.getConnection().prepareStatement(req);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

                //fos_user user = new fos_user();
                //user.setId(rs.getInt("user"));
                liste.add(new Conge(rs.getInt("id"),
                         rs.getDate("datedebut"),
                        rs.getInt("nbrjours"),
                        rs.getString("type_conge"),
                        rs.getString("raison"),
                        rs.getString("etat")/*,user*/));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

}
