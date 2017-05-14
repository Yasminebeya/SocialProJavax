/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.service;

import social_pro.entites.Evenement;
import social_pro.interfaces.IServ;
import java.util.List;
import social_pro.entites.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import social_pro.entites.BCrypt;
import social_pro.interfaces.IUtilisateur;

import social_pro.utiles.DataSource;

/**
 *
 * @author Oumaima
 */
public class UserService implements IServ<User>, IUtilisateur {

    PreparedStatement ste, ste1;
    DataSource dataSource;
    User u = new User();

    public static User loggeduser;

    public UserService() {
        dataSource = DataSource.getInstance();
    }
    
            private final int workload = 13;

 public String hashPassword(String password) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password, salt);
        String FixedPass = hashed_password.substring(0, 2) + 'y' + hashed_password.substring(3);
        return (FixedPass);
    }
 public boolean checkPassword(String password,String DbHash) {
        boolean password_verified = false;
                    System.out.println(DbHash);

      /*  if (null == DbHash || !DbHash.startsWith("$2a$")) {
            System.out.println(DbHash);
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }*/
        password_verified = BCrypt.checkpw(password, DbHash);
       // System.out.println(BCrypt.hashpw(password, DbHash));
//        BCrypt.hashpw(password, DbHash);
        return (password_verified);
    }
    
    @Override
    public void add(User u) {

        
        try {

            String req = "INSERT INTO fos_user(username, username_canonical ,email,email_canonical,password,datenaissance,num_telephone,poste,departement,nom,prenom,sexe,cin,roles,image) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            ste = dataSource.getConnection().prepareStatement(req);

            ste.setString(1, u.getUsername());
            ste.setString(2, u.getUsername_canonical());
            ste.setString(3, u.getEmail());
            ste.setString(4, u.getEmail_canonical());
            ste.setString(5, u.getPassword());
            ste.setDate(6, new java.sql.Date(u.getDatenaissance().getTime()));
            ste.setInt(7, u.getNum_telephone());
            ste.setString(8, u.getPoste());
            ste.setString(9, u.getDepartement());
            ste.setString(10, u.getNom());
            ste.setString(11, u.getPrenom());
            ste.setString(12, u.getSexe());
            ste.setString(13, u.getCin());
            ste.setString(14, u.getRoles());
            ste.setString(15, u.getImage());

            ste.executeUpdate();

        } catch (SQLException ex) {
        
        }
    }

    public int getIdByEmail(String email) throws SQLException {
        String req = "select id from fos_user where email = ?";
        ste = dataSource.getConnection().prepareStatement(req);

        ste.setString(1, email);
        ResultSet rs = ste.executeQuery();
        if (rs.first()) {
            return rs.getInt("id");
        } else {
            return -1;
        }

    }

    public int getIdByUsername(String username) throws SQLException {
        String req = "select id from fos_user where username = ?";
        ste = dataSource.getConnection().prepareStatement(req);

        ste.setString(1, username);
        ResultSet rs = ste.executeQuery();
        if (rs.first()) {
            return rs.getInt("id");
        } else {
            return -1;
        }

    }

    @Override
    public void remove(int id) {
        try {
            String req = "Delete from fos_user where id=?";

            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void confirmMember(int id) {
        try {
            String req = "update fos_user set enable=1 where id=?";

            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Override
    public void desactiverCompte(int id) {
        try {
            String req = "update fos_user set enable=0 where id=?";

            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(int id, User u) {
        String req = "UPDATE `fos_user`  SET `username`=?,`email`=?, `password`=?, `dateembauche`=?, `datenaissance`=?,`cin`=?, `poste`=?, `departement`=?, `nom`=? , `prenom`=?, `sexe`=? WHERE `id`=?";
        try {
            ste = dataSource.getConnection().prepareStatement(req);

            ste.setString(1, u.getUsername());
            ste.setString(2, u.getEmail());
            ste.setString(3, u.getPassword());

            ste.setDate(5, new java.sql.Date(u.getDatenaissance().getTime()));
            ste.setString(6, u.getCin());
            ste.setString(7, u.getPoste());
            ste.setString(8, u.getDepartement());
            ste.setString(9, u.getNom());
            ste.setString(10, u.getPrenom());
            ste.setString(11, u.getSexe());
            ste.setInt(12, id);
            ste.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {

            String req = "SELECT * FROM fos_user";
            ste = dataSource.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id")); // p.setId(rs.getInt(1)); position de l'id  est 1 dans la table 
                u.setUsername(rs.getString("username"));//p.setNom(rs.getString(1));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setDateembauche(rs.getDate("dateembauche"));
                u.setDatenaissance(rs.getDate("datenaissance"));
                u.setCin(rs.getString("cin"));
                u.setPoste(rs.getString("poste"));
                list.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getUtilisateur(String email, String pass) throws SQLException {

        String req = "SELECT * FROM fos_user WHERE email=?";

        ste = dataSource.getConnection().prepareStatement(req);
        ste.setString(1, email);
        ResultSet rs = ste.executeQuery();

        if (rs.next()) {
            if (BCrypt.checkpw(pass, rs.getString("password"))) {
                return -1;
            } else {
                return rs.getInt("id");
            }
        } else {
            return -1;
        }
    }

    public User getUserbyId(int id) {
        try {
            String req = "select * from fos_user where id=?";
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();

            if (rs.first()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setCin(rs.getString("cin"));
                u.setNom(rs.getString("nom"));
                u.setUsername(rs.getString("username"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setNum_telephone(rs.getInt("num_telephone"));
                u.setPoste(rs.getString("poste"));
                u.setDepartement(rs.getString("departement"));
                u.setRoles(rs.getString("roles"));
              //  u.setImage(rs.getString("image"));

                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ResultSet recherche(String v) {

        ResultSet result = null;
        String sql = "SELECT * FROM fos_user WHERE id LIKE '%" + v + "%' OR nom LIKE '%" + v + "%' or email LIKE '%" + v + "%'  ORDER BY id DESC";;
        try {

            ste = dataSource.getConnection().prepareStatement(sql);

            result = ste.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;

    }

    @Override
    public boolean existeUtilisateur(String username) {

        String req = "select * from fos_user WHERE username=?";
        try {
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setString(1, username);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                loggeduser = new User();
                loggeduser.setId(rs.getInt("id"));
                loggeduser.setUsername(rs.getString("username"));
                loggeduser.setRoles(rs.getString("roles"));
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public User findUtilisateurByLoginMdp(String username) {

        String req = "select * from fos_user WHERE username=? AND enable=1";
        try {
            User user = new User();
            ste = dataSource.getConnection().prepareStatement(req);
            ste.setString(1, username);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRoles(rs.getString("roles"));
                user.setPassword(rs.getString("password"));

                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void afficher(ObservableList<User> Liste_User) {
        try {
            String req = "select * FROM `fos_user` ORDER BY enable ASC ";
            ste = dataSource.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Liste_User.add(new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("num_telephone"), rs.getInt("enable"),
                        rs.getString("cin"), rs.getDate("datenaissance"), rs.getString("poste"), rs.getString("departement"), rs.getString("roles")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
