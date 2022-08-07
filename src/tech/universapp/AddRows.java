package tech.universapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRows {
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private final static String INSERT = "INSERT INTO Persone " + "(Nome, Eta ,Reddito) VALUES (?,?,?)";
    private final static String SELECT = "SELECT * FROM Persone";

    public static void main(String[] args) {
        //Aggiunta di ennuple
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             PreparedStatement pstmt = conn.prepareStatement(INSERT)) {
            pstmt.setString(1, "Andrea");
            pstmt.setInt(2, 27);
            pstmt.setInt(3, 24);
            pstmt.execute();
            pstmt.setString(1, "Aldo");
            pstmt.setInt(2, 25);
            pstmt.setInt(3, 15);
            pstmt.execute();
            pstmt.setString(1, "Maria");
            pstmt.setInt(2, 55);
            pstmt.setInt(3, 42);
            pstmt.execute();

            //Mostra tutte le ennuple presenti
            try (ResultSet rs = stmt.executeQuery(SELECT)) {
                System.out.println("INSERIMENTO DI ENNUPLE");
                while (rs.next()) {
                    System.out.println("--------------------------------------");
                    System.out.println("Nome= " + rs.getString("Nome"));
                    System.out.println("Eta'= " + rs.getInt("Eta"));
                    System.out.println("Reddito= " + rs.getInt("Reddito"));
                    System.out.println("--------------------------------------");
                }
            }
            catch (SQLException e) {
                Logger.getLogger(AddRows.class.getName()).log(Level.SEVERE, null, e);}
        }
        catch (SQLException e) {
            Logger.getLogger(AddRows.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
