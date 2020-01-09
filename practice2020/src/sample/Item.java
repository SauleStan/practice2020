package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty img = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    //private String itemID;

    private int itemAmount;
    private double totalItemPrice;

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getImg() {
        return img.get();
    }

    public void setImg(String img) {
        this.img.set(img);
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}
