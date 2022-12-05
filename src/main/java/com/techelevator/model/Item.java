package com.techelevator.model;

public class Item {

    private String slotId;
    private String name;
    private Double price; // Convert to BigDecimal in Transaction class
    private String category;
    private int availableQuantity;

    public Item() {}

    public Item(
            String element0,
            String element1,
            Double element2,
            String element3,
            int startingStock) {
        this.slotId = element0.toUpperCase();
        this.name = element1;
        this.price = element2;
        this.category = element3;
        this.availableQuantity = startingStock;
    }


    public String getSlotId() {
        return slotId.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public String makeNoise(String category) {
        switch (category) {
            case "Candy":
                System.out.println("Munch Munch, Yum!");
                return "Munch Munch, Yum!";

            case "Chip":
                System.out.println("Crunch Crunch, Yum!");
                return "Crunch Crunch, Yum!";
            case "Drink":
                System.out.println("Glug Glug, Yum!");
                return "Glug Glug, Yum!";
            case "Gum":
                System.out.println("Chew Chew, Yum!");
                return "Chew Chew, Yum!";
        }
        return null;
    }

    public int decrementQuantity() {
        return getAvailableQuantity() - 1; //availableQuantity--;
    }

    @Override
    public String toString() {
        if (availableQuantity == 0) {
            return slotId +
                    "|" + name +
                    "|" + price +
                    "|" + category +
                    "|" + "SOLD OUT";
        }
        return slotId +
                "|" + name +
                "|" + price +
                "|" + category +
                "|" + availableQuantity;
    }

}
