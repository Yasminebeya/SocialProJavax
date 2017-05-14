/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.interfaces;

import java.sql.ResultSet;
import java.util.List;
import social_pro.entites.Evenement;
import social_pro.entites.User;

/**
 *
 * @author smart
 */
public interface IEvenement {
       
      
        public void confirmEvent(int id);
        public List<Evenement> getparticipatedevents(int idConnected);
      //   public List<Evenement> getmyEvents(int idConnected);
        public ResultSet recherche(String v);
}


