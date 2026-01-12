/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.dbvereinclient;

/**
 *
 * @author ml
 */
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SportartController {

    @FXML
    Label lblStatusLeft;

    //Membervariablen
    private Stage stage;

    //Getter und Setter
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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

}
