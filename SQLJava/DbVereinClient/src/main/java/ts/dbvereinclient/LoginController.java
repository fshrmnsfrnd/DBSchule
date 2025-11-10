package ts.dbvereinclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController
{
    @FXML
    private TextField txtPort;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnConnect;
    @FXML
    private Label lblPort;
    @FXML
    private Label lblPwd;
    @FXML
    private Label lblUser;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtIP;
    @FXML
    private Label lblIP;
    @FXML
    private PasswordField txtPwd;
    @FXML
    private Label lblHeader;

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
        try {
            DbVerein.connect(txtIP.getText(), txtPort.getText(), txtUser.getText(), txtPwd.getText());
        } catch (SQLException e) {
            App.showErrorAlert("Error", "connectToDB", e.getMessage());
        }

        this.close();
    }
}