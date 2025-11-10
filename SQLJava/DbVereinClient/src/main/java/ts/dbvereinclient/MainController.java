/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.dbvereinclient;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    //Membervariablen
    private Stage stage;
    @FXML
    private Label lblStateRight;
    @FXML
    private MenuItem mnuSports;
    @FXML
    private MenuItem mnuQuit;
    @FXML
    private MenuItem mnuConnect;
    @FXML
    private MenuItem mnuMembers1;
    @FXML
    private Label lblStateLeft;
    @FXML
    private MenuItem mnuMembers;
    @FXML
    private VBox rootVBox;
    @FXML
    private Font x1;
    @FXML
    private Color x2;
    @FXML
    private Font x3;
    @FXML
    private MenuItem mnuDisConnect;
    @FXML
    private Color x4;


    //Getter und Setter
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //andere Methoden
    @FXML
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
            System.err.printf("Error: %s%n", e.getMessage());
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
            System.err.printf("Error: %s%n", e.getLocalizedMessage());
        }         
    }

    @FXML
    public void startLogin(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(LoginController.class.getResource("login.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Mit Datenbank verbinden");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(stage.getOwner());
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }
    
    @FXML
    public void closeConnection() {
        try {
            DbVerein.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void close(){
        stage.close();
    }
}

