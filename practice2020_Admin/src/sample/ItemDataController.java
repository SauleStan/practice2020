package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ItemDataController {
    @FXML TextField nameTextField;
    @FXML TextArea descriptionTextArea;
    @FXML TextField priceTextField;
    @FXML TextField imagePathTextField;
    @FXML TextField stockTextField;

    @FXML ImageView imageView;

    @FXML Button cancelButton;
    @FXML Button okButton;

    private ItemDatabase itemDatabase = new ItemDatabase();
    private String updatedItem;

    @FXML public void onOkPressed() throws SQLException, IOException {

        itemDatabase.updateItem(updatedItem, nameTextField.getText(), descriptionTextArea.getText(),
                Double.parseDouble(priceTextField.getText()), Integer.parseInt(stockTextField.getText()),
                imagePathTextField.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("itemsWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.setScene(scene);

    }

    @FXML public void onCancelPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("itemsWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setScene(scene);
    }


    public void initData(Item item){

        updatedItem = item.getName();

        nameTextField.setText(item.getName());
        descriptionTextArea.setText(item.getDescription());
        priceTextField.setText(String.valueOf(item.getPrice()));
        stockTextField.setText(String.valueOf(item.getStock()));
        imagePathTextField.setText(item.getImagePath());



        Image image = new Image(item.getImagePath());
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
    }

}
