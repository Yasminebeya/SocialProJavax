/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.List;

/**
 *
 * @author Navoxx
 */
    public interface PiInteface <T> {
    
    public void ajouter (T t);
    public void supprimer (int id );
    public void modifier (int id , T t);
    public List<T> lister(); 
    
    
}

    

