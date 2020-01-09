package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Window {

    //method for opening a new stage
    public void openNewWindow(String filename, String title) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(filename));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
