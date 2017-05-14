
package social_pro.entites;


import java.sql.Date;
import java.util.List;


public class Tache {
    private int id;
    private Date datedebut;
    private Date datefin;
    private String description;
    private String etat;
    private List<fos_user> list_user;
    private int matricule_id;
    private String username;


   
    
    public Tache()
    {}
     public Tache(int id_matricule)
    {
        this.matricule_id=id_matricule;
    }
    
    public Tache(Date datedebut,Date datefin,String description,int id_matricule,String etat)
    {
        this.datedebut=datedebut;
        this.datefin=datefin;
        this.description=description;
        this.matricule_id=id_matricule;
        this.etat=etat; 
    }
    
    public Tache(Date datedebut,Date datefin,String description,String etat)
    {
        this.datedebut=datedebut;
        this.datefin=datefin;
        this.description=description;
        this.etat=etat; 
    }
    
     public Tache(String description,String etat)
    {
      
        this.description=description;
        this.etat=etat; 
    }

    public Tache(Date datedebut, Date datefin, String description, String etat, List<fos_user> list_user) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.etat = etat;
        this.list_user = list_user;
    }
    
      public Tache(Date datedebut, Date datefin, String description) {
        
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        
    }
      
        public Tache(int id,Date datedebut, Date datefin, String description) {
        this.id=id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        
    }
        
          public Tache(int id,Date datedebut, Date datefin, String description,int matricule_id) {
        this.id=id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.matricule_id=matricule_id;
        
    }
      
      
      public Tache(Date datedebut, Date datefin ){
        this.datedebut = datedebut;
        this.datefin = datefin;
       
        
    }

    public Tache(int id, Date datedebut, Date datefin, String description, int matricule_id, String username) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.matricule_id = matricule_id;
        this.username = username;
    }

    public List<fos_user> getList_user() {
        return list_user;
    }

    public void setList_user(List<fos_user> list_user) {
        this.list_user = list_user;
    }

  

    public int getId() {
        return id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
 public void setMatricule_id(int matricule_id) {
        this.matricule_id = matricule_id;
    }
    
    public int getMatricule_id() {
        return matricule_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String toString() {
        return "Tache{" + "id=" + id + ", datedebut=" + datedebut + ", datefin=" + datefin + ", description=" + description + ", etat=" + etat + '}';
    }
    
    
    
    
    
}
