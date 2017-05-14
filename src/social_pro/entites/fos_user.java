package social_pro.entites;

import java.util.Date;

public class fos_user {
    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private String password;
    private String matricule; 
    private String poste;
    private String departement;
    private int cin;
    private int num_telephone;
    private Date datenaissance;
    private Date dateambauche;
    private float salaire;
    private String role;

    
    
    public fos_user(){}
    public fos_user(int id)
    {
        this.id=id;
    }
    
     public fos_user(String username)
    {
        this.username=username;
    }

    public fos_user(int id, String username, String username_canonical, String email, String email_canonical, String password, String matricule) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.matricule = matricule;
    }

    public fos_user(int id, String username, String username_canonical, String email, String email_canonical, String password, String matricule, String poste, String departement, int cin, int num_telephone, Date datenaissance, Date dateambauche, float salaire) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.matricule = matricule;
        this.poste = poste;
        this.departement = departement;
        this.cin = cin;
        this.num_telephone = num_telephone;
        this.datenaissance = datenaissance;
        this.dateambauche = dateambauche;
        this.salaire = salaire;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public String getPassword() {
        return password;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getPoste() {
        return poste;
    }

    public String getDepartement() {
        return departement;
    }

    public int getCin() {
        return cin;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public Date getDateambauche() {
        return dateambauche;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public void setDateambauche(Date dateambauche) {
        this.dateambauche = dateambauche;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "fos_user{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", password=" + password + ", matricule=" + matricule + ", poste=" + poste + ", departement=" + departement + ", cin=" + cin + ", num_telephone=" + num_telephone + ", datenaissance=" + datenaissance + ", dateambauche=" + dateambauche + ", salaire=" + salaire + '}';
    }
    
    
    
    
    
    
}
