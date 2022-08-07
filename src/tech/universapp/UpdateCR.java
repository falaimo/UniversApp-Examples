package tech.universapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;

public class UpdateCR {
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
            //disabilito autocommit
            conn.setAutoCommit(false);
            pstmt.setInt(1, 25);
            pstmt.execute();
            Savepoint safe=conn.setSavepoint();

            //Mostra le ennuple presenti
            try (ResultSet rs = stmt.executeQuery(SELECT)) {
                System.out.println("UPDATE Reddito=Reddito*1.1 con condizione Reddito < 25 come transazione");
                boolean RedditoOver = false;
                //Verifico se dopo l'aumento del Reddito ho superato 25
                while (rs.next()) {
                    if (rs.getInt("Reddito") > 25) {
                        System.out.println("Uno dei Redditi sta per essere aggiornato ad un valore > 25!");
                        System.out.println("Effettuato rollback!");
                        conn.rollback(safe);
                    } else {
                        RedditoOver = true;
                        break;
                    }
                }
                //non si sono verificati errori, posso chiudere la transaction
                if (!RedditoOver) {
                    conn.commit();
                    System.out.println("Effettuato commit.");
                }
            }
        }
        catch (SQLException e) {
            Logger.getLogger(UpdateCR.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
