package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UsersDatabase {
    private ObservableList<User> users = FXCollections.observableArrayList();


    public ObservableList<User> getAllUsers() throws SQLException {
        Connection conn = null;
        try{
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "SELECT username, email, address, orders, spentMoney, discount, freeShipping FROM clients ORDER BY orders DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                User newUser = new User();

                newUser.setUsername(rs.getString("username"));
                newUser.setOrders(rs.getInt("orders"));
                newUser.setEmail(rs.getString("email"));
                newUser.setAddress(rs.getString("address"));
                newUser.setSpentMoney(rs.getDouble("spentMoney"));
                newUser.setDiscount(rs.getDouble("discount"));
                newUser.setFreeShipping(rs.getBoolean("freeShipping"));

                users.add(newUser);
            }

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        } finally {
            if (conn.isClosed() == false) {
                conn.close();
            }
        }
        return users;
    }

    public void updateUser(String username, Double discount, int freeShipping) throws SQLException {
        Connection conn = null;
        try {
            //update database
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "UPDATE clients SET discount=?, freeShipping=? WHERE username='" + username + "'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, discount);
            pstmt.setInt(2, freeShipping);
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
