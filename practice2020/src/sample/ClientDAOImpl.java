package sample;

import java.sql.*;

public class ClientDAOImpl implements ClientDAO {

    private static Client currentClient = new Client();


    @Override
    public boolean isValid(String username, String password) throws SQLException {
        Connection conn = null;
        boolean valid = false;

        try {
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "SELECT username, password, orders, spentMoney, discount, freeShipping FROM clients WHERE username = '" + username +"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.next()) {
                System.out.println("Wrong username or password");
            }
            else{
                currentClient.setUsername(rs.getString("username"));
                currentClient.setOrders(rs.getInt("orders"));
                currentClient.setSpentMoney(rs.getDouble("spentMoney"));
                currentClient.setDiscount(rs.getDouble("discount"));
                currentClient.setFreeShipping(rs.getInt("freeShipping"));
                valid = true;
            }
        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        finally {
            if (conn.isClosed() == false) {
                conn.close();
            }
        }

        return valid;
    }

    @Override
    public void addClient(Client client) throws SQLException {
        Connection conn = null;
        try{
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO clients(username, password, email, orders, spentMoney, discount, freeShipping) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, client.getUsername());
            pstmt.setString(2, client.getPassword());
            pstmt.setString(3, client.getEmail());
            pstmt.setInt(4, client.getOrders());
            pstmt.setDouble(5, client.getSpentMoney());
            pstmt.setDouble(6, client.getDiscount());
            pstmt.setInt(7, client.getFreeShipping());
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

    @Override
    public void addOrder(double orderPrice) throws SQLException {
        Connection conn = null;
        currentClient.setOrders(currentClient.getOrders() + 1);
        currentClient.setSpentMoney(currentClient.getSpentMoney() + orderPrice);

        try{
            String url = "jdbc:sqlite:C:/Users/Saule/Desktop/P20/Database/shop.db";
            conn = DriverManager.getConnection(url);
            String sql = "UPDATE clients SET orders=?, spentMoney=? WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(3, currentClient.getUsername());
            pstmt.setInt(1, currentClient.getOrders());
            pstmt.setDouble(2, currentClient.getSpentMoney());
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

    @Override
    public double getUserDiscount() {
        return currentClient.getDiscount();
    }


}
