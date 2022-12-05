package com.techelevator.view;

import com.techelevator.controller.data.VMLoader;
import com.techelevator.model.Cart;
import com.techelevator.model.Transaction;
import com.techelevator.model.Item;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private PrintWriter out;
    private Scanner in;

    private Transaction wallet = new Transaction();
    private Cart cart = new Cart();
    private List<BigDecimal> transactionHistory = new ArrayList<>();
    private Map<String, Item> inventory = VMLoader.getInventory();

    private static final String SELECTED_ONE_DOLLAR_BILL = "1";
    private static final String SELECTED_TWO_DOLLAR_BILL = "2";
    private static final String SELECTED_FIVE_DOLLAR_BILL = "5";
    private static final String SELECTED_TEN_DOLLAR_BILL = "10";

    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    private void toContinue(String action) {
        String userInput;
        switch (action) {
            case "Feed Money":
                System.out.println("\nContinue feed more bills? [Y or N]");
                userInput = in.nextLine();
                if (userInput.equalsIgnoreCase("y")) feedMoney();
                break;
            case "Select Product":
                System.out.println("\nAdd more items to your cart? [Y or N]");
                userInput = in.nextLine();
                if (userInput.equalsIgnoreCase("y")) cart();
                break;
            case "Add Money":
                System.err.println("\nGoodness... You don't have enough money.");
                System.out.println("Would you like to insert more money?[Y or N]");
                userInput = in.nextLine();
                if (userInput.equalsIgnoreCase("y")) feedMoney();
                else printHeader();
                break;
        }
    }

    public void feedMoney() {
        System.out.print("Please select bill >>> $");
        String userInput = in.nextLine();
        try {
            if (SELECTED_ONE_DOLLAR_BILL.equals(userInput)) {
                transactionHistory.add(wallet.depositMoney(userInput));
                System.out.println("Current Balance: $" + wallet.getBalance());
                toContinue("Feed Money");
            } else if (SELECTED_TWO_DOLLAR_BILL.equals(userInput)) {
                transactionHistory.add(wallet.depositMoney(userInput));
                System.out.println("Current Balance: $" + wallet.getBalance());
                toContinue("Feed Money");
            } else if (SELECTED_FIVE_DOLLAR_BILL.equals(userInput)) {
                transactionHistory.add(wallet.depositMoney(userInput));
                System.out.println("Current Balance: $" + wallet.getBalance());
                toContinue("Feed Money");
            } else if (SELECTED_TEN_DOLLAR_BILL.equals(userInput)) {
                transactionHistory.add(wallet.depositMoney(userInput));
                System.out.println("Current Balance: $" + wallet.getBalance());
                toContinue("Feed Money");
            } else {
                System.err.println("Invalid bill selection.\n" +
                        "Please select a valid [$1, $2, $,5 or $10] bill.");
                toContinue("Feed Money");
                // throw new InvalidBillOptionException("Please select a valid [$1, $2, $,5 or $10] bill.");
            }
        }
        catch (NumberFormatException ex){
            System.err.println("Invalid bill selection.");
        }
    }

    public void cart() {
        System.out.println("Enter item code: ");
        String userInput = in.nextLine();
        for (Map.Entry<String, Item> item : inventory.entrySet()) {
            if ((item.getKey().equalsIgnoreCase(userInput) &&
                    (item.getValue().getAvailableQuantity() > 0))) {

                if (wallet.convertBigDecimalToDouble(wallet.getBalance()) < item.getValue().getPrice()) {
                    toContinue("Add Money");
                } else if (wallet.convertBigDecimalToDouble(wallet.getBalance()) >=
                        item.getValue().getPrice()) {

                    BigDecimal finalItemPrice = wallet.convertDoubleToBigDecimal(item.getValue().getPrice());
                    transactionHistory.add(wallet.updatedBalance(finalItemPrice));
                    cart.addToCart(userInput, item.getValue().getPrice());
                    item.getValue().decrementQuantity();

                    item.getValue().makeNoise(item.getValue().getCategory());

                    System.out.printf("\nDISPENSING: %s for $%s",
                            item.getValue().getName(),
                            item.getValue().getPrice());
                    System.out.println("\n\nAvailable Funds: $" + wallet.getBalance());
                    toContinue("Select Product");
                    if (item.getValue().getAvailableQuantity() == 0) {
                        System.out.println(item.getValue().getName() + " | " + "is SOLD OUT");
                    }
                }
            }
        }
    }

    public void printHeader() {
        System.out.println("|**********************************************|");
        System.out.println("|                Welcome to                    |");
        System.out.println("|              Vendo-Matic 800                 |");
        System.out.println("|                 MAIN MENU                    |");
        System.out.println("|*********************************************|+");
        System.out.println();
        System.out.println("AVAILABLE FUNDS $" + wallet.getBalance());
    }

    public void printExitMessage() {
        System.out.println("|*********************************************|");
        System.out.println("|              Thank You shopping             |");
        System.out.println("|          with us. Have a nice day!          |");
        System.out.println("|*********************************************|");
    }
    public void displayAvailableItems() {
        System.out.println("+||------------------------------------------||+");
        System.out.println("+||              Available Items             ||+");
        System.out.println("+||__________________________________________||+");
        System.out.println();
        VMLoader.displayInventory();
    }

    public void printFeedMoneyHeader() {
        System.out.println("-||------------------------------------------||-");
        System.out.println("-||                FEED MONEY                ||-");
        System.out.println("-||          Please enter bill below         ||-");
        System.out.println("-||           to add your wallet:            ||-");
        System.out.println("-||           $1, $2, $5, or $10             ||-");
        System.out.println("-||__________________________________________||-");
        System.out.println();
        feedMoney();
    }

    public void purchaseMenuHeader() {
        System.out.println("+|--------------------------------------------|+");
        System.out.println("+|              PURCHASE MENU                 |+");
        System.out.println("+|           Step 1: Feed money               |+");
        System.out.println("+|           Step 2: Select Product           |+");
        System.out.println("+|           Step 3: Finish Transaction       |+");
        System.out.println("+|--------------------------------------------|+");
        System.out.println();
        System.out.println("AVAILABLE FUNDS $" + wallet.getBalance());
    }

    public void printSelectProductHeader() {
        System.out.println("+|--------------------------------------------|+");
        System.out.println("+|                Select Product              |+");
        System.out.println("+|--------------------------------------------|+");
        System.out.println();
        VMLoader.displayInventory();
        System.out.println();
        System.out.println("AVAILABLE FUNDS $" + wallet.getBalance());
        System.out.println();
        cart();
    }

    public void printFinishTransactionHeader() {
        System.out.println("+|--------------------------------------------|+");
        System.out.println("+|              Finish Transaction            |+");
        System.out.println("+|--------------------------------------------|+");
        System.out.println();
        System.out.println("AVAILABLE FUNDS $" + wallet.getBalance());
        System.out.println();
        System.out.println("Coin Return: ");
        wallet.changeReturn(wallet.getBalance());
    }

    public void printSaleReportMessage() {
        System.out.println("+|--------------------------------------------|+");
        System.out.println("+|              Please check the              |+");
        System.out.println("+|          log directory for report.         |+");
        System.out.println("+|--------------------------------------------|+");
    }

}
