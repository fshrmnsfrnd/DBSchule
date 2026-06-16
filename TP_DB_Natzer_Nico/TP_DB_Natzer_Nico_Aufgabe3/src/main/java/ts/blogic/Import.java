/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ts.blogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




/**
 *
 * @author ml
 */
public class Import {
    public static void loadData() throws SQLException {
        deleteAll();
        Connection cnn = DbConnect.getConnection("127.0.0.1", "3306", "root", "kennwort1");
        String sql = "SELECT KundenNr, Kunde, BeraterID, Berater, Stundensatz, Stunden FROM import";
        Statement stmt = cnn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int kundenNr = rs.getInt("KundenNr"); 
            String kunde = rs.getString("Kunde"); 
            String kvname = kunde.split(" ")[0];
            String knname = kunde.split(" ")[1];
            int beraterID = rs.getInt("BeraterID");
            String berater = rs.getString("Berater"); 
            String vname = berater.split(" ")[0];
            String nname = berater.split(" ")[1];
            String stundensatz = rs.getString("Stundensatz"); 
            int stunden = rs.getInt("Stunden");
            
            if(!exists("berater", "b_id", beraterID)){
                String insertBerater = "INSERT INTO berater (b_id, b_vname, b_nname) VALUES ("+ beraterID +", "+ vname +", "+ nname +")";
                PreparedStatement pstmtBerater = cnn.prepareStatement(insertBerater);
                pstmtBerater.execute();
            }

            if(!exists("kunde", "k_nr", kundenNr)){
                String insertKunde = "INSERT INTO kunde (k_nr, k_vname, k_nname) VALUES ("+ kundenNr +", "+ kvname +", "+ knname +")";
                PreparedStatement pstmtKunde = cnn.prepareStatement(insertKunde);
                pstmtKunde.execute();
            }
            
            if(!exists("beratungen", "k_nr", kundenNr) && !exists("beratungen", "b_id", beraterID)){
                String insertBeratungen = "INSERT INTO beratungen (k_nr, b_id, stundensatz, stunden) VALUES ("+ kundenNr +", "+ beraterID +", "+ stundensatz + ", "+ stunden +")";
                PreparedStatement pstmtBeratungen = cnn.prepareStatement(insertBeratungen);
                pstmtBeratungen.execute();
            }else{
                String sql2 = "SELECT Stunden FROM beratungen WHERE k_nr ="+ kundenNr + "AND b_id ="+ beraterID;
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                ResultSet rss = stmt.executeQuery(sql2);
                rs.next();
                int found = rs.getInt("Stunden");
                int neuStunden = found + stunden;
                String updateSQL = "UPDATE beratungen SET stunden ="+neuStunden+" WHERE k_nr ="+ kundenNr + "AND b_id ="+ beraterID; ;
            }
        } 
    }
    
    private static boolean exists(String tablename, String pkField, long pkValue) throws SQLException{

        Connection cnn = DbConnect.getConnection("127.0.0.1", "3306", "root", "kennwort1");
        
        String sql = "SELECT COUNT(*) as Anzahl FROM %table WHERE %pkField = %pkValue";
        sql = sql.replace("%table", tablename);
        sql = sql.replace("%pkField", pkField);
        sql = sql.replace("%pkValue", String.valueOf(pkValue));
        
        Statement stmt = cnn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        rs.next();
        long found = rs.getLong("Anzahl");
        
        return found > 0;
    }
    
    private static void deleteAll() throws SQLException{
        Connection cnn = DbConnect.getConnection("127.0.0.1", "3306", "root", "kennwort1");
        try{
            cnn.setAutoCommit(false);
            String sql = "DELETE FROM kunde";
            Statement stmt = cnn.createStatement();
            stmt.executeUpdate(sql);
            sql = "DELETE FROM berater";
            stmt = cnn.createStatement();
            stmt.executeUpdate(sql);
            sql = "DELETE FROM beratungen";
            stmt = cnn.createStatement();
            stmt.executeUpdate(sql);
            cnn.commit();
        }catch(SQLException e){
            cnn.rollback();
            System.err.println("Fehler! Rollback ausgeführt: " + e.getMessage());
        }finally {
            cnn.setAutoCommit(true);
	}
    }
}
