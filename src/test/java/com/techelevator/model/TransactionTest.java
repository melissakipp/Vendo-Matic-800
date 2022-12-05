package com.techelevator.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionTest {

    private Transaction test;
    static List<BigDecimal> transactionHistory = new ArrayList<>();

    @Before
    public void setup() {
        test = new Transaction();
    }

    @Test
    public void Test_get_balance() {
        BigDecimal bal = test.getBalance();
        assertEquals(new BigDecimal("0.00"), bal);
    }

    @Test
    public void Test_add_to_balance_with_user_string_input() {
        String userInput = "1";
        BigDecimal answer = test.depositMoney(userInput);
        assertEquals(new BigDecimal("1.00"), answer);
    }

    @Test
    public void Test_maintaining_balance_with_two_deposit() {
        Transaction maintaining = new Transaction();
        String userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        assertEquals(new BigDecimal("2.00"), maintaining.getBalance());
    }

    @Test
    public void Test_maintaining_balance_with_three_deposit() {
        Transaction maintaining = new Transaction();
        String userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        assertEquals(new BigDecimal("3.00"), maintaining.getBalance());
    }

    @Test
    public void Test_maintaining_balance_with_four_deposit() {
        Transaction maintaining = new Transaction();
        String userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        userInput = "1";
        transactionHistory.add(maintaining.depositMoney(userInput));
        assertEquals(new BigDecimal("4.00"), maintaining.getBalance());
    }

}
