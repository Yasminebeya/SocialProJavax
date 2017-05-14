/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.entites;

import java.io.File;
import java.sql.Blob;
import java.sql.Date;
import javafx.scene.image.Image;
import social_pro.service.UserService;

/**
 *
 * @author ahmed
 */
public class Publication {
             public static fos_user loggedUser;
         UserService userService = new UserService();

     private int userId = userService.loggeduser.getId();
    Date date0 = new Date(System.currentTimeMillis());
    private String PubUser = userService.loggeduser.getUsername();
    private  int jaime;
    private int jaimePas;
    private int id;
    private String text;
    private Date dateAj =date0  ;
     private byte[] PathJava;
    private String Path ;
     
  
    public Publication(int id, String text, Date dateAj) {
        this.id = id;
        this.text = text;
        this.dateAj = dateAj;
        
    }

    public Publication(String text, String Path) {
        this.text = text;
        this.Path = Path;
    }
    

    public Publication(String text,Date dateAj,String Path) {
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
    }
    
    public Publication(int id ,String text,Date dateAj,String Path) {
         this.id=id;
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
    }
 public Publication(int id ,String text,Date dateAj,String Path,int jaime,int jaimePas) {
         this.id=id;
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
        this.jaime=jaime;
        this.jaimePas=jaimePas;
    }
 
  public Publication(int id ,String text,Date dateAj,String Path,int jaime,int jaimePas,String PubUser) {
         this.id=id;
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
        this.jaime=jaime;
        this.jaimePas=jaimePas;
        this.PubUser=PubUser;
    }
  public Publication(int id ,String text,Date dateAj,String Path,int jaime,int jaimePas,String PubUser,int userId) {
         this.id=id;
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
        this.jaime=jaime;
        this.jaimePas=jaimePas;
        this.PubUser=PubUser;
        this.userId=userId;
    }
    public Publication(int id, String text) {
        this.id = id;
        this.text = text;
    }
    

   

    public Publication(int id, String text,Date dateAj, byte[] PathJava) {
        this.id = id;
        this.text = text;
        this.dateAj = dateAj;
        this.PathJava = PathJava;
    }
    

    public Publication(String text) {
        this.text = text;
    }
    public Publication() {
        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateAj() {
        return dateAj;
    }

    public void setDateAj(Date dateAj) {
        this.dateAj = dateAj;
    }

   

    public byte[] getPathJava() {
        return PathJava;
    }

    public void setPathJava(byte[] PathJava) {
        this.PathJava = PathJava;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public int getJaime() {
        return jaime;
    }

    public void setJaime(int jaime) {
        this.jaime = jaime;
    }

    public int getJaimePas() {
        return jaimePas;
    }

    public void setJaimePas(int jaimePas) {
        this.jaimePas = jaimePas;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPubUser() {
        return PubUser;
    }

    public void setPubUser(String PubUser) {
        this.PubUser = PubUser;
    }

  
   

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", text=" + text + ", dateAj=" + dateAj + '}';
    }

    public void getPathJava(File absoluteFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
