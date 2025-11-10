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
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {  
    @FXML
    Label  lblStatusLeft;
    
    //Membervariablen
    private Stage stage;
    

    //Getter und Setter
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //andere Methoden
    public void editSportart()
    {
        try {
            // FXML-Loader erzeugen und Layout auswählen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("sportart.fxml"));
            
            // Root der neuen Scene laden
            Parent root = loader.load();
            
            // Controller aus loader holen und stage übergeben
            SportartController controller = loader.getController();
            controller.setStage(stage);

            //Neue Szene erzeugen und Root-Element übergeben
            Scene scene = new Scene(root);

            //Die Szene der Stage austauschen 
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }         
    }
    
    @FXML
    public void editMitglied()
    {
        try {
            // FXML-Loader erzeugen und Layout auswählen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("mitglied.fxml"));
            
            // Root der neuen Scene laden
            Parent root = loader.load();
            
            // Controller aus loader holen und stage übergeben
            MitgliedController controller = loader.getController();
            controller.setStage(stage);

            //Neue Szene erzeugen und Root-Element übergeben
            Scene scene = new Scene(root);

            //Die Szene der Stage austauschen 
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getLocalizedMessage()));
        }         
    }
    
    public void close()
    {
        stage.close();
    }

}

