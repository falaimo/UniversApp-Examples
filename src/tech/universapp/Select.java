package tech.universapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;

public class Select {
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    public static void main(String[] args) {
        //Mostra le ennuple corrispondenti ad una condizione (passando una stringa)
        try (Connection conn = getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()){
            //Predispongo la query
            String Query= "SELECT * FROM Persone";
            //Mostra le ennuple presenti che rispondono alla query
            try (ResultSet rs = stmt.executeQuery(Query)) {
                System.out.println("SELECT * FROM Persone");
                while (rs.next()) {
                    System.out.println("--------------------------------------");
                    System.out.println("Nome= " + rs.getString("Nome"));
                    System.out.println("Eta'= " + rs.getInt("Eta"));
                    System.out.println("Reddito= " + rs.getInt("Reddito"));
                    System.out.println("--------------------------------------");
                }
            }
            catch (SQLException e) {
                Logger.getLogger(Select.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Select.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
