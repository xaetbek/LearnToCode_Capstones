package org.pluralsight.model.products;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer order containing sandwiches, drinks, and chips
 */
public class Order {
    private LocalDateTime orderDateTime;
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    public Order() {
        this.orderDateTime = LocalDateTime.now();
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    // Add a sandwich to the order
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    // Add a drink to the order
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    // Add chips to the order
    public void addChips(Chips chips) {
        this.chips.add(chips);
    }

    /**
     * Calculate the total cost of the entire order
     * @return The total cost including all items
     */
    public double calculateTotal() {
        double total = 0.0;

        // Add sandwich prices
        for (Sandwich sandwich : sandwiches) {
            total += sandwich.calculatePrice();
        }

        // Add drink prices
        for (Drink drink : drinks) {
            total += drink.calculatePrice();
        }

        // Add chips prices
        for (Chips chip : chips) {
            total += chip.calculatePrice();
        }

        return total;
    }

    /**
     * Check if the order is empty
     * @return True if the order contains no items
     */
    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }

    // Getters
    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public List<Sandwich> getSandwiches() { return sandwiches; }
    public List<Drink> getDrinks() { return drinks; }
    public List<Chips> getChips() { return chips; }
}