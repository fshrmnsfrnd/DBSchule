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

public class MitgliedController {
    
    Mitglied selected;
    
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
    
    //Membervariablen
    private Stage stage;

    //Getter und Setter
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void newMitglied(){
        initMitglied();
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
    public void loadMitglied(){
        
        
        //Map Columns to Objekt-Properties
        TableColumn col1 = tblMitglieder.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory<>("mitgliedId"));        
        
        TableColumn col2 = tblMitglieder.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        
        TableColumn col3 = tblMitglieder.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        
        //Löschen
        tblMitglieder.getItems().clear();
        
        try{
            ArrayList<Mitglied> mitglieder = MitgliedDao.getAll();
            
            for (Mitglied o:mitglieder)
            {
                tblMitglieder.getItems().add(o);  
            }
            lblStatusLeft.setText("Mitglieder: " + mitglieder.size());
                       
        }catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehlermeldung");
            alert.setHeaderText("Mitglieder laden");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
        }

    }
    
    @FXML
    public void insertMitglied()
    {
        Mitglied o = getMitgliedFromInput();
        
        try{
            MitgliedDao.insert(o);
            loadMitglied();
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
    @FXML
    public void updateMitglied()
    {
        //create object with current changes
        Mitglied upd = getMitgliedFromInput();
        
        try{
            MitgliedDao.update(selected, upd);
            loadMitglied();
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehlermeldung");
            alert.setHeaderText("Mitglied speichern");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait(); 
        }
    } 
    @FXML
    public void deleteMitglied()
    {
        
        try{
            MitgliedDao.delete(selected);
            initMitglied();
            loadMitglied();  
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
    public void saveMitglied(){
        if (Long.valueOf(txtMId.getText()) == 0)
        {
            insertMitglied();
        }
        else
        {
            updateMitglied();
        }
        loadMitglied();
    }
    
    @FXML
    public void selectItem(){
        selected = tblMitglieder.getSelectionModel().getSelectedItem();
        
        txtMId.setText(Long.toString(selected.getMitgliedId()));
        txtVorname.setText(selected.getVorname());
        txtNachname.setText(selected.getNachname());
        txtGeburtsdatum.setText(selected.getGeburtsdatum());
        txtGeschlecht.setText(selected.getGeschlecht());
        txtStrasse.setText(selected.getStrasse());
        txtOrtId.setText(Long.toString(selected.getOrtId()));
        txtTelefon.setText(selected.getTelefonnr());
        txtEintrittsdatum.setText(selected.getEintrittsdatum());
    }
    
    @FXML
    private void initialize()
    {   
        //Defaults
        initMitglied();
        
        //Add Listener to detect selection-changes
        tblMitglieder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mitglied>(){
                @Override
                public void changed(ObservableValue<? extends Mitglied> ov, Mitglied t, Mitglied t1) {
                    selectItem();
                }    
            });
        //Daten laden
        loadMitglied();  
    }
    
    private void initMitglied(){
        
        selected = null;
        txtMId.setText("0");
        txtVorname.setText("");
        txtNachname.setText("");
        txtGeburtsdatum.setText("");
        txtGeschlecht.setText("");
        txtStrasse.setText("");
        txtOrtId.setText("0");
        txtTelefon.setText("");
        txtEintrittsdatum.setText("");       
    }
    
    private Mitglied getMitgliedFromInput(){
        
        Mitglied o;
        
        long mitgliedId = Long.valueOf(txtMId.getText());
        String vorname = txtVorname.getText();
        String nachname = txtNachname.getText();
        String geburtsdatum = txtGeburtsdatum.getText();
        String geschlecht = txtGeschlecht.getText();
        String strasse = txtStrasse.getText();
        long ort_id = Long.valueOf(txtOrtId.getText());
        String telefon = txtTelefon.getText();
        String eintrittsdatum = txtEintrittsdatum.getText();
        
        
        o = new Mitglied(mitgliedId,vorname,nachname,geburtsdatum,
                         geschlecht,strasse,ort_id,telefon,eintrittsdatum);
        return o;
    }

}
