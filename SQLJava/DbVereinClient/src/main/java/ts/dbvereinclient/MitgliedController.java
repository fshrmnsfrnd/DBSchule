package ts.dbvereinclient;

import javafx.scene.control.Label;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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

    public void insertMitglied(){

    }

    public void loadMitglied(){

    }

    public void saveMitglied(){

    }

    public void deleteMitglied(){

    }

    public void selectItem(){

    }
}