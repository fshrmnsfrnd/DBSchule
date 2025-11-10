/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


/**
 * Code-Analyse
 * 
 * @author (ml) 
 * @version (09.11.2025)
 */
package blogic;

import java.sql.*;

public class App
{
    public static void main(String args[])
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql= null;
        int mId = 0;
        int sportId = 0;


        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbVerein","root","");
            //Start Transaction
            con.setAutoCommit(false);
            stmt = con.createStatement();
           

            //neues Mitglied
            sql ="Insert into mitglied (Vorname,Nachname,Geburtsdatum,Geschlecht, Strasse,Ort_id,Tel)";
            sql = sql + " VALUES ('Max','Mustermann','1981-01-01','m','Bahnhofstr. 10',21,'11111');";
            stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            rs.next();
            mId = rs.getInt(1);
            
            //neue Sportart
            sql ="Insert into Sportart (sportart,beitrag)";
            sql = sql + " VALUES ('Rugby','120.00');";
            stmt.execute(sql,Statement.RETURN_GENERATED_KEYS); 
            rs = stmt.getGeneratedKeys();
            rs.next();
            sportId = rs.getInt(1);
            
            //neuer Link
            sql ="Insert into Link_Mitglied_Sportart (m_id,sport_id)";
            sql = sql + " VALUES ("+ mId +","+sportId+");";
            stmt.execute(sql);         
            
            
            
            
            //query new Mitglied
            sql ="Select m.*,s.*";
            sql = sql + " from mitglied m, link_mitglied_sportart l, sportart s"; 
            sql = sql + " WHERE m.m_id = l.m_id AND l.Sport_id = s.sport_id";
            sql = sql + " AND m.m_id = " + mId;
            rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                System.out.print(rs.getInt("m.m_id")+ ";");
                System.out.print(rs.getString("Vorname")+ ";");
                System.out.print(rs.getString("Nachname")+ ";");
                System.out.print(rs.getString("s.Sportart")+ ";");
                System.out.print(rs.getString("s.beitrag")+ ";");                
                System.out.println("");
            }
            //end transaction
            con.commit();
        }
        catch (SQLException e)
        {
            try
            {           
                con.rollback();
                System.out.println(e.getMessage());     
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());     
            }        
        }
        finally
        {
            try
            {
                con.close();
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());     
            }
        }

    }
}

