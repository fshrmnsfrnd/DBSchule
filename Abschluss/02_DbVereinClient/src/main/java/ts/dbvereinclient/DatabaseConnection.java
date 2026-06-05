package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
    private static Connection con = null;

    public static void connect(String host,String port, String user, String pwd) throws SQLException{
        if (con != null) {
            if (!con.isClosed()) {
                con.close();
            }
        }
        String url = "jdbc:mysql://%host:%port/dbVerein";
        url = url.replace("%host", host);
        url = url.replace("%port", port);
        con = DriverManager.getConnection(url,user,pwd);
    }

    public static Connection getConnection(){return con;}

    public static void close() throws SQLException{
        if (con != null){
            con.close(); con = null;
        }
    }
}