package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SportartDao {
    public static ArrayList<Sportart> getAll() throws SQLException {
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

    public static ArrayList<Sportart> getById(long idFilter) throws SQLException{
        Connection con = DbVerein.getConnection();
        ArrayList<Sportart> results = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Sport_Id, Sportart, Beitrag FROM sportart WHERE Sport_ID=" + idFilter +";");

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

    public static void insert(Sportart sportart){
        Connection con = DbVerein.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO Sportart(Sportart, Beitrag) " +
                                    "VALUES('" +
                                            sportart.getSportart() + "','" +
                                            sportart.getBeitrag() +
                                "');");
        } catch (SQLException e) {
            App.showErrorAlert("Error", "insert Sportarten", e.getLocalizedMessage());
        }
    }

    public static void update(Sportart sportart){
        Connection con = DbVerein.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute("UPDATE Sportart " +
                                "SET Sportart='" + sportart.getSportart() + "', " +
                                "Beitrag='"+sportart.getBeitrag() +
                            "' WHERE Sport_ID='"+sportart.getSportId() + "';");
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            App.showErrorAlert("Error", "insert Sportarten", e.getLocalizedMessage());
        }
    }

    public static void delete(Sportart sportart){
        Connection con = DbVerein.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM Sportart " +
                    "WHERE Sport_ID='"+sportart.getSportId() + "';");
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            App.showErrorAlert("Error", "delete Sportarten", e.getLocalizedMessage());
        }
    }
}
