package com.techelevator.model.category;

import com.techelevator.model.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    // Create new items
    Item candyTest;
    Item drinkTest;
    Item chipTest;
    Item gumTest;

    @Before
    public void setUp() {
        candyTest = new Item(
                "B2",
                "Cowtales",
                1.50,
                "Candy",
                5
        );
        chipTest = new Item(
                "a1",
                "Potato Crisps",
                3.05,
                "Chip",
                5
        );
        drinkTest = new Item(
                "C1",
                "Cola",
                1.25,
                "Drink",
                5
        );
        gumTest = new Item(
                "d2",
                "Little League Chew",
                0.95,
                "Gum",
                5
        );
    }

    @Test
    public void get_slot_id_for_item() {
        assertEquals("B2", candyTest.getSlotId());
        assertEquals("C1", drinkTest.getSlotId());
    }

    @Test
    public void get_slot_id_for_item_if_lowercase_entered() {
        assertEquals("A1", chipTest.getSlotId());
        assertEquals("D2", gumTest.getSlotId());
    }

    @Test
    public void get_name_for_item() {
        assertEquals("Cowtales", candyTest.getName());
        assertEquals("Potato Crisps", chipTest.getName());
        assertEquals("Cola", drinkTest.getName());
        assertEquals("Little League Chew", gumTest.getName());
    }

    @Test
    public void get_price_for_item() {
        assertEquals(1.50, candyTest.getPrice(), 0);
        assertEquals(3.05, chipTest.getPrice(),0);
        assertEquals(1.25, drinkTest.getPrice(),0);
        assertEquals(0.95, gumTest.getPrice(),0);
    }

    @Test
    public void get_category_for_item() {
        assertEquals("Candy", candyTest.getCategory());
        assertEquals("Chip", chipTest.getCategory());
        assertEquals("Drink", drinkTest.getCategory());
        assertEquals("Gum", gumTest.getCategory());
    }

    @Test
    public void get_available_starting_quantity() {
        assertEquals(5, candyTest.getAvailableQuantity());
        assertEquals(5, chipTest.getAvailableQuantity());
        assertEquals(5, drinkTest.getAvailableQuantity());
        assertEquals(5, gumTest.getAvailableQuantity());
    }

//    @Test
//    public void purchased_item_minus_from_initial_quantity() {
//        assertEquals(4, candyTest.getAvailableQuantity());
//    }

    @Test
    public void decrement_quantity_from_inventory_display() {
        assertEquals(4, candyTest.decrementQuantity());
    }

    @Test
    public void print_correct_message() {
        assertEquals("Munch Munch, Yum!", candyTest.makeNoise("Candy"));
        assertEquals("Crunch Crunch, Yum!", chipTest.makeNoise("Chip"));
        assertEquals("Glug Glug, Yum!", drinkTest.makeNoise("Drink"));
        assertEquals("Chew Chew, Yum!", gumTest.makeNoise("Gum"));
    }

    @Test
    public void toString_return() {
        assertEquals("B2|Cowtales|1.5|Candy|5", candyTest.toString());
        assertEquals("A1|Potato Crisps|3.05|Chip|5", chipTest.toString());
        assertEquals("C1|Cola|1.25|Drink|5", drinkTest.toString());
        assertEquals("D2|Little League Chew|0.95|Gum|5", gumTest.toString());
    }

}
