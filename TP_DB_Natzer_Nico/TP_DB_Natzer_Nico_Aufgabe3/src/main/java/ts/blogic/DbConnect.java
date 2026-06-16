/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.blogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author meinhard.lingo
 */
public class DbConnect {
    private static Connection con = null;

    public static Connection getConnection(String host,String port, String user, String pwd) throws SQLException{
        if (con == null) {
            String url = "jdbc:mysql://%host:%port/dbtp26";
            url = url.replace("%host", host);
            url = url.replace("%port", port);
        
            con = DriverManager.getConnection(url,user,pwd);
        
             return con;
        }
        else{
            return con;
        }
    }

    
}
