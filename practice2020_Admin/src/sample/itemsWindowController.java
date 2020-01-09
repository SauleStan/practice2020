package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class itemsWindowController implements Initializable {
    //tableview stuff
    @FXML TableView tableView;
    @FXML TableColumn imageColumn;
    @FXML TableColumn nameColumn;
    @FXML TableColumn descriptionColumn;
    @FXML TableColumn priceColumn;
    @FXML TableColumn stockColumn;

    //buttons
    @FXML Button usersButton;
    @FXML Button modifyButton;

    ItemDatabase database = new ItemDatabase();

    @FXML public void onDeleteItemButtonPressed(ActionEvent event) throws SQLException {
        Item selectedItem = (Item) tableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        database.deleteItem(selectedItem.getName());
        database.getItemsList().remove(selectedItem);

        tableView.refresh();
    }

    @FXML public void onUserButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("usersWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) usersButton.getScene().getWindow();
        stage.setScene(scene);
        tableView.getItems().clear();
    }

    @FXML public void onModifyButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("itemData.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ItemDataController controller = loader.getController();
        Item selectedItem = (Item) tableView.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) modifyButton.getScene().getWindow();
        controller.initData(selectedItem);
        stage.setTitle("Modify Item");
        stage.setScene(scene);
        tableView.getItems().clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<Item, Object>("img"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //tableView.getItems().clear();
        try{
            tableView.setItems(database.getAllItems());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
