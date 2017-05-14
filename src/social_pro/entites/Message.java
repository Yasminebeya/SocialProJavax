
package social_pro.entites;

import java.sql.Date;

public class Message {
    
    private int id;
    private String objet;
    private String text;
    private int user_id;
    private String nom_user;
    private Date datedenvoye;
    
    public Message(){}

    public Message(int id ,String objet, String text, int employe_id, Date datedenvoye) {
        this.id=id;
        this.objet = objet;
        this.text = text;
        this.user_id = employe_id;
        this.datedenvoye = datedenvoye;
    }
    
    public Message(String objet, String text, int employe_id, Date datedenvoye) {
      
        this.objet = objet;
        this.text = text;
        this.user_id = employe_id;
        this.datedenvoye = datedenvoye;
    }

   

    public int getId() {
        return id;
    }

    public String getObjet() {
        return objet;
    }

    public String getText() {
        return text;
    }

    public int getEmploye_id() {
        return user_id;
    }

    public String getNom_user() {
        return nom_user;
    }

    public Date getDatedenvoye() {
        return datedenvoye;
    }
    
    
    
    
}
