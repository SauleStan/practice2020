package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML private PasswordField passwordTextField;
    @FXML private TextField usernameTextField;
    @FXML private Label statusLabel;
    @FXML private Button enterButton;

    ClientDAO clientDAO = new ClientDAOImpl();
    Window window = new Window();

    @FXML void onEnterPressed(ActionEvent event) throws SQLException, IOException {
        //checks if provided credentials exist in the database
        if(clientDAO.isValid(usernameTextField.getText(), passwordTextField.getText())){
            statusLabel.setText("");

            //closes the previous stage
            Stage stage = (Stage) enterButton.getScene().getWindow();
            stage.close();

            //opens a new stage
            window.openNewWindow("mainWindow.fxml", "E-commerce app");

        }else {
            statusLabel.setText("Incorrect username or password");
        }
    }

    //
    @FXML void onRegisterPressed(ActionEvent event) throws IOException {
        //closes the previous stage
        Stage stage = (Stage) enterButton.getScene().getWindow();
        stage.close();

        window.openNewWindow("register.fxml", "Register");
    }

}
