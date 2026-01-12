/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Meinhard.Lingo
 */
public class SportartDao {
    
    public static ArrayList<Sportart> getAll() throws SQLException
    {
        Connection con = null;
        ArrayList<Sportart> results = new ArrayList<>();

        con = DbVerein.getConnection();

        //Statement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Sportart ORDER BY sport_id;");

        //ORM: object-relational-mapping
        while (rs.next())
        {
            long id = rs.getInt("sport_id");
            String sportart = rs.getString("sportart");
            float beitrag = rs.getFloat("beitrag");
            //Sport-Objekt erzeugen
            results.add(new Sportart(id,sportart,beitrag));
        }
        return results;
    }
    public static Sportart getByID(long id) throws SQLException{
        
        Connection con = null;
        Sportart result = null;
        con = DbVerein.getConnection();

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Sportart WHERE sport_id = ?;");
        ps.setLong(1,id);
        ResultSet rs = ps.executeQuery();

        //ORM: object-relational-mapping
        if (rs.next())
        {
            long sport_id = rs.getLong("sport_id");
            String sportart = rs.getString("sportart");
            float beitrag = rs.getFloat("beitrag");
            
            //Sport-Objekt erzeugen
            result = new Sportart(id,sportart,beitrag);
        }
        
        return result;
    }
    
    public static void insert(Sportart o)throws SQLException{
        Connection con = DbVerein.getConnection();

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        String sql = "INSERT INTO Sportart (sportart,beitrag) VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,o.getSportart());
        ps.setFloat(2,o.getBeitrag());
        
        //execute statement
        ps.execute();
  
        //Wie lautet die Sport_id der neuen Sportart? 
        ResultSet rs =  ps.getGeneratedKeys();
        rs.next();
        long newSportId = rs.getLong(1);
        
        //update Property sportId in Sportart-Object
        o.setSportId(newSportId);
    }
    
    public static void update(Sportart org,Sportart upd)throws SQLException,Exception{
        Connection con = null;
        con = DbVerein.getConnection();
        

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        if (org.getSportId() != upd.getSportId()){
            throw new Exception("Die Sportart-Id ist unterschiedlich! Kein speichern möglich.");
        }
        else {
            boolean dataChanged = false;
            
            String sql = "UPDATE Sportart";
            if (!org.getSportart().equals(upd.getSportart())){
               sql = sql + " SET sportart = '" + upd.getSportart() + "'";
               dataChanged = true;
            }
            if (org.getBeitrag()!= upd.getBeitrag()){
                if (dataChanged){
                    sql = sql + ",";
                }
                sql = sql + " SET beitrag =" + upd.getBeitrag() ;
                dataChanged = true;
            }
            sql = sql + " WHERE sport_id = ?";

            if (dataChanged)
            {   
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1,upd.getSportId());
                ps.execute();
            }        
        }
    }   
    
    public static void delete(Sportart o) throws SQLException{
        Connection con = DbVerein.getConnection();

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        String sql = "DELETE FROM sportart WHERE sport_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1,o.getSportId());

        ps.execute();

    }
}
