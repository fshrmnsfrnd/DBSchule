/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ts.blogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ml
 */
public class BuchDao {

    public static ArrayList<Buch> suchen(String suchbegriff) throws SQLException {

        ArrayList<Buch> results = new ArrayList<>();
        Connection con = DbConnect.getConnection();
        PreparedStatement ps;

        //Ergänzen Sie Ihren Code hier
        String sql;
        ResultSet rs;

        if (suchbegriff.isEmpty()) {
            sql = "SELECT * FROM buch";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } else if (suchbegriff.startsWith("%") && suchbegriff.endsWith("%")) {
            String fSuchbegiff = suchbegriff.replaceAll("%", "");
            sql = "SELECT * FROM buch WHERE schlagworte like ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, fSuchbegiff);
            rs = ps.executeQuery();
        } else if (suchbegriff.endsWith("%")) {
            String fSuchbegiff = suchbegriff.replaceAll("%", "");
            fSuchbegiff = fSuchbegiff + "*";
            sql = "SELECT * FROM buch WHERE schlagworte like ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, fSuchbegiff);
            rs = ps.executeQuery();
        }
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                results.add(new Buch(rs.getInt("id"), rs.getString("isbn"), rs.getString("titel"), rs.getString("autor"), rs.getString("schlagworte")));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return results;
    }

    public static Buch speichern(Buch o) throws SQLException {
        Connection con = DbConnect.getConnection();

        //Ergänzen Sie Ihren Code hier
        if (o.getId() == 0) {
            String sql = "INSERT INTO buch (isbn, titel, autor, schlagworte) VALUES (?, ?, ?, ?)";

            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, o.getIsbn());
                ps.setString(2, o.getTitel());
                ps.setString(3, o.getAutor());
                ps.setString(4, o.getSchlagworte());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "UPDATE buch SET isbn = ?, titel = ?, autor = ?, schlagworte = ? WHERE  id = ?";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, o.getIsbn());
                ps.setString(2, o.getTitel());
                ps.setString(3, o.getAutor());
                ps.setString(4, o.getSchlagworte());
                ps.setInt(5, o.getId());
                int rows = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return o;

    }

    public static void loeschen(Buch o) throws SQLException {
        Connection con = DbConnect.getConnection();

        //PreparedStatement-Objekt mit Hilfe des Connection-Objektes erzeugen.
        String sql = "DELETE FROM buch WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, o.getId());

        ps.execute();

    }
}
