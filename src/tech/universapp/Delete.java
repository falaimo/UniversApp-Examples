package tech.universapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;

public class Delete {
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private final static String DELETE = "DELETE FROM Persone WHERE Nome=?";
    private final static String SELECT = "SELECT * FROM Persone";

    public static void main(String[] args) {
        //cancellare ennuple
        try (Connection conn = getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             PreparedStatement pstmt = conn.prepareStatement(DELETE)){
            pstmt.setString(1, "Maria");
            pstmt.execute();
            //Mostra le ennuple presenti
            try (ResultSet rs = stmt.executeQuery(SELECT)) {
                System.out.println("DELETE con condizione Nome='Maria'");
                while (rs.next()) {
                    System.out.println("--------------------------------------");
                    System.out.println("Nome= " + rs.getString("Nome"));
                    System.out.println("Eta'= " + rs.getInt("Eta"));
                    System.out.println("Reddito= " + rs.getInt("Reddito"));
                    System.out.println("--------------------------------------");
                }
            } catch (SQLException e) {
                Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
