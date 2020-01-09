package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class registerController {

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField confirmPassTextField;
    @FXML private TextField emailTextField;

    @FXML private Button registerButton;

    @FXML private Label statusLabel;

    ClientDAO clientDAO = new ClientDAOImpl();
    Window window = new Window();

    @FXML
    void onRegisterPressed(ActionEvent event) throws SQLException, IOException {
        //check if fields have been filled properly
        if(checkFields()){
            //creates new client
            Client newClient = new Client();
            newClient.setUsername(usernameTextField.getText());
            newClient.setPassword(passwordTextField.getText());
            newClient.setEmail(emailTextField.getText());

            //adds new client to the database
            clientDAO.addClient(newClient);

            //closes the window
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();

            window.openNewWindow("successfulRegister.fxml", "");

        }


    }

    //function to check whether fields were filled properly
    private boolean checkFields(){
        if(!(confirmPassTextField.getText().equals(passwordTextField.getText()))){
            statusLabel.setText("passwords don't match");
            return false;
        }
        else if(usernameTextField.getText().isEmpty() || usernameTextField.getText().trim().isEmpty()){
            statusLabel.setText("username cannot be empty");
            return false;
        }
        else if (passwordTextField.getText().isEmpty() || passwordTextField.getText().trim().isEmpty()){
            statusLabel.setText("password cannot be empty");
            return false;
        }
        else if(emailTextField.getText().isEmpty() || emailTextField.getText().trim().isEmpty()){
            statusLabel.setText("Email cannot be empty");
            return false;
        }
        else return true;
    }
}
