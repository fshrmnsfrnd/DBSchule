module ts.dbvereinclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens ts.dbvereinclient to javafx.fxml;
    exports ts.dbvereinclient;
    
}
