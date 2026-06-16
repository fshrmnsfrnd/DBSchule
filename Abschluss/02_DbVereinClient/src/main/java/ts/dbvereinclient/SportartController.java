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
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class SportartController {
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
    private Label lblStatusRight;
    @FXML
    private TextField txtSportId;
    @FXML
    private TextField txtBeitrag;
    @FXML
    private TextField txtSportart;
    @FXML
    private TableView tblSportarten;
    @FXML
    private MenuItem mnuDelete;

    @FXML
    public void initialize(){
        loadSportart();
    }

    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void clearCells(){
        txtSportId.clear();
        txtSportart.clear();
        txtBeitrag.clear();
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

        }catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private Sportart getInputAsSportart(){
        if(Objects.equals(txtSportId.getText(), "")){
            return new Sportart(txtSportart.getText(), Float.parseFloat(txtBeitrag.getText()));
        }
        return new Sportart(Integer.parseInt(txtSportId.getText()), txtSportart.getText(), Float.parseFloat(txtBeitrag.getText()));
    }

    @FXML
    public void deleteSportart(ActionEvent actionEvent) {
        try {
            SportartDAO.deleteByID(getInputAsSportart());
        }catch (SQLException e){
            App.showAlert("Error", "Deleting Sportart", e.getMessage());
        }
        this.clearCells();
        this.loadSportart();
    }

    @FXML
    public void selectItem() {
        Sportart selected = (Sportart) tblSportarten.getSelectionModel().getSelectedItem();

        txtSportId.setText(Integer.toString(selected.getSport_Id()));
        txtSportart.setText(selected.getSportart());
        txtBeitrag.setText(Float.toString(selected.getBeitrag()));
    }

    @FXML
    public void loadSportart() {
        TableColumn col1 = (TableColumn) tblSportarten.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory<>("sport_Id"));

        TableColumn col2 = (TableColumn) tblSportarten.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory<>("sportart"));

        TableColumn col3 = (TableColumn) tblSportarten.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory<>("beitrag"));

        tblSportarten.getItems().clear();

        try{
            ArrayList<Sportart> sportarten = SportartDAO.getAll();
            for (Sportart o:sportarten){
                tblSportarten.getItems().add(o);
            }
            lblStatusLeft.setText("Sportarten: " + sportarten.size());
        }catch(SQLException e){
            App.showAlert("Error", "LoadSportart", e.getMessage());
        }
    }

    @FXML
    public void saveSportart() {
        try {
            SportartDAO.update(getInputAsSportart());
        }catch (SQLException e){
            App.showAlert("Error", "Updating Sportart", e.getMessage());
        }
        loadSportart();
    }

    @FXML
    public void newSportart(ActionEvent actionEvent) {
        try {
            SportartDAO.create(getInputAsSportart());
        }catch (SQLException e){
            App.showAlert("Error", "Creating Sportart", e.getMessage());
        }
        loadSportart();
    }
}
