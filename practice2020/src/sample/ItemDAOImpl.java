/**
 * make the methods here n stuff.
 * database doesn't have item table yet
 * gl hf
 */

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO{

    ObservableList<Item> Items = FXCollections.observableArrayList();

    public ObservableList<Item> getItems() {
        return Items;
    }

    public void setItems(ObservableList<Item> items) {
        Items = items;
    }

    public ObservableList<Item> getAllItems() throws SQLException {
        Connection conn = null;
        try{
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM items";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                Item newItem = new Item();

                newItem.setName(rs.getString("name"));
                newItem.setPrice(rs.getDouble("price"));
                newItem.setDescription(rs.getString("description"));
                newItem.setImg(rs.getString("img"));

                Items.add(newItem);
            }

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        } finally {
            if (conn.isClosed() == false) {
                conn.close();
            }
        }
        return Items;
    }
}
