package ts.dbvereinclient;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

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
    private TableView<Mitglied> tblMitglieder;
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
    @FXML
    private TextField txtOrt;
    @FXML
    private TextField txtPlz;

    //Getter und Setter
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void close(){
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
    public void loadMitglied(){
        //Map Columns to Objekt-Properties
        TableColumn col1 = (TableColumn) tblMitglieder.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn col2 = (TableColumn) tblMitglieder.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory<>("Vorname"));
        TableColumn col3 = (TableColumn) tblMitglieder.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory<>("Nachname"));

        tblMitglieder.getItems().clear();
        ArrayList<Mitglied> mitglieder = null;

        try {
            if(!txtMId.getText().isEmpty()){
                mitglieder = MitgliedDao.getById(Integer.parseInt(txtMId.getText()));
                System.out.println("get mitglied by id");
            }else {
                System.out.println("get all mitglier");
                mitglieder = MitgliedDao.getAll();
            }
        } catch (SQLException e) {
            App.showErrorAlert("Error", "load Mitglieder", e.getLocalizedMessage());
        }

        for (Mitglied o: mitglieder){
            tblMitglieder.getItems().add(o);
        }
        lblStatusLeft.setText("Mitglieder: " + mitglieder.size());

    }

    public void insertMitglied(){
        Ort ort = new Ort(
        -1,
                txtPlz.getText(),
                txtOrt.getText()
        );

        Mitglied mitglied = new Mitglied(
                -1,
                txtVorname.getText(),
                txtNachname.getText(),
                Date.from(Instant.parse(txtGeburtsdatum.getText())),
                txtGeschlecht.getText().charAt(0),
                txtStrasse.getText(),
                ort,
                txtTelefon.getText(),
                new Date()
        );

        try {
            MitgliedDao.insert(mitglied);
        } catch (Exception e) {
            App.showErrorAlert("Error", "insert Mitglied", e.getLocalizedMessage());
        }
        loadMitglied();
    }

    public void updateMitglied(){
        Ort ort = new Ort(
                -1,
                txtPlz.getText(),
                txtOrt.getText()
        );
        Mitglied mitglied = new Mitglied(
                Long.parseLong(txtMId.getText()),
                txtVorname.getText(),
                txtNachname.getText(),
                Date.from(Instant.parse(txtGeburtsdatum.getText())),
                txtGeschlecht.getText().charAt(0),
                txtStrasse.getText(),
                ort,
                txtTelefon.getText(),
                new Date()
        );

        try {
            MitgliedDao.update(mitglied);
        } catch (Exception e) {
            App.showErrorAlert("Error", "insert Sportarten", e.getLocalizedMessage());
        }
        loadMitglied();
    }

    @FXML
    public void deleteMitglied(){
        Ort ort = new Ort(
                -1,
                txtPlz.getText(),
                txtOrt.getText()
        );

        Mitglied mitglied = new Mitglied(
                Long.parseLong(txtMId.getText()),
                txtVorname.getText(),
                txtNachname.getText(),
                Date.from(Instant.parse(txtGeburtsdatum.getText())),
                txtGeschlecht.getText().charAt(0),
                txtStrasse.getText(),
                ort,
                txtTelefon.getText(),
                new Date()
        );

        try {
            MitgliedDao.delete(mitglied);
        } catch (Exception e) {
            App.showErrorAlert("Error", "delete Mitglied", e.getLocalizedMessage());
        }
    }

    @FXML
    public void selectItem() {
        tblMitglieder.getItems().clear();
        ArrayList<Mitglied> mitglieder = null;

        try {
            if(!txtMId.getText().isEmpty()){
                mitglieder = MitgliedDao.getById(Integer.parseInt(txtMId.getText()));
            }else {
                mitglieder = MitgliedDao.getAll();
            }
        } catch (SQLException e) {
            App.showErrorAlert("Error", "select Mitglied", e.getLocalizedMessage());
        }

        Mitglied mitglied;
        try {
            long mId = tblMitglieder.getSelectionModel().getSelectedItem().id;
            mitglied = MitgliedDao.getById(mId).get(0);
        }catch (Exception e){
            //If nothing fund, emppty mitglied
            mitglied = new Mitglied(0, "", "", new Date(), ' ', "", new Ort(0, "", ""), "", new Date());
        }
        txtMId.setText(String.valueOf(mitglied.id));
        txtVorname.setText(String.valueOf(mitglied.vorname));
        txtNachname.setText(String.valueOf(mitglied.nachname));
        txtGeburtsdatum.setText(String.valueOf(mitglied.geburtsdatum));
        txtGeschlecht.setText(String.valueOf(mitglied.geschlecht));
        txtStrasse.setText(String.valueOf(mitglied.strasse));
        txtOrtId.setText(String.valueOf(mitglied.ort.ort_id));
        txtPlz.setText(String.valueOf(mitglied.ort.plz));
        txtOrt.setText(String.valueOf(mitglied.ort.ort));
        txtTelefon.setText(String.valueOf(mitglied.telefonnummer));
        txtEintrittsdatum.setText(String.valueOf(mitglied.eintrittsdatum));
    }
}