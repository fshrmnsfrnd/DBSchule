package ts.dbvereinclient;

import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    private TableView<Sportart> tblSportarten;
    @FXML
    private TextField txtSportId;
    @FXML
    private Button ausführen;
    @FXML
    private TextField txtBeitrag;
    @FXML
    private TextField txtSportart;
    @FXML
    private MenuItem mnuDelete;

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("main.fxml"));
            Parent root = loader.load();

            MainController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);

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
        TableColumn col1 = tblSportarten.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory<>("sportId"));
        TableColumn col2 = tblSportarten.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory<>("Sportart"));
        TableColumn col3 = tblSportarten.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory<>("beitrag"));

        tblSportarten.getItems().clear();
        ArrayList<Sportart> sportarten = null;

        try {
            if(!txtSportId.getText().isEmpty()){
                sportarten = SportartDao.getById(Integer.parseInt(txtSportId.getText()));
            }else {
                sportarten = SportartDao.getAll();
            }
        } catch (SQLException e) {
            App.showErrorAlert("Error", "load Sportarten", e.getLocalizedMessage());
        }

        for (Sportart o:sportarten){
            tblSportarten.getItems().add(o);
        }
        lblStatusLeft.setText("Sportarten: " + sportarten.size());

    }

    public void insertSportart(){
        Sportart sportart = new Sportart(-1, txtSportart.getText(), Float.parseFloat(txtBeitrag.getText()));

        try {
            SportartDao.insert(sportart);
        } catch (Exception e) {
            App.showErrorAlert("Error", "insert Sportarten", e.getLocalizedMessage());
        }
        loadSportart();
    }

    public void updateSportart(){
        Sportart sportart = new Sportart(Long.parseLong(txtSportId.getText()), txtSportart.getText(), Float.parseFloat(txtBeitrag.getText()));

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
}
