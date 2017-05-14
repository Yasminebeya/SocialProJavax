/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import social_pro.entites.Panier;
import social_pro.entites.Produit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import social_pro.utiles.DataSource;

/**
 *
 * @author Navoxx
 */
public class PanierService {

    private List<Produit> articles;
    PreparedStatement ste;
    DataSource mc;

    public PanierService() {
        mc = DataSource.getInstance();
        this.articles = new ArrayList<Produit>();
    }

    public int getPanierByEtat() {
        try {

            int num = 0;
            String req = "Select numero from panier where etat='Fini'";
            ste = mc.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                num = rs.getInt("numero");
                num = num + 1;
                System.out.println("num=" + num);
                return num;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public void listePanier(ObservableList<Panier> Liste_Panier) {
        try {
            String req = "select * FROM `panier` ";
            ste = mc.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Liste_Panier.add(new Panier(rs.getInt("numero"), rs.getString("etat"), rs.getInt("produit")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addProduit(int id) {
        String req = "insert into panier (produit,numero,etat,user) values (?,?,?,?) ";
        try {
            Panier p = new Panier();
            int n = getPanierByEtat();

            //   System.out.println("nummm="+n);
            ste = mc.getConnection().prepareStatement(req);
      int idu = UserService.loggeduser.getId();
            ste.setInt(1, id);
            ste.setInt(2, n);
            ste.setString(3, "En Cours");
            ste.setInt(4,idu);

            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showPanier(ObservableList<Produit> Liste_Produit,int user) {
        try {
            String req = "SELECT * FROM produit,panier where produit.id=panier.produit and panier.etat='En Cours' and user="+user;
            ste = mc.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Liste_Produit.add(new Produit(rs.getString("libelle"), rs.getString("typeproduit"), rs.getDouble("prix")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ValiderPanier(Panier p) throws SQLException {

        String req = "update panier SET etat=? WHERE etat='En Cours'";
        ste = mc.getConnection().prepareStatement(req);

        ste.setString(1, "Fini");
        ste.executeUpdate();

    }

    public double CalculerPanier(int user) {
        double somme = 0;
        try {
            String req = "SELECT produit.prix FROM produit,panier where produit.id=panier.produit and panier.etat='En Cours' and user="+user;
            ste = mc.getConnection().prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                double prix = rs.getInt("prix");
                somme = somme + prix;
                System.out.println("somme=" + somme);

            }
            return somme;
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void Louer(int id, Produit p) {
        String req = "update produit SET datedebut= ?,datefin= ? WHERE id="+id;
        System.out.println("id req" + id);
        try {
            ste = mc.getConnection().prepareStatement(req);

            ste.setDate(1, p.getDatedebut());
            ste.setDate(2, p.getDatefin());
            
            ste.executeUpdate();
            System.out.println("produit Louer");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modification" + ex.getMessage());
        }

    }
}
