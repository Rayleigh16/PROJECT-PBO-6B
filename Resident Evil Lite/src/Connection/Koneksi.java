package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Koneksi {
    private String url = ("jdbc:mysql://localhost/zombieshooter");
    private String user = "root";
    private String pass = "";
     Connection con = null;
     
    public Connection connect(){
       
        try{
            con = DriverManager.getConnection(url, user,pass);
            System.out.println("Suskes");
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return con;
    }
    
}
