/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.entites;

import java.sql.Date;
import java.util.List;
import javafx.collections.ObservableList;
//import javafx.scene.control.DatePicker;


public class Conge {
    private int id;
    private Date datedebut;
    private int nbrjours;
    private String raison;
    private String type_conge;
    private String etat;
   private User user;

    public Conge() {
    }

    public Conge(Date datedebut, int nbrjours,String type_conge, String raison, String etat) {
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
         this.type_conge = type_conge;
        this.raison = raison;
        this.etat = etat;
    }

    public Conge(int id, Date datedebut, int nbrjours, String type_conge,String raison, String etat, User user) {
       this.id = id;
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
        this.raison = raison;
        this.type_conge = type_conge;
        this.etat = etat;
        this.user = user;
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Conge(int id, Date datedebut, int nbrjours, String type_conge,String raison) {
        this.id = id;
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
        this.raison = raison;
        this.type_conge = type_conge;
        
    }

    public Conge(Date datedebut, int nbrjours,String type_conge, String raison) {
        
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
        this.raison = raison;
        this.type_conge = type_conge;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datebedut) {
        this.datedebut = datebedut;
    }

    public int getNbrjours() {
        return nbrjours;
    }

    public void setNbrjours(int nbrjours) {
        this.nbrjours = nbrjours;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getType_conge() {
        return type_conge;
    }

    public void setType_conge(String type_conge) {
        this.type_conge = type_conge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Conge{" + "id=" + id + ", datebedut=" + datedebut + ", nbrjours=" + nbrjours + ", raison=" + raison + ", type_conge=" + type_conge + ", user=" + user + '}';
    }

   /* public ObservableList<String> getType_conge(ObservableList<String> options) {
              return options;
    }*/

    public Conge(int id, Date datedebut, int nbrjours, String type_conge,String raison, String etat) {
        this.id = id;
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
        this.raison = raison;
        this.type_conge = type_conge;
        this.etat = etat;
    }

    

    
    
}
