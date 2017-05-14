

package social_pro.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author smart
 */
public class DataSource {
   private String url = "jdbc:mysql://localhost:3306/social_pro";
    private String username="root";
    private String password="";
    private Connection connection; // la varible pont qui va assuer la cnx
    private static DataSource instance;  // la seule instance

    private DataSource() {
       
        try {
            connection=DriverManager.getConnection(url,username,password);// clase driverManager
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() 
    {
        if (instance==null)
        instance=new DataSource();
        return instance;
    }
    //singleton classe instanciable une seule fois
     
    
    
}
