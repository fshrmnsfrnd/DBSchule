/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author meinhard.lingo
 */
public class AdminCenterDao {
    public static ResultSet executeQuery(String sql) throws SQLException
    {
        Connection con = null;

        con = DbVerein.getConnection();

        //Statement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        return rs;
    }
    
}
