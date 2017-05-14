/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.entites;

/**
 *
 * @author Yass
 */
public class StatConge {
    
    private int nbJours;
    private String typeConge;
    
    public StatConge() {
    }

    public StatConge(int nbJours, String typeConge) {
        this.nbJours = nbJours;
        this.typeConge = typeConge;
    }

    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    public String getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(String typeConge) {
        this.typeConge = typeConge;
    }
    
    
    
}
