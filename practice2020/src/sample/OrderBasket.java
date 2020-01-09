package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderBasket {
    private double price = 0;
    private ObservableList<Item> chosenItems = FXCollections.observableArrayList();


    public double getPrice() {
        return price;
    }

    public ObservableList<Item> getChosenItems() {
        return chosenItems;
    }


    public void showOrderBasket(){
        for(int i = 0; i < chosenItems.size(); i ++) {
            System.out.println(chosenItems.get(i).getName());
        }
    }

    public double calculatePrice(){
        price = 0;
        for(int i = 0; i < chosenItems.size(); i++){
            price += chosenItems.get(i).getTotalItemPrice();
        }
        return price;
    }

    public void setChosenItems(Item item) {
        //checks if there are any items in the list and if no, adds the chosen one
        if(chosenItems.size() == 0){
            chosenItems.add(item);
            price += item.getTotalItemPrice();
        }
        //if there are items, checks if there are no duplicates, if yes, adds info to the duplicate item
        else {
            for (int i = 0; i < chosenItems.size(); i++) {
                if (chosenItems.contains(item)) {
                    chosenItems.get(i).setItemAmount(chosenItems.get(i).getItemAmount() + item.getItemAmount());
                    chosenItems.get(i).setTotalItemPrice(chosenItems.get(i).getTotalItemPrice() + item.getTotalItemPrice());
                } else {
                    chosenItems.add(item);
                    price += item.getTotalItemPrice();
                }
            }
        }

    }
}
