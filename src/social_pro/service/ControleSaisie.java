/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.service;

import java.sql.Date;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;


/**
 *
 * @author azerty
 */
public class ControleSaisie {
      public static boolean isDateRetour(String debut, String fin) throws ParseException {

        boolean isDateRetour = true;

        int datedebut = Integer.parseInt(debut.substring(0, 4));
        System.out.println("datedebut"+datedebut);
        
        
        int datefin = Integer.parseInt(fin.substring(0, 4));
        System.out.println("datefin"+datefin);
      
        if (datedebut > datefin) {
            isDateRetour = false;
        } else if (datedebut <= datefin) {
            isDateRetour = true;
        } 
        return isDateRetour;

    }
      
      public static boolean isDate(Date dDebut, Date dFin){
          
          boolean isDate = true;
      
        if(dDebut.after(dFin))
        { isDate = false; }
        return isDate;
      }
      
     public static boolean isString(String text) {

        if (text.matches("^[a-zA-Z]+$")) {
            return true;
        } else {
            return false;
        }
    }
      public static boolean isUsername(String text) {

        if (text.matches("^[A-Za-z0-9]+$")) {
            return true;
        } else {
            return false;
        }
    }
      public static boolean adresse(String text) {

        if (text.matches("^[A-Z a-z 0-9]+$")) {
            return true;
        } else {
            return false;
        }
    }
       public static boolean isnumber(String text) {

        if (text.matches("^[0-9]+$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNbPlace(String text) {

        try {
            Integer.parseInt(text);
            if (Integer.parseInt(text) >= 1 && Integer.parseInt(text) <= 4) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

      private static Matcher matcher;
     private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
     
     
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static final String pwd=  "^[A-Za-z0-9]+$";
    private static Pattern pattern1 = Pattern.compile(pwd);
     public static boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
      public static boolean validate1(final String hex) {
        matcher = pattern1.matcher(hex);
        return matcher.matches();
    }
      
      
}
