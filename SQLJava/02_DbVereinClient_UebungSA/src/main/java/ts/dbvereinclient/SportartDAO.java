/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ts.dbvereinclient;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Nico
 */
public class SportartDAO {

    public static ArrayList<Sportart> getAll() {
        ArrayList sportarten = new ArrayList<Sportart>();
        String sql = "SELECT * FROM sportart";

        try ( Connection conn = DatabaseConnection.getConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int sportid = rs.getInt("Sport_Id");
                String name = rs.getString("Sportart");
                Double beitrag = rs.getDouble("Beitrag");
                sportarten.add(new Sportart(sportid, beitrag, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sportarten;
    }

    public static Sportart getById(int id) {
        Sportart sportart;
        String sql = "SELECT * FROM sportart WHERE Sport_Id = ?";

        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(id));
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            sportart = new Sportart(rs.getInt("Sport_Id"), rs.getDouble("Beitrag"), rs.getString("Name"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return sportart;
    }

    public static Sportart insert(Sportart object) {
        String sql = "INSERT INTO sportart (Sportart, Betrag) VALUES (?, ?)";

        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, object.getSportart());
            pstmt.setString(2, String.valueOf(object.getBeitrag()));
            int rows = pstmt.executeUpdate();
            object.setSportId(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void update(Sportart alt, Sportart neu) {
        String sql = "UPDATE sportart SET Sportart = ?, Beitrag = ? WHERE Sport_Id = ?";

        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, neu.getSportart());
            pstmt.setString(2, String.valueOf(neu.getBeitrag()));
            pstmt.setString(3, String.valueOf(alt.getSportId()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Sportart object) {
        String sql = "DELETE FROM sportart WHERE Sport_Id = ?";

        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(object.getSportId()));
            int rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
