package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class successfulRegisterController {

    @FXML
    private Button continueButton;

    Window window = new Window();

    @FXML
    void onContinuePressed(ActionEvent event) throws IOException {
        //closes the window
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();

        //opens login window
        window.openNewWindow("login.fxml", "Login");
    }

}
