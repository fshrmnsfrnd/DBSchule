/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Meinhard.Lingo
 */
public class MitgliedDao {
    
    public static ArrayList<Mitglied> getAll() throws SQLException
    {
        Connection con = null;
        ArrayList<Mitglied> results = new ArrayList<>();

        con = DbVerein.getConnection();

        //Statement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT M_Id,vorname,nachname, DATE(Geburtsdatum) as GDat,"
                                        + "geschlecht, strasse, ort_id, tel, DATE(Eintrittsdatum) as EDat"
                                        + " FROM Mitglied ORDER BY nachname;");

        //ORM: object-relational-mapping
        while (rs.next())
        {
            long id = rs.getInt("M_id");
            String vorname = rs.getString("Vorname");
            String nachname = rs.getString("Nachname");
            String geburtsdatum = rs.getString("GDat");
            String geschlecht = rs.getString("Geschlecht");
            String strasse = rs.getString("Strasse");
            long ort_id = rs.getLong("Ort_Id");
            String tel = rs.getString("Tel");
            String eintrittsdatum = rs.getString("EDat");
            
            //Mitglied-Objekt erzeugen
            results.add(new Mitglied(id,vorname,nachname,geburtsdatum,geschlecht,
                                      strasse,ort_id,tel,eintrittsdatum));
        }
        return results;
    }
    public static Mitglied getByID(long id) throws SQLException{
        
        Connection con = DbVerein.getConnection();
        Mitglied result = null;

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Mitglied WHERE m_id = ?;");
        ps.setLong(1,id);
        ResultSet rs = ps.executeQuery();

        //ORM: object-relational-mapping
        if (rs.next())
        {
            String vorname = rs.getString("Vorname");
            String nachname = rs.getString("Nachname");
            String geburtsdatum = rs.getString("Geburtsdatum");
            String geschlecht = rs.getString("Geschlecht");
            String strasse = rs.getString("Strasse");
            long ort_id = rs.getLong("Ort_Id");
            String tel = rs.getString("Tel");
            String eintrittsdatum = rs.getString("Eintrittsdatum");
            
            //Mitglied-Objekt erzeugen
            result = new Mitglied(id,vorname,nachname,geburtsdatum,geschlecht,
                                   strasse,ort_id,tel,eintrittsdatum);
        }        
        return result;
    }
    
    public static void insert(Mitglied o)throws SQLException{
        Connection con = DbVerein.getConnection();

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        String sql = "INSERT INTO Mitglied (Vorname,Nachname,Geburtsdatum,Geschlecht,"
                                        + " Strasse,Ort_id,Tel,Eintrittsdatum) "
                                        + " VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,o.getVorname());
        ps.setString(2,o.getNachname());
        ps.setString(3,o.getGeburtsdatum());
        ps.setString(4,o.getGeschlecht());
        ps.setString(5,o.getStrasse());
        ps.setLong(6,o.getOrtId());
        ps.setString(7,o.getTelefonnr());
        ps.setString(8,o.getEintrittsdatum());       
        
        //execute statement
        ps.execute();
  
        //Wie lautet die Sport_id der neuen Sportart? 
        ResultSet rs =  ps.getGeneratedKeys();
        rs.next();
        long newMId = rs.getLong(1);
        
        //update Property sportId in Sportart-Object
        o.setMitgliedId(newMId);
    }
    public static void update(Mitglied org,Mitglied upd)throws SQLException,Exception{
        Connection con = null;
        con = DbVerein.getConnection();
        

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        if (org.getMitgliedId() != upd.getMitgliedId()){
            throw new Exception("Die Mitglied-Id ist unterschiedlich! Kein speichern möglich.");
        }
        else {
            boolean dataChanged = false;
            
            String sql = "UPDATE Mitglied";
            if (!org.getVorname().equals(upd.getVorname())){
               sql = sql + " SET vorname = '" + upd.getVorname() + "'";
               dataChanged = true;
            }
            if (!org.getNachname().equals(upd.getNachname())){
                if (dataChanged){
                    sql = sql + ",";
                }
                sql = sql + " SET nachname ='" + upd.getNachname() + "'" ;
                dataChanged = true;
            }
            if (!org.getGeburtsdatum().equals(upd.getGeburtsdatum())){
                if (dataChanged){
                    sql = sql + ",";
                }
                sql = sql + " SET Geburtsdatum ='" + upd.getGeburtsdatum() + "'" ;
                dataChanged = true;
            }
            if (!org.getGeschlecht().equals(upd.getGeschlecht())){
                if (dataChanged){
                    sql = sql + ",";
                }
                sql = sql + " SET Geschlecht ='" + upd.getGeschlecht()+ "'" ;
                dataChanged = true;
            }
            if (!org.getStrasse().equals(upd.getStrasse())){
                if (dataChanged){
                    sql = sql + ",";
                }
                sql = sql + " SET Strasse ='" + upd.getStrasse()+ "'" ;
                dataChanged = true;
            }            
            if (org.getOrtId()!= upd.getOrtId()){
                if (dataChanged){
                    sql = sql + ",";
                }
                sql = sql + " SET Ort_id =" + upd.getOrtId();
                dataChanged = true;
            }
            if (!org.getTelefonnr().equals(upd.getTelefonnr())){
                if (dataChanged){
                    sql = sql + ",";
                }
                sql = sql + " SET Tel ='" + upd.getTelefonnr() + "'";
                dataChanged = true;
            }  
            if (!org.getEintrittsdatum().equals(upd.getEintrittsdatum())){
                if (dataChanged){
                    sql = sql + ",";
                }
                sql = sql + " SET Eintrittsdatum ='" + upd.getEintrittsdatum() + "'";
                dataChanged = true;
            }             
            sql = sql + " WHERE m_id = ?";

            if (dataChanged)
            {   
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1,upd.getMitgliedId());
                ps.execute();
            }        
        }
    }    
    public static void delete(Mitglied o) throws SQLException{
        Connection con = DbVerein.getConnection();

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        String sql = "DELETE FROM mitglied WHERE m_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1,o.getMitgliedId());

        ps.execute();
    }
}
