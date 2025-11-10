package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SportartDao {
    public static ArrayList<Sportart> getAll() throws SQLException
    {
        Connection con = DbVerein.getConnection();
        ArrayList<Sportart> results = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Sport_Id, Sportart, Beitrag FROM sportart;");

        //ORM: object-relational-mapping
        while (rs.next())
        {
            long id = rs.getInt("sport_id");
            String sportart = rs.getString("Sportart");
            float beitrag = rs.getFloat("beitrag");
            //Sport-Objekt erzeugen
            results.add(new Sportart(id,sportart,beitrag));
        }
        return results;
    }

    public static Sportart getById(long id){
        ResultSet rs;
        Sportart sportart = null;
        try {
            Connection con = DbVerein.getConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT Sport_Id, Sportart, Beitrag FROM sportart WHERE Sportart_id = " + String.valueOf(id);
            rs = statement.executeQuery(sql);
            rs.next();
            sportart = new Sportart(rs.getLong("Sport_Id"), rs.getString("Sportart"), rs.getFloat("Beitrag"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return sportart;
    }

    public static void insert(Sportart o){

    }

    public static void update(Sportart org, Sportart upd){

    }

    public static void delete(Sportart o){

    }
}
