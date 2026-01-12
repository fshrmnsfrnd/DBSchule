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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SportartController {
    
    Sportart selected;
    
    @FXML
    TableView<Sportart> tblSportarten; 
    
    @FXML
    Label  lblStatusLeft;
    
    @FXML
    TextField  txtSportId;
    
    @FXML
    TextField  txtSportart;
    
    @FXML
    TextField  txtBeitrag;    
    
    //Membervariablen
    private Stage stage;

    //Getter und Setter
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void newSportart(){
        initSportart();
    }
            
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
        
        System.out.println("loadSportart");
        
        //Map Columns to Objekt-Properties
        tblSportarten.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("sportId"));        
        
        tblSportarten.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("sportart"));
        
        tblSportarten.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("beitrag"));
        
        //Löschen
        tblSportarten.getItems().clear();
        
        try{
            ArrayList<Sportart> sportarten = SportartDao.getAll();
            
            for (Sportart o:sportarten)
            {
                tblSportarten.getItems().add(o);  
            }
            lblStatusLeft.setText("Sportarten: " + sportarten.size());
                       
        }catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehlermeldung");
            alert.setHeaderText("Sportarten laden");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
        }

    }
    
    @FXML
    public void insertSportart()
    {
        String sportart = txtSportart.getText();
        float beitrag = Float.valueOf(txtBeitrag.getText());
        
        Sportart o = new Sportart(0,sportart,beitrag);
        
        try{
            SportartDao.insert(o);
            loadSportart();
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehlermeldung");
            alert.setHeaderText("Sportart neu anlegen");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait(); 
        }
        
    }
    
    public void updateSportart()
    {
        //create object with current changes
        long sportId = Long.valueOf(txtSportId.getText());
        String sportart = txtSportart.getText();
        float beitrag = Float.valueOf(txtBeitrag.getText());
        
        Sportart upd = new Sportart(sportId,sportart,beitrag);
        
        try{
            SportartDao.update(selected, upd);
            loadSportart();
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehlermeldung");
            alert.setHeaderText("Sportart speichern");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait(); 
        }
        
    } 
    
    public void deleteSportart()
    {
        
        try{
            SportartDao.delete(selected);
            initSportart();
            loadSportart();
           
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehlermeldung");
            alert.setHeaderText("Sportart löschen");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait(); 
        }
        
    }    
    public void saveSportart(){
        if (Long.valueOf(txtSportId.getText()) == 0)
        {
            insertSportart();
        }
        else
        {
            updateSportart();
        }
        loadSportart();
    }
    
    @FXML
    public void selectItem(){
        selected = tblSportarten.getSelectionModel().getSelectedItem();
        
        txtSportId.setText(Long.toString(selected.getSportId()));
        txtSportart.setText(selected.getSportart());
        txtBeitrag.setText(Float.toString(selected.getBeitrag()));
    }
    
    @FXML
    private void initialize()
    {   
        //Defaults
        initSportart();
        
        //Add Listener to detect selection-changes
        tblSportarten.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sportart>(){
                @Override
                public void changed(ObservableValue<? extends Sportart> ov, Sportart t, Sportart t1) {
                    selectItem();
                }    
            });
        //Daten laden
        loadSportart();  
    }
    
    private void initSportart(){
        
        selected = null;
                
        txtSportId.setText("0");
        txtSportart.setText("");
        txtBeitrag.setText("0.00");        
    }

}
