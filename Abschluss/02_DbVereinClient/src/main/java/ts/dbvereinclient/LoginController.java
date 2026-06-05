package ts.dbvereinclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

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
    private Button btnClose ;
    @FXML
    private Button btnConnect ; ;

    @FXML
    public void initialize() {
    }

    @FXML
    public void close() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void connect() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (txtIP.getText().length() == 0) {
            alert.setTitle("IP-Adresse fehlt");
            alert.setHeaderText("Ein Fehler ist aufgetreten.");
            alert.setContentText("Bitte prüfen Sie Ihre Eingabe!");
            alert.showAndWait();
        }else if (txtPort.getText().length() == 0) {
            alert.setTitle("Port-Angabe fehlt");
            alert.setHeaderText("Ein Fehler ist aufgetreten.");
            alert.setContentText("Bitte prüfen Sie Ihre Eingabe!");
            alert.showAndWait();
        }else if (txtUser.getText().length() == 0) {
            alert.setTitle("Benutzername fehlt");
            alert.setHeaderText("Ein Fehler ist aufgetreten.");
            alert.setContentText("Bitte prüfen Sie Ihre Eingabe!");
            alert.showAndWait();
        }else {
            try {
                DatabaseConnection.connect(txtIP.getText(), txtPort.getText(), txtUser.getText(), txtPwd.getText());
                close();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}