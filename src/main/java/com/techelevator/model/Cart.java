package com.techelevator.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    public Cart() {
    }

    public void addToCart(String slotId, Double price) {
        Map<String, Double> items = new HashMap<>();
        items.put(slotId, price);
    }

    public void cartErrorMessage() {
        System.out.println("Sorry, your cart is empty.");
    }
}

