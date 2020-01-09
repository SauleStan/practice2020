package sample;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    ObservableList<Item> getAllItems() throws SQLException;

}
