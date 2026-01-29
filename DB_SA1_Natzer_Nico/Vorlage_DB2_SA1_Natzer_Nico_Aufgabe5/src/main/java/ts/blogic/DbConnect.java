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

    public static void connect(String host,String port, String user, String pwd) throws SQLException{
        if (con != null) {
            if (!con.isClosed())
            {
                con.close();
            }
        }
        String url = "jdbc:mysql://%host:%port/dbsa1";
        url = url.replace("%host", host);
        url = url.replace("%port", port);
        con = DriverManager.getConnection(url,user,pwd);  

    }
    public static Connection getConnection(){
        return con;
    }
    public static void close() throws SQLException{
        if (con != null) {
            if (!con.isClosed())
            {
                con.close();
            }
        }  
    }
    public static boolean isClosed() {
        boolean isClosed = true;
        try{
            isClosed = con.isClosed();
        }
        catch(SQLException e){
            //nicht nötig
        }
        return isClosed;

    }
    
}
