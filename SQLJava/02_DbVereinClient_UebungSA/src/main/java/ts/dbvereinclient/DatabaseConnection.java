/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ts.dbvereinclient;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/dbverein"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 
    private static Connection con = null; 

    public static Connection getConnection() throws SQLException { 
        if (con == null){ 
                con = DriverManager.getConnection(URL, USER, PASSWORD); 
        } 
        return con; 
    } 

    public static void close() throws SQLException{ 
        if (con != null){ 
                con.close(); con = null; 
        } 
    } 
}