/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package social_pro.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import social_pro.entites.Tache;
import social_pro.service.TacheService;



/**
 *
 * @author smart
 */
public class test {
    

 
    public static void main(String[] args) {
        
      
       java.sql.Date date1 = java.sql.Date.valueOf("9999-09-28");
         java.sql.Date date2 = java.sql.Date.valueOf("1567-09-28");
        
        
     Tache t = new Tache(date1,date2,"zzzzzzzzz",8,"zzzzzzzz");
  System.out.print(t.getMatricule_id());
      TacheService ps=new TacheService();
      //ps.add(t);
      //ps.add(t);
      //ps.remove(94); 
     //System.out.println( ps.getAll());
     
     ps.add(t);
      //System.out.println( ps.getTachenonrealise());
     
    }   
         
         
}
