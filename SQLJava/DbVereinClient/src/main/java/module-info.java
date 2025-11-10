module ts.dbvereinclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;

    opens ts.dbvereinclient to javafx.fxml;
    exports ts.dbvereinclient;
    
}
