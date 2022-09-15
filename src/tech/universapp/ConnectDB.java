package tech.universapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {
    private final static String URL = "jdbc:mysql://localhost:3306/";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Collegamento effettuato");
        }
        catch (SQLException e) {
            System.out.println("Si è verificato un problema nella connessione a MySQL.");
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
