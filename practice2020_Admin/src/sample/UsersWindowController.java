package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersWindowController implements Initializable {
    //table stuff
    @FXML TableView<User> tableView;
    @FXML TableColumn usersColumn;
    @FXML TableColumn ordersColumn;
    @FXML TableColumn moneySpentColumn;
    @FXML TableColumn freeShippingColumn;
    @FXML TableColumn discountColumn;

    //Info stuff
    @FXML Label usernameLabel;
    @FXML CheckBox freeShippingCheckBox;
    @FXML CheckBox discountCheckBox;
    @FXML TextField discountTextField;

    @FXML Button itemsButton;

    UsersDatabase usersDatabase = new UsersDatabase();

    @FXML
    public void onItemsButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("itemsWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) itemsButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML public void onDiscountCheckBoxChecked(ActionEvent event){
        if(discountCheckBox.isSelected()) {
            discountTextField.setDisable(false);
        }else{
            discountTextField.setDisable(true);
        }
    }

    @FXML public void onApplyButtonPressed(ActionEvent event) throws SQLException {
        int freeShipping = 0;
        if(discountTextField.isDisabled()){
            discountTextField.setText("0");
        }
        if(freeShippingCheckBox.isSelected()){
            freeShipping = 1;
        }
        usersDatabase.updateUser(usernameLabel.getText(), Double.parseDouble(discountTextField.getText()), freeShipping);

        tableView.getItems().clear();
        tableView.setItems(usersDatabase.getAllUsers());
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();

        usernameLabel.setText(selectedUser.getUsername());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usersColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        ordersColumn.setCellValueFactory(new PropertyValueFactory<>("orders"));
        moneySpentColumn.setCellValueFactory(new PropertyValueFactory<>("spentMoney"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        freeShippingColumn.setCellValueFactory(new PropertyValueFactory<>("freeShipping"));

        discountTextField.setDisable(true);

        try{
            tableView.setItems(usersDatabase.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
