package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author meinhard.lingo
 */
public class DbVerein {
    private static Connection con = null;

    public static void connect(String host,String port, String user, String pwd) throws SQLException{
        if (con != null) {
            if (!con.isClosed())
            {
                con.close();
            }
        }
        String url = "jdbc:mysql://%host:%port/dbverein";
        url = url.replace("%host", host);
        url = url.replace("%port", port);
        con = DriverManager.getConnection(url,user,pwd);

        try{
            Statement stmt = con.createStatement();
            stmt.execute("SELECT Sport_Id, Sportart, Beitrag FROM sportart;");
        } catch (SQLException e) {
            App.showErrorAlert("Error", "connectToDB", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection(){
        return con;
    }

    public static void closeConnection() throws SQLException{
        if (con != null) {
            if (!con.isClosed())
            {
                con.close();
            }
        }
    }

}