package ts.dbvereinclient;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Label lblHeader;

    @FXML
    private Label lblIP;

    @FXML
    private Label lblPort;

    @FXML
    private Label lblPwd;

    @FXML
    private Label lblUser;

    @FXML
    private TextField txtIP;

    @FXML
    private TextField txtPort;

    @FXML
    private PasswordField txtPwd;

    @FXML
    private TextField txtUser;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnConnect;

    public void connect() {
        Alert alert = new Alert(AlertType.INFORMATION);
        if (txtIP.getText().length() == 0) {
            alert.setTitle("IP-Adresse fehlt");
            alert.setHeaderText("Ein Fehler ist aufgetreten.");
            alert.setContentText("Bitte prüfen Sie Ihre Eingabe!");
            alert.showAndWait();
        } else if (txtPort.getText().length() == 0) {
            alert.setTitle("Port-Angabe fehlt");
            alert.setHeaderText("Ein Fehler ist aufgetreten.");
            alert.setContentText("Bitte prüfen Sie Ihre Eingabe!");
            alert.showAndWait();
        } else if (txtUser.getText().length() == 0) {
            alert.setTitle("Benutzername fehlt");
            alert.setHeaderText("Ein Fehler ist aufgetreten.");
            alert.setContentText("Bitte prüfen Sie Ihre Eingabe!");
            alert.showAndWait();
        } /*
        else if (txtPwd.getText().length() == 0)    
        {
            alert.setTitle("Passwort fehlt"); 
            alert.setHeaderText("Ein Fehler ist aufgetreten."); 
            alert.setContentText("Bitte prüfen Sie Ihre Eingabe!"); 
            alert.showAndWait();
        }
         */ else {
            try {
                DbVerein.connect(txtIP.getText(), txtPort.getText(), txtUser.getText(), txtPwd.getText());
                close();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

    }

    @FXML
    public void close() {
        // get a handle to the stage
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
