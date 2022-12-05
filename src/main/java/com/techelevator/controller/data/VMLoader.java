package com.techelevator.controller.data;

import com.techelevator.controller.utils.LogVM;
import com.techelevator.model.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class VMLoader {

    private final static Logger logs = Logger.getLogger(LogVM.class.getName());

    private static final Map<String, Item> inventory = new LinkedHashMap<>();

    private static final String CSV_FILE = "vendingmachine.csv";
    private static final int STARTING_STOCK = 5;

    /**
     * <p>For display and shopping cart</p>
     *
     * <p>Resources: <a href="https://www.youtube.com/watch?v=zKDmzKaAQro">Java read CSV File</a></p>
     */
    public static void displayInventory() {
        System.out.println("================================================");
        System.out.printf("%-5s %-20s %-6s %7s %5s %n", "Slot", "Item", "Price", "Type", "QTY");
        System.out.println("________________________________________________");
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] element = line.split(Pattern.quote("|"));
                inventory.put(element[0], new Item(
                        element[0],
                        element[1],
                        Double.parseDouble(element[2]),
                        element[3],
                        STARTING_STOCK)
                );
                System.out.printf("%-5s %-20s $%-6.2f %7s %3s %n",
                        element[0],
                        element[1],
                        Double.parseDouble(element[2]),
                        element[3],
                        STARTING_STOCK
                );
            }
        } catch (IOException e) {
            logs.log(Level.SEVERE, "File logger not working.", e.getMessage());
        }
        System.out.println("===============================================");
    }

    /**
     *
     * @return raw list of available items
     */
    public static Map<String, Item> getInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] attributes = line.split(Pattern.quote("|"));
            Item stockItems = new Item(
                    attributes[0],
                    attributes[1],
                    Double.parseDouble(attributes[2]),
                    attributes[3],
                    STARTING_STOCK
            );
            inventory.put(attributes[0], stockItems);
        }
    } catch (IOException e) {
        logs.log(Level.SEVERE, "File logger not working.", e.getMessage());
    }
        return inventory;
    }

}
