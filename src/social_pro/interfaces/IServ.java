package social_pro.interfaces;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oumaima
 */
public interface IServ<T> {
    
    public void add(T t);
    public void remove(int id);
    public void update(int id,T t);
    
    
 
    
    
}
