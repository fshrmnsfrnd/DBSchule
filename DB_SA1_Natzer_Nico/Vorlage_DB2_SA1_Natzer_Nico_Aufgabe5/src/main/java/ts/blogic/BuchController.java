/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ts.blogic;

/**
 *
 * @author ml
 */
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuchController {

    Buch selected;

    @FXML
    private TableView<Buch> tblBuecher;

    @FXML
    private TextField txtSuchbegriff;

    @FXML
    private TextField txtBuchId;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtTitel;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextArea txtSchlagworte;

    @FXML
    private Label lblStatusLeft;

    public BuchController() {
        try {
            DbConnect.connect("127.0.0.1", "3306", "root", "");
        } catch (SQLException e) {
            String message = "Verbindung zur Datenbank konnte nicht aufgebaut werden. " + e.getLocalizedMessage();
            System.err.println(message);
        }
    }

    public void neu() {
        initializeBuch();
    }

    public void speichern() {
        int buchId = Integer.valueOf(txtBuchId.getText());
        if(buchId < 1){
            buchId = 0;
        }
        Buch buch = new Buch(buchId, txtISBN.getText(), txtAutor.getText(), txtTitel.getText(), txtSchlagworte.getText());

        //Ergänzen Sie Ihren Code hier
        try{
            BuchDao.speichern(buch);
        }catch(Exception e){
            String message = "Buch speichern. " + e.getLocalizedMessage();
            lblStatusLeft.setText(message);
            System.err.println(message);
        }
        
        suchen();
    }

    public void loeschen() {

        try {
            BuchDao.loeschen(selected);
            initializeBuch();
            suchen();

        } catch (Exception e) {
            String message = "Buch löschen. " + e.getLocalizedMessage();
            lblStatusLeft.setText(message);
            System.err.println(message);
        }

    }

    public void suchen() {
        //Map Columns to Objekt-Properties
        TableColumn col1 = tblBuecher.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn col2 = tblBuecher.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn col3 = tblBuecher.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory<>("titel"));

        //Ergänzen Sie Ihren Code hier
        tblBuecher.getItems().clear();

        try {
            ArrayList<Buch> buecher = BuchDao.suchen(txtSuchbegriff.getText());
            for (Buch o : buecher) {
                tblBuecher.getItems().add(o);
            }
            lblStatusLeft.setText("Buecher: " + buecher.size());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    @FXML
    private void selectItem() {

        if (tblBuecher.getSelectionModel().getSelectedItem() != null) {
            selected = tblBuecher.getSelectionModel().getSelectedItem();

            txtBuchId.setText(Integer.toString(selected.getId()));
            txtISBN.setText(selected.getIsbn());
            txtTitel.setText(selected.getTitel());
            txtAutor.setText(selected.getAutor());
            txtSchlagworte.setText(selected.getSchlagworte());
        }

    }

    private void initializeBuch() {
        txtBuchId.setText("0");
        txtISBN.setText("");
        txtTitel.setText("");
        txtAutor.setText("");
        txtSchlagworte.setText("");
    }
}
