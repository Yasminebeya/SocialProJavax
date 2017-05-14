/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.service;

import social_pro.entites.Evenement;
import social_pro.entites.Participant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import social_pro.entites.User;
import social_pro.interfaces.IEvenement;
import social_pro.interfaces.IServ;

import social_pro.utiles.DataSource;

/**
 *
 * @author Oumaima
 */
public class EvenementService implements IServ<Evenement>, IEvenement {

    PreparedStatement ste, ste1;
    DataSource dataSource;

    public EvenementService() {
        dataSource = DataSource.getInstance();
    }
    UserService us = new UserService();
    
    private static EvenementService instance;

    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;
    }


    @Override
    public void add(Evenement e) {
        try {

            String req = "INSERT INTO evenement(nom ,categorie, datedebut, datefin, description,nbrelimite,flag,user_id,lieu,nbrparticipants,image) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            ste = dataSource.getConnection().prepareStatement(req);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getCategorie());
            ste.setDate(3, new java.sql.Date(e.getDatedebut().getTime()));
            ste.setDate(4, new java.sql.Date(e.getDatefin().getTime()));
            ste.setString(5, e.getDescription());
            ste.setInt(6, e.getNbrelimite());
            ste.setInt(7, e.getFlag());
            ste.setInt(8, UserService.loggeduser.getId());
            ste.setString(9, e.getLieu());
            ste.setInt(10, 0);
            ste.setString(11, e.getImage());
           
         

            ste.executeUpdate();

        } catch (SQLException ex) {
           // Logger.getLogger(PISocialProTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    @Override
    public void remove(int id) {
        try {
            String req = "DELETE FROM evenement WHERE id=?";
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Evenement e) {
        String req = "UPDATE `evenement`  SET `nom`=?,`categorie`=?, `description`=?, `datedebut`=?, `datefin`=?,`nbrelimite`=?,`lieu`=? WHERE `id`=?";
        try {
            ste = dataSource.getConnection().prepareStatement(req);

            ste.setString(1, e.getNom());
            ste.setString(2, e.getCategorie());
            ste.setString(3, e.getDescription());
            ste.setDate(4, new java.sql.Date(e.getDatedebut().getTime()));
            ste.setDate(5, new java.sql.Date(e.getDatefin().getTime()));
            ste.setInt(6, e.getNbrelimite());
            ste.setString(7, e.getLieu());
            ste.setInt(8, id);
            ste.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void confirmEvent(int id) {
        try {
            String req = "update evenement set flag=1 where id=?";

            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void myEvents(ObservableList<Evenement> Liste_Evenement) {

        try {
            String req = "SELECT * FROM participant p, evenement e where p.idevenement=e.id AND p.user_id=? ";
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, UserService.loggeduser.getId());
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Liste_Evenement.add(new Evenement(rs.getInt("id"), rs.getString("categorie"), rs.getString("nom"), rs.getString("description"),
                        rs.getInt("nbrelimite"), rs.getInt("nbrparticipants"), rs.getDate("datedebut"), rs.getDate("datefin"), rs.getInt("pourcentage")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficher(ObservableList<Evenement> Liste_Evenemnet) {

        try {
            String req = "select * FROM `evenement` where datedebut>=CURRENT_DATE and flag=1 ORDER by datedebut ASC ";
            ste = dataSource.getConnection().prepareStatement(req);
            //   ste.setInt(1, UserService.loggeduser.getId());
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Liste_Evenemnet.add(new Evenement(rs.getInt("id"), rs.getString("categorie"), rs.getString("nom"), rs.getString("description"),
                        rs.getInt("nbrelimite"), rs.getInt("nbrparticipants"), rs.getDate("datedebut"), rs.getDate("datefin"), rs.getString("lieu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficher1(ObservableList<Evenement> Liste_Evenemnet) {
        try {
            String req = "select * FROM `evenement` where datedebut>=CURRENT_DATE ORDER BY datedebut ASC";
            ste = dataSource.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Liste_Evenemnet.add(new Evenement(rs.getInt("id"), rs.getString("categorie"), rs.getString("nom"), rs.getString("description"),
                        rs.getInt("nbrelimite"), rs.getDate("datedebut"), rs.getDate("datefin"), rs.getString("lieu"), rs.getInt("flag"),rs.getString("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void affiche(ObservableList<Evenement> Liste_Evenemnet) {
        try {
            String req = "select * FROM `evenement` where datedebut>=CURRENT_DATE AND user_id=? ORDER BY datedebut ASC";
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, UserService.loggeduser.getId());
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Liste_Evenemnet.add(new Evenement(rs.getInt("id"), rs.getString("categorie"), rs.getString("nom"), rs.getString("description"),
                        rs.getInt("nbrelimite"), rs.getDate("datedebut"), rs.getDate("datefin"), rs.getString("lieu"), rs.getInt("flag"),rs.getString("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficher2(ObservableList<Evenement> Liste_Evenemnet) {
        try {
            String req1 = "update `evenement` set pourcentage=nbrparticipants*100/nbrelimite ";
            String req = "select * FROM `evenement` where datefin<CURRENT_DATE ORDER BY nbrelimite ASC  ";
            ste = dataSource.getConnection().prepareStatement(req);
            ste1 = dataSource.getConnection().prepareStatement(req1);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Liste_Evenemnet.add(new Evenement(rs.getInt("id"), rs.getString("categorie"), rs.getString("nom"), rs.getString("description"),
                        rs.getInt("nbrelimite"), rs.getInt("nbrparticipants"), rs.getDate("datedebut"), rs.getDate("datefin"), rs.getInt("pourcentage")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Evenement getEvenementById(int id) {
        try {

            String req = "Select * from evenement where id=?";
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();

            if (rs.first()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setCategorie(rs.getString("categorie"));
                e.setNom(rs.getString("nom"));
                e.setDescription(rs.getString("description"));
                e.setDatedebut(rs.getDate("datedebut"));
                e.setDatefin(rs.getDate("datefin"));
                e.setNbrelimite(rs.getInt("nbrelimite"));

                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Evenement> getEvenementByCat(String categorie) throws SQLException {
        List<Evenement> evenements = new ArrayList<>();
        String req = "select * from evenement where id and categorie like '%" + categorie + "%'";
        Evenement e = new Evenement();
        ResultSet rs = ste.executeQuery(req);
        EvenementService es = new EvenementService();
        Evenement evenement;

        while (rs.next()) {
            evenement = es.getEvenementById(rs.getInt("id"));
            e = new Evenement(rs.getInt("id"), rs.getString("categorie"), rs.getString("nom"), rs.getString("description"), rs.getInt("nbrelimite"), rs.getDate("datedebut"), rs.getDate("datefin"), rs.getString("lieu"));
            evenements.add(e);
        }
        return evenements;

    }
    
    
    @Override
    public List<Evenement> getparticipatedevents(int idconnected) {
        List<Evenement> list = new ArrayList<>();
        try {

            String req = "SELECT e.id FROM participant p, evenement e where p.idevenement=e.id AND p.user_id=?";
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, idconnected);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt("id")); // p.setId(rs.getInt(1)); position de l'id  est 1 dans la table 
                //e.setNom(rs.getString("nom"));//p.setNom(rs.getString(1));

                list.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    public void participer(int idEvenement, int idParticipant) {
        try {
            Evenement e = getEvenementById(idEvenement);
            Participant p = new Participant();
            String req = "INSERT INTO participant(id,idevenement,user_id) VALUES(?,?,?)";
            String req2 = "UPDATE evenement SET nbrelimite=nbrelimite-1, nbrparticipants=nbrparticipants+1 WHERE id=?";

            ste = dataSource.getConnection().prepareStatement(req);
            ste1 = dataSource.getConnection().prepareStatement(req2);
            ste.setInt(1, p.getId());
            ste.setInt(2, idEvenement);
            ste.setInt(3, idParticipant);
            ste1.setInt(1, idEvenement);

            //e.setNbrelimite(4);
            ste.executeUpdate();

            ste1.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void nePlusParticiper(int id) {
        try {
         
            Participant p = new Participant();
            String req = "DELETE from participant where id=?";
           

            ste = dataSource.getConnection().prepareStatement(req);
            
        
            ste.setInt(1, id);



            //e.setNbrelimite(4);
            ste.executeUpdate();

         

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Evenement> Search(String categorie) {
        ObservableList<Evenement> taches = FXCollections.observableArrayList();

        try {
            String req = "select * FROM `evenement` where `categorie`=? and datefin>CURRENT_DATE()";
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setString(1, categorie);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Evenement t = new Evenement();
                t.setId(rs.getInt("id"));
                t.setNom(rs.getString("nom"));
                t.setNbrelimite(rs.getInt("nbrelimite"));
                t.setDatedebut(rs.getDate("datedebut"));
                t.setDatefin(rs.getDate("datefin"));
                t.setDescription(rs.getString("description"));

                taches.add(t);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taches;
    }

    @Override
    public ResultSet recherche(String v) {

        ResultSet result = null;
        String sql = "SELECT * FROM evenement WHERE id LIKE '%" + v + "%' OR nom LIKE '%" + v + "%' or categorie LIKE '%" + v + "%' ORDER BY flag ASC ";;
        try {

            ste = dataSource.getConnection().prepareStatement(sql);

            result = ste.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;

    }

    public int Calculertotal() {
        String req = "SELECT COUNT(*) FROM evenement ";
        Evenement categorie = null;
        int nombreLignes = 0;
        try {
            ste = dataSource.getConnection().prepareStatement(req);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                nombreLignes = rs.getInt(1);
            }

           // resultSet.last();
            //on récupère le numéro de la ligne 
            // nombreLignes = resultSet.getRow();
            //on replace le curseur avant la première ligne 
            // resultSet.beforeFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreLignes;
    }

    public int Calculer(String categorie) {
        String req = "SELECT COUNT(*) AS count FROM evenement where categorie =?";
        Evenement cat = null;
        int nombreLignes = 0;
        try {
            ste = dataSource.getConnection().prepareStatement(req);

            ste.setString(1, categorie);
            ResultSet resultSet = ste.executeQuery();
            while (resultSet.next()) {
                nombreLignes = resultSet.getInt(1);
            }
        //  resultSet.last(); 
//            //on récupère le numéro de la ligne 
            // nombreLignes = resultSet.getRow(); 
//            //on replace le curseur avant la première ligne 
            // resultSet.beforeFirst(); 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreLignes;
    }
    
    
    
    
    
    public Participant getParticipantById(int id) {
        try {

            String req = "Select * from participant where id=?";
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();

            if (rs.first()) {
                Participant p = new  Participant();
                p.setId(rs.getInt("id"));
                p.setIdevenement(rs.getInt("idevenement"));
                p.setUser_id(rs.getInt("user_id"));
              
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    
   public void updateEvent(int idEvent){
        String req = "UPDATE `evenement`  SET `nbrelimite`=nbrelimite+1,`nbrparticipants`=nbrparticipants-1 WHERE `id`=?";
        try {
            ste = dataSource.getConnection().prepareStatement(req);

          
            ste.setInt(1, idEvent);
            ste.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
    }
   }
