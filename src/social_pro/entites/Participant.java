/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.entites;

/**
 *
 * @author Oumaima
 */
public class Participant {
    private int id;
    private int user_id;
    private int idevenement;

    public Participant() {
    }

    public Participant(int id, int user_id, int idevenement) {
        this.id = id;
        this.user_id = user_id;
        this.idevenement = idevenement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    @Override
    public String toString() {
        return "Participant{" + "id=" + id + ", user_id=" + user_id + ", idevenement=" + idevenement + '}';
    }

    public Participant( int idevenement) {
        
        this.idevenement = idevenement;
    }
    
    
}
