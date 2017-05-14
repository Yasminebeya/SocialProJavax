/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package social_pro.interfaces;

import java.util.List;

/**
 *
 * @author smart
 */
public interface IService_1<T> {
     public void add(T t);
     public void delete(int id);
     
      public void updatefront(int id,T t);
   
    
}
