/**
 * rounding errors in increasing and decreasing item amount. (price is fokd)
 *page control interface to share page info??
 *
 */

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML private Button shoppingCartButton;

    //tableview stuff
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> name;
    @FXML private TableColumn<Item, String> price;

    //Description stuff
    @FXML private Label selectedItemLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label amountLabel;
    @FXML private Label totalPriceLabel;
    @FXML private ImageView imageView = new ImageView();


    ItemDAOImpl itemDAO = new ItemDAOImpl();

    int amount;
    double totalItemPrice = 0.0;
    double itemPrice = 0.0;
    OrderBasket orderBasket = new OrderBasket();

    DecimalFormat df = new DecimalFormat("#0.00");


    //switches scene to shopping cart scene
    @FXML
    void onShoppingCartButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shoppingCart.fxml"));
        Parent root = loader.load();
        ShoppingCartController shoppingCartController = loader.getController();
        shoppingCartController.setOrderBasket(orderBasket);
        Scene scene = new Scene(root);
        //sends info of this scene to next so you could come back to this one
        Scene previousScene = shoppingCartButton.getScene();
        shoppingCartController.setPreviousScene(previousScene);
        //opens a new scene
        Stage stage = (Stage) shoppingCartButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void onPlusButtonPressed(ActionEvent event) {
        amount++;
        amountLabel.setText("" + amount);
        totalItemPrice += itemPrice;
        //formats to avoid rounding errors
        totalItemPrice = Double.parseDouble(String.format("%.2f", totalItemPrice));

        totalPriceLabel.setText("" + df.format(totalItemPrice) + " $");
    }

    @FXML
    void onMinusButtonPressed(ActionEvent event) {

        if (totalItemPrice > 0 && amount > 0) {
            amount--;
            amountLabel.setText("" + amount);
            totalItemPrice -= itemPrice;
            //formats to avoid rounding errors
            totalItemPrice = Double.parseDouble(String.format("%.2f", totalItemPrice));

            totalPriceLabel.setText("" + df.format(totalItemPrice) + " $");
        }
    }

    @FXML
    void onAddToCartButtonPressed(ActionEvent event) {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        selectedItem.setTotalItemPrice(totalItemPrice);
        selectedItem.setItemAmount(amount);
        orderBasket.setChosenItems(selectedItem);
        //test
        orderBasket.showOrderBasket();
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        //sets image
        Image image = new Image(selectedItem.getImg());
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        itemPrice = selectedItem.getPrice();
        totalItemPrice = itemPrice;
        amount = 1;
        amountLabel.setText("" + amount);
        totalPriceLabel.setText("" + totalItemPrice);

        selectedItemLabel.setText(selectedItem.getName());
        descriptionLabel.setText(selectedItem.getDescription());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
        totalPriceLabel.setText("" + df.format(totalItemPrice));

        try {
            tableView.setItems(itemDAO.getAllItems());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
