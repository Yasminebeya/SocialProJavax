/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.interfaces;

import java.util.List;
import social_pro.entites.StatConge;

/**
 *
 * @author Yass
 */
public interface CongeInterface <T> {
   public void add(T t); 
   public void remove(int id);
   public void Update(int id,T t);
   public List<T> getAll();
   public boolean validerConge(T t);
    public void refuserConge(T t);
   public void updateEtat(T t);
   public int getNbreJoursParTypeConge(String typeConge);
}
