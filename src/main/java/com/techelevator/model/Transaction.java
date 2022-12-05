package com.techelevator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transaction {

    private BigDecimal balance = setBalance();

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal depositMoney(String userInput) {
        BigDecimal addAmount = convertStringToBigDecimal(userInput);
        balance = balance.add(addAmount);
        return balance;
    }

    public BigDecimal updatedBalance(BigDecimal price) {
        balance = getBalance().subtract(price).setScale(2, RoundingMode.HALF_UP);
        return balance;
    }

    public Double convertBigDecimalToDouble(BigDecimal money) {
        return money.doubleValue();
    }

    public BigDecimal convertDoubleToBigDecimal(Double price) {
        return new BigDecimal(price);
    }

    public void changeReturn(BigDecimal balance) {
        double change;
        int quarters;
        int dime;
        int nickels;

        change = (balance.doubleValue() * 100);

        quarters = ((int) change / 25);
        change = change - (quarters * 25);

        dime = ((int) change / 10);
        change = change - (dime * 10);

        nickels = ((int) change / 5);

        this.balance = BigDecimal.ZERO;

        System.out.println(
                        quarters + " quarters\n" +
                        dime + " dimes\n" +
                        nickels + " nickels"
        );

    }


    private BigDecimal setBalance() {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal convertStringToBigDecimal(String input) {
        return new BigDecimal(input);
    }

}
