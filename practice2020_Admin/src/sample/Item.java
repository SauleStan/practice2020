package sample;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private ObjectProperty img = new SimpleObjectProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleIntegerProperty stock = new SimpleIntegerProperty();
    private String imagePath;
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

    public Object getImg() {
        return img.get();
    }

    public void setImg(String img) {
        imagePath = img;

        Image image = new Image(img);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(67);
        imageView.setFitHeight(67);

        this.img.set(imageView);
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getImagePath() {
        return imagePath;
    }
}
