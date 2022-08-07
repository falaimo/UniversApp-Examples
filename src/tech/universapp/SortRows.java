package tech.universapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortRows {
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    public static void main(String[] args) {
        //Ordinare ennuple
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()){
            String Query= "SELECT * FROM Persone ORDER BY Reddito DESC";
            try (ResultSet rs = stmt.executeQuery(Query)) {
                while (rs.next()) {
                    System.out.println("--------------------------------------");
                    System.out.println("Nome= " + rs.getString("Nome"));
                    System.out.println("Eta'= " + rs.getInt("Eta"));
                    System.out.println("Reddito= " + rs.getInt("Reddito"));
                    System.out.println("--------------------------------------");

                }
            }
            catch (SQLException e) {
                Logger.getLogger(SortRows.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        catch (SQLException e) {
            Logger.getLogger(SortRows.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
