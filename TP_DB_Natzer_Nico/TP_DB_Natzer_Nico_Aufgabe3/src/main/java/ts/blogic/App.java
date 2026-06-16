package ts.blogic;

import java.sql.SQLException;




/**
 * JavaFX App
 */
public class App {

    public static void main(String[] args) {
        try {
            Import.loadData();
        } catch (SQLException ex) {
            System.err.println( ex);
        }

    }

}