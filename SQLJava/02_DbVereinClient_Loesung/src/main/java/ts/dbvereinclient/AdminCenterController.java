/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.dbvereinclient;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author meinhard.lingo
 */
public class AdminCenterController {
    //Membervariablen

    private Stage stage;

    @FXML
    ComboBox cboStatements;

    @FXML
    TextArea txtSQL;

    @FXML
    TableView tblResults;

    //Getter und Setter
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void selectSqlStatement() {
        Object selected = cboStatements.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtSQL.setText(selected.toString());
        }

    }

    @FXML
    public void executeSQL() {
        if (DbVerein.isClosed()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Keine Datenbankverbindung");
            alert.setHeaderText("Ein Fehler ist aufgetreten.");
            alert.setContentText("Bitte verbinden Sie sich zur Datenbank.");
            alert.showAndWait();
        } else {

            try {
                tblResults.getItems().clear();
                tblResults.getColumns().clear();

                //Abfrage starten
                ResultSet rs = AdminCenterDao.executeQuery(txtSQL.getText());

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

    @FXML
    public void close() {
        try {
            // FXML-Loader erzeugen und Layout auswählen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("main.fxml"));
            // Root der neuen Scene speichern
            Parent root = loader.load();

            //aktuellen Controller von main.xml holen und stage übergeben
            MainController controller = loader.getController();
            controller.setStage(stage);

            //Neue Szene erzeugen und Root-Element übergeben
            Scene scene = new Scene(root);

            //Die Szene der Stage austauschen und 
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void initialize() {
        //Supported SQL Statements
        loadSQLStatements();

        //Add Listener to detect selection-changes
        cboStatements.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> ov, Object t, Object t1) {
                selectSqlStatement();
            }

        });
    }

    @FXML
    public void loadSQLStatements() {

        cboStatements.getItems().addAll(
                "SHOW COLLATION",
                "SHOW DATABASES",
                "SHOW ERRORS",
                "SHOW GRANTS",
                "SHOW TABLES",
                "SHOW TRIGGERS",
                "SHOW PRIVILEGES",
                "SHOW VARIABLES",
                "SHOW WARNINGS"
        );

    }
}
