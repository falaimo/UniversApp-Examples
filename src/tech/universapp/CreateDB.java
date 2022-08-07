package tech.universapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateDB {
    private final static String URL = "jdbc:mysql://localhost:3306/";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private final static String CREATE_DB = "CREATE DATABASE dbtest";
    private final static String DROP_DB = "DROP DATABASE IF EXISTS dbtest";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()){
            try {
                System.out.println("DROP DATABASE, se esiste, e CREATE");
                //se il database esiste lo cancello
                stmt.executeUpdate(DROP_DB);
                //creo il database
                stmt.executeUpdate(CREATE_DB);
                }
                catch (SQLException e) {
                    Logger.getLogger(CreateDB.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            catch (SQLException e) {
                Logger.getLogger(CreateDB.class.getName()).log(Level.SEVERE, null, e);
            }
    }
}
