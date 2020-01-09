package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ItemDatabase {

    private static ObservableList<Item> items = FXCollections.observableArrayList();

    public ObservableList<Item> getItemsList() {
        return items;
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
                newItem.setStock(rs.getInt("stock"));

                items.add(newItem);
            }

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        } finally {
            if (conn.isClosed() == false) {
                conn.close();
            }
        }
        return items;
    }

    public void deleteItem(String itemName) throws SQLException {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "DELETE FROM items WHERE name=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, itemName);
            pstmt.executeUpdate();
        }catch (Exception exc) {
            System.out.println(exc.getMessage());
        } finally {
            if (conn.isClosed() == false) {
                conn.close();
            }
        }
    }

    public void addItem(Item item, String imagePath) throws SQLException {
        Connection conn = null;
        try{
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO items(name, description, price, img, stock) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDescription());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setString(4, imagePath);
            pstmt.setInt(5, item.getStock());
            pstmt.executeUpdate();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        finally{
            if (conn.isClosed() == false){
                conn.close();
            }
        }
    }

    public void updateItem(String updatedItem, String newName, String newDescription, Double newPrice, int newStock, String imagePath) throws SQLException {
        Connection conn = null;
        try {
            //update database
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "UPDATE items SET name=?, description=?, price=?, img=?, stock=? WHERE name='" + updatedItem + "'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newName);
            pstmt.setString(2, newDescription);
            pstmt.setDouble(3, newPrice);
            pstmt.setString(4, imagePath);
            pstmt.setInt(5, newStock);
            pstmt.executeUpdate();



        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        } finally {
            if (conn.isClosed() == false) {
                conn.close();
            }
        }
    }
}
