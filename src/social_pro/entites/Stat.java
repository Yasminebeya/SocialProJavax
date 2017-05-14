
package social_pro.entites;


public class Stat {
    
    private int nbreTaches;
    private String etat;

    public Stat(int nbreTaches, String etat) {
        this.nbreTaches = nbreTaches;
        this.etat = etat;
    }

    public Stat() {
    }

    public int getNbreTaches() {
        return nbreTaches;
    }

    public void setNbreTaches(int nbreTaches) {
        this.nbreTaches = nbreTaches;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
