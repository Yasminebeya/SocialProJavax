/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.interfaces;

import java.sql.ResultSet;
import java.util.List;
import social_pro.entites.User;

/**
 *
 * @author smart
 */
public interface IUtilisateur <T>{
        public boolean existeUtilisateur(String username);
        public User findUtilisateurByLoginMdp(String username);
        public void confirmMember(int id);
        public ResultSet recherche(String v);
        public List<T> getAll();
        public void desactiverCompte(int id);
}


