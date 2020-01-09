package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShoppingCartController {
    @FXML Button catalogButton;
    @FXML Button orderButton;
    @FXML Button removeOneButton;
    @FXML Button removeAllButton;

    @FXML Label priceLabel;
    @FXML Label discountLabel;
    @FXML Label finalPriceLabel;

    //tableview stuff
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> name;
    @FXML private TableColumn<Item, String> amount;
    @FXML private TableColumn<Item, String> price;

    private OrderBasket orderBasket;
    ClientDAO clientDAO = new ClientDAOImpl();
    private Scene previousScene;

    //switches scene to catalog scene
    @FXML
    void onCatalogButtonPressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) catalogButton.getScene().getWindow();
        stage.setScene(previousScene);
    }

    @FXML void onOrderButtonPressed(ActionEvent event) throws SQLException {
        clientDAO.addOrder(orderBasket.getPrice());

    }


    @FXML void onRemoveOneButtonPressed(ActionEvent event){
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        selectedItem.setItemAmount(selectedItem.getItemAmount() - 1);
        selectedItem.setTotalItemPrice(selectedItem.getTotalItemPrice() - selectedItem.getPrice());

        setLabels();

        tableView.refresh();
    }

    @FXML void onRemoveAllButtonPressed(ActionEvent event){
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        orderBasket.getChosenItems().remove(selectedItem);

        setLabels();

        tableView.refresh();
    }

    public void setOrderBasket(OrderBasket orderBasket){
        this.orderBasket = orderBasket;

        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<Item, String>("itemAmount"));
        price.setCellValueFactory(new PropertyValueFactory<Item, String>("totalItemPrice"));
        discountLabel.setText(String.valueOf(clientDAO.getUserDiscount()));

        setLabels();

        tableView.setItems(orderBasket.getChosenItems());

    }

    public void setLabels(){
        priceLabel.setText(String.valueOf(orderBasket.calculatePrice()));
        finalPriceLabel.setText(String.valueOf(orderBasket.calculatePrice()*Double.parseDouble(discountLabel.getText())));
    }

    public void setPreviousScene(Scene scene){
        previousScene = scene;
    }

}
