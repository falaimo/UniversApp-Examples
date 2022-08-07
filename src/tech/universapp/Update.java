package tech.universapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;

public class Update {
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private final static String UPDATE = "UPDATE Persone SET Reddito=Reddito*1.1 WHERE Reddito < ?";
    private final static String SELECT = "SELECT * FROM Persone";

    public static void main(String[] args) {
        //Update di ennuple  che soddisfano una condizione
        try(Connection conn = getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE)){
            pstmt.setInt(1, 25);
            pstmt.execute();
            //Mostra le ennuple presenti
            try (ResultSet rs = stmt.executeQuery(SELECT)) {
                System.out.println("UPDATE Reddito=Reddito*1.1 con condizione Reddito<25");
                while (rs.next()) {
                    System.out.println("--------------------------------------");
                    System.out.println("Nome= " + rs.getString("Nome"));
                    System.out.println("Eta'= " + rs.getInt("Eta"));
                    System.out.println("Reddito= " + rs.getInt("Reddito"));
                    System.out.println("--------------------------------------");

                }
            } catch (SQLException e) {
                Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
