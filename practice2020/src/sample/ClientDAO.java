package sample;

import java.sql.SQLException;
import java.util.List;

interface ClientDAO {
    boolean isValid(String username, String password) throws SQLException;
    void addClient(Client client) throws SQLException;
    void addOrder(double orderPrice) throws SQLException;
    double getUserDiscount();
}
