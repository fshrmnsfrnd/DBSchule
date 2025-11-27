package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrtDao {
    public static ArrayList<Ort> getAll() throws SQLException {
        Connection con = DbVerein.getConnection();
        ArrayList<Ort> results = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT PLZ, Ort, Ort_Id FROM ort;");

        //ORM: object-relational-mapping
        while (rs.next())
        {
            //Ort-Objekt erzeugen
            results.add(new Ort(
                    rs.getLong("Ort_Id"),
                    rs.getString("PLZ"),
                    rs.getString("Ort")
            ));
        }
        return results;
    }

    public static ArrayList<Ort> getById(long idFilter) throws SQLException{
        Connection con = DbVerein.getConnection();
        ArrayList<Ort> results = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT PLZ, Ort, Ort_Id FROM ort WHERE Ort_Id=" + idFilter +";");

        //ORM: object-relational-mapping
        while (rs.next())
        {
            //Ort-Objekt erzeugen
            results.add(new Ort(
                    rs.getLong("Ort_Id"),
                    rs.getString("PLZ"),
                    rs.getString("Ort")
            ));
        }
        return results;
    }

    public static Ort getByOrtPLZ(String ort, String PLZ) throws SQLException{
        Connection con = DbVerein.getConnection();
        ArrayList<Ort> results = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT PLZ, Ort, Ort_Id FROM ort WHERE Ort=" + ort + "AND PLZ=" + PLZ + ";");

        //ORM: object-relational-mapping
        if(rs.next()){
            return new Ort(
                    rs.getLong("Ort_Id"),
                    rs.getString("PLZ"),
                    rs.getString("Ort")
            );
        }else{
            return null;
        }
    }

    public static void insert(Ort ort){
        Connection con = DbVerein.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO Ort(PLZ, Ort) " +
                    "VALUES('" +
                    ort.getPlz() + "', '" +
                    ort.getOrt() +
                    "');");
        } catch (SQLException e) {
            App.showErrorAlert("Error", "insert Ort", e.getLocalizedMessage());
        }
    }

    public static void update(Ort ort){
        Connection con = DbVerein.getConnection();

        try {
            Statement stmt = con.createStatement();

            stmt.execute("UPDATE ort " +
                    "SET Ort_Id='"+ ort.getOrt_id() + "', " +
                    "PLZ='"+ ort.getPlz() + "', " +
                    "Ort='"+ ort.getOrt() + "', " +
                    "' WHERE Ort_Id='"+ ort.getOrt_id() + "';");
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            App.showErrorAlert("Error", "insert Ort", e.getLocalizedMessage());
        }
    }

    public static void delete(Ort ort){
        Connection con = DbVerein.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM ort " +
                    "WHERE Ort_Id='"+ ort.getOrt_id() + "';");
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            App.showErrorAlert("Error", "delete Ort", e.getLocalizedMessage());
        }
    }
}
