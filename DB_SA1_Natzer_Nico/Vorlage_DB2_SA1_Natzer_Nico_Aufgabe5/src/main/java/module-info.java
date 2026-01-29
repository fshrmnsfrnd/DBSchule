module ts.blogic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens ts.blogic to javafx.fxml;
    exports ts.blogic;
}
