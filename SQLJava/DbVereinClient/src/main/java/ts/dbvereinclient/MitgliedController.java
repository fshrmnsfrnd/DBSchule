package ts.dbvereinclient;

import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MitgliedController {
    @FXML
    Label  lblStatusLeft;

    //Membervariablen
    private Stage stage;
    @FXML
    private MenuItem mnuClose;
    @FXML
    private MenuItem mnuShow;
    @FXML
    private MenuItem mnuSave;
    @FXML
    private MenuItem mnuNew;
    @FXML
    private VBox rootVBox;
    @FXML
    private Font x1;
    @FXML
    private Color x2;
    @FXML
    private Font x3;
    @FXML
    private Color x4;
    @FXML
    private Label lblStatusRight;
    @FXML
    private TextField txtEintrittsdatum;
    @FXML
    private TextField txtGeschlecht;
    @FXML
    private TextField txtTelefon;
    @FXML
    private TableView tblMitglieder;
    @FXML
    private TextField txtOrtId;
    @FXML
    private TextField txtStrasse;
    @FXML
    private TextField txtVorname;
    @FXML
    private TextField txtGeburtsdatum;
    @FXML
    private MenuItem mnuDelete;
    @FXML
    private TextField txtNachname;
    @FXML
    private TextField txtMId;

    //Getter und Setter
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void close()
    {
        try
        {
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

        }
        catch (IOException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    public void loadSportart(){
        //Map Columns to Objekt-Properties
        TableColumn col1 = (TableColumn) tblMitglieder.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory<>("mitgliedId"));
        TableColumn col2 = (TableColumn) tblMitglieder.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory<>("Vorname"));
        TableColumn col3 = (TableColumn) tblMitglieder.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory<>("Nachname"));

        tblMitglieder.getItems().clear();
        ArrayList<Mitglied> mitglieder = null;

        try {
            if(!txtSportId.getText().isEmpty()){
                mitglieder = SportartDao.getById(Integer.parseInt(txtSportId.getText()));
            }else {
                mitglieder = MitgliedDao.getAll();
            }
        } catch (SQLException e) {
            App.showErrorAlert("Error", "load Sportarten", e.getLocalizedMessage());
        }

        for (Mitglied o: mitglieder)
        {
            tblMitglieder.getItems().add(o);
        }
        lblStatusLeft.setText("Sportarten: " + mitglieder.size());

    }

    public void insertSportart(){
        Sportart sportart = new Sportart(-1, txtMitglied.getText(), Float.parseFloat(txtBeitrag.getText()));

        try {
            SportartDao.insert(sportart);
        } catch (Exception e) {
            App.showErrorAlert("Error", "insert Sportarten", e.getLocalizedMessage());
        }
        loadSportart();
    }

    public void updateSportart(){
        Sportart sportart = new Sportart(Long.parseLong(txtMitgliedId.getText()), txtMitglied.getText(), Float.parseFloat(txtBeitrag.getText()));

        try {
            SportartDao.update(sportart);
        } catch (Exception e) {
            App.showErrorAlert("Error", "insert Sportarten", e.getLocalizedMessage());
        }
        loadSportart();
    }

    @FXML
    public void deleteSportart(){
        Sportart sportart = new Sportart(Long.parseLong(txtSportId.getText()), txtSportart.getText(), Float.parseFloat(txtBeitrag.getText()));

        try {
            SportartDao.delete(sportart);
        } catch (Exception e) {
            App.showErrorAlert("Error", "insert Sportarten", e.getLocalizedMessage());
        }
    }

    @FXML
    public void selectItem(){
        txtSportId.setText(String.valueOf(tblSportarten.getSelectionModel().selectedItemProperty().getValue().getSportId()));
        txtSportart.setText(String.valueOf(tblSportarten.getSelectionModel().selectedItemProperty().getValue().getSportart()));
        txtBeitrag.setText(String.valueOf(tblSportarten.getSelectionModel().selectedItemProperty().getValue().getBeitrag()));
    }

    @FXML
    public void saveMitglied(ActionEvent actionEvent) {
    }

    @FXML
    public void loadMitglied(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteMitglied(ActionEvent actionEvent) {
    }

    @FXML
    public void insertMitglied(ActionEvent actionEvent) {
    }
}