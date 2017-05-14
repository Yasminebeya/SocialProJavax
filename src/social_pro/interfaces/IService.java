/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package social_pro.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import social_pro.entites.Message;
import social_pro.entites.Stat;
import social_pro.entites.Tache;
import social_pro.entites.fos_user;

/**
 *
 * @author smart
 */
public interface IService<T> {
     public void add(T t);
     public void delete(int id);
     public void updateback(int id,T t);
      public void updatefront(int id,T t);
     public List<T> getAll();
    public void convocation(Message c);
    public int nomUser(String nom);
    public void listeTacheNonAff(ObservableList<Tache> Liste_Tache);
    public void listeTacheNonRealise(ObservableList<Tache> tacheNonRalisee);
     public void listeTacheAff(ObservableList<Tache> Liste_Tache);
      public void listeMatEmp(ObservableList<fos_user> Liste_Matricule);
    public Tache getTacheById(int id);
    public void listeTacheLUser(ObservableList<Tache> Liste_Tache_aff,int mat_id);
    public void affecter(Tache t,int id);
    public void updateEtat(Tache t,int id,String etat);
      public void MessageUser(ObservableList<Message> Liste_Message,int emp_id);
       public void deleteMsg(int id);
       public List<Stat> getNbreTacheByEtat();
}
