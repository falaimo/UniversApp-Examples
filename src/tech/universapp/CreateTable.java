package tech.universapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateTable {
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private final static String DROP_TABLE = "DROP TABLE IF EXISTS Persone";
    private final static String CREATE_TABLE = "CREATE TABLE Persone (\n" +
            "     Nome CHAR(30) NOT NULL,\n" +
            "     Eta INTEGER,\n" +
            "     Reddito INTEGER\n" +
            ");";
    public static void main(String[] args) {
        //Cancello la tabella se è presente e poi la ricreo
        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement()) {
            try {
                System.out.println("DROP E CREATE DELLA TABELLA PERSONE");
                //se la tabella esiste la cancello
                stmt.executeUpdate(DROP_TABLE);
                //poi la creo
                stmt.executeUpdate(CREATE_TABLE);

            } catch (SQLException e) {
                Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, e);
            }
        } catch (SQLException e) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
