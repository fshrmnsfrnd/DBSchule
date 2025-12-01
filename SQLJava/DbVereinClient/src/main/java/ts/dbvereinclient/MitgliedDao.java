package ts.dbvereinclient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MitgliedDao {
    public static ArrayList<Mitglied> getAll() throws SQLException {
        Connection con = DbVerein.getConnection();
        ArrayList<Mitglied> results = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT M_Id, Vorname, Nachname, Geburtsdatum, Geschlecht, Strasse, Tel, Eintrittsdatum, PLZ, Ort, mitglied.Ort_Id FROM mitglied, ort WHERE mitglied.Ort_Id = ort.Ort_Id ;");

        //ORM: object-relational-mapping
        while (rs.next())
        {
            Ort ort = new Ort(
                    rs.getLong("mitglied.Ort_Id"),
                    rs.getString("PLZ"),
                    rs.getString("Ort")
            );
            //Mitglied-Objekt erzeugen
            results.add(new Mitglied(
                    rs.getLong("M_Id"),
                    rs.getString("Vorname"),
                    rs.getString("Nachname"),
                    rs.getDate("Geburtsdatum"),
                    rs.getString("Geschlecht").charAt(0),
                    rs.getString("Strasse"),
                    ort,
                    rs.getString("Tel"),
                    rs.getDate("Eintrittsdatum")
                    ));
        }
        return results;
    }

    public static ArrayList<Mitglied> getById(long idFilter) throws SQLException{
        Connection con = DbVerein.getConnection();
        ArrayList<Mitglied> results = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT M_Id, Vorname, Nachname, Geburtsdatum, Geschlecht, Strasse, Tel, Eintrittsdatum, PLZ, Ort, Ort_Id FROM mitglied, ort WHERE M_Id=" + idFilter +";");

        //ORM: object-relational-mapping
        while (rs.next())
        {
            Ort ort = new Ort(rs.getLong("Ort_Id"), rs.getString("PLZ"), rs.getString("Ort"));
            //Mitglied-Objekt erzeugen
            results.add(new Mitglied(
                    rs.getLong("M_Id"),
                    rs.getString("Vorname"),
                    rs.getString("Nachname"),
                    rs.getDate("Geburtsdatum"),
                    rs.getString("Geschlecht").charAt(0),
                    rs.getString("Strasse"),
                    ort,
                    rs.getString("Tel"),
                    rs.getDate("Eintrittsdatum")
            ));
        }
        return results;
    }

    public static void insert(Mitglied mitglied){
        Connection con = DbVerein.getConnection();

        try {
            if(OrtDao.getByOrtPLZ(mitglied.getOrt().getOrt(), mitglied.getOrt().getPlz()) == null){
                OrtDao.insert(mitglied.getOrt());
                mitglied.ort = OrtDao.getByOrtPLZ(mitglied.getOrt().getOrt(), mitglied.getOrt().getPlz());
            }
            
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO Mitglied(Vorname, Nachname, Geburtsdatum, Geschlecht, Strasse, Tel, Eintrittsdatum, Ort_Id) " +
                    "VALUES('" +
                    mitglied.getVorname() + "','" +
                    mitglied.getNachname() + "','" +
                    mitglied.getGeburtsdatum() + "','" +
                    mitglied.getGeschlecht() + "','" +
                    mitglied.getStrasse() + "','" +
                    mitglied.getTelefonnummer() + "','" +
                    mitglied.getEintrittsdatum() + "','" +
                    mitglied.getOrt().getOrt_id() +
                    "');");
        } catch (SQLException e) {
            App.showErrorAlert("Error", "insert Mitglied", e.getLocalizedMessage());
        }
    }

    public static void update(Mitglied mitglied){
        Connection con = DbVerein.getConnection();

        try {
            Statement stmt = con.createStatement();

            stmt.execute("UPDATE mitglied " +
                    "SET Vorname='"+ mitglied.getVorname() + "', " +
                    "Nachname='"+ mitglied.getNachname() + "', " +
                    "Geburtsdatum='"+ mitglied.getGeburtsdatum() + "', " +
                    "Geschlecht='"+ mitglied.getGeschlecht() + "', " +
                    "Strasse='"+ mitglied.getStrasse() + "', " +
                    "Tel='"+ mitglied.getTelefonnummer() + "', " +
                    "Eintrittsdatum='"+ mitglied.getEintrittsdatum() + "' " +
                    "Ort_Id='" + mitglied.getOrt().getOrt_id() + "' " +
                    "' WHERE M_Id='"+mitglied.getId() + "';");
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            App.showErrorAlert("Error", "insert Mitglied", e.getLocalizedMessage());
        }
    }

    public static void delete(Mitglied mitglied){
        Connection con = DbVerein.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM mitglied " +
                    "WHERE M_Id='"+ mitglied.getId() + "';");
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            App.showErrorAlert("Error", "delete Mitglied", e.getLocalizedMessage());
        }
    }
}
