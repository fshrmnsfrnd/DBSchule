package ts.dbvereinclient;

import java.sql.*;
import java.util.ArrayList;

public class SportartDAO {

    public static void create(Sportart sportart) throws SQLException{
        String sql = "INSERT INTO Sportart (Sportart, Beitrag) VALUES (?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, sportart.getSportart());
        pstmt.setFloat(2, sportart.getBeitrag());
        int rows = pstmt.executeUpdate();
    }

    public static void update(Sportart sportart) throws SQLException{
        String sql = "UPDATE Sportart SET Sportart = ?, Beitrag = ? WHERE Sport_Id = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, sportart.getSportart());
        pstmt.setFloat(2, sportart.getBeitrag());
        pstmt.setInt(3, sportart.getSport_Id());
        int rows = pstmt.executeUpdate();
        System.out.println(rows + " Datensätze aktualisiert.");
    }

    public static void deleteByID(Sportart sportart) throws SQLException{
        String sql = "DELETE FROM Sportart WHERE Sport_Id = ?";

        Connection con = DatabaseConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, String.valueOf(sportart.getSport_Id()));
        int rows = pstmt.executeUpdate();
    }

    public static ArrayList<Sportart> getAll() throws SQLException{
        String sql = "SELECT * FROM Sportart";
        ArrayList<Sportart> sportarten = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            Sportart nextsport = new Sportart(rs.getInt("Sport_Id"), rs.getString("Sportart"), rs.getInt("Beitrag"));
            sportarten.add(nextsport);
        }
        return sportarten;
    }
}
