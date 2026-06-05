package ts.dbvereinclient;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class MitgliedController {
    @FXML
    TableView<Mitglied> tblMitglieder ;

    @FXML
    private TextField txtMId;

    @FXML
    private TextField txtVorname;

    @FXML
    private TextField txtNachname;

    @FXML
    private TextField txtGeburtsdatum;

    @FXML
    private TextField txtGeschlecht;

    @FXML
    private TextField txtStrasse;

    @FXML
    private TextField txtOrtId;

    @FXML
    private TextField txtTelefon;

    @FXML
    private TextField txtEintrittsdatum;

    @FXML
    private Label lblStatusLeft;

    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
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
    public void selectItem(Event event) {
    }

    @FXML
    public void newMitglied(ActionEvent actionEvent) {
    }

    @FXML
    public void close() {
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


}