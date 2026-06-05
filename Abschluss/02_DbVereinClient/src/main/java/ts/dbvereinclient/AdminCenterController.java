package ts.dbvereinclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;

public class AdminCenterController {
    private Stage stage;

    @FXML
    ComboBox cboStatements;
    @FXML
    TextArea txtSQL;
    @FXML
    TableView  tblResults;

    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void selectSqlStatement(ActionEvent actionEvent) {
    }

    @FXML
    public void close(ActionEvent actionEvent) {
    }

    @FXML
    public void executeSQL() {
        try {
            Statement stmt = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(txtSQL.getText());
            showRS(rs);
        }catch (SQLException e){
            App.showAlert("Error", "ExcecuteSQL", e.getMessage());
        }
    }

    public void showRS(ResultSet rs){
        try {
            tblResults.getItems().clear();
            tblResults.getColumns().clear();

            //Metadaten auswerten
            int colCount = rs.getMetaData().getColumnCount();

            //Create Result-Columns
            for (int i = 1; i <= colCount; i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new MapValueFactory<>("col" + i));
                tblResults.getColumns().add(col);
            }

            //Loop Data and fill Data-Items
            ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                String value = "";

                for (int i = 1; i <= colCount; i++) {

                    value = "";

                    switch (rs.getMetaData().getColumnType(i)) {
                        case Types.BOOLEAN:
                            value = String.valueOf(rs.getBoolean(i));
                            break;
                        case Types.DATE:
                            value = String.valueOf(rs.getDate(i));
                            break;
                        case Types.REAL:
                        case Types.DECIMAL:
                        case Types.NUMERIC:
                        case Types.DOUBLE:
                            value = String.valueOf(rs.getDouble(i));
                            break;
                        case Types.FLOAT:
                            value = String.valueOf(rs.getFloat(i));
                            break;
                        case Types.TINYINT:
                        case Types.SMALLINT:
                        case Types.INTEGER:
                            value = String.valueOf(rs.getInt(i));
                            break;
                        case Types.TIMESTAMP:
                            value = String.valueOf(rs.getTimestamp(i));
                            break;
                        case Types.LONGVARCHAR:
                        case Types.VARCHAR:
                            value = rs.getString(i);
                            break;
                    }
                    item.put("col" + i, value);
                }
                items.add(item);
            }
            tblResults.getItems().addAll(items);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}