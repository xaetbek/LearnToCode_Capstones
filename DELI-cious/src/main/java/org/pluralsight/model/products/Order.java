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
        double sandwichTotal = sandwiches.stream()
                .mapToDouble(Sandwich::calculatePrice)
                .sum();

        double drinkTotal = drinks.stream()
                .mapToDouble(Drink::calculatePrice)
                .sum();

        double chipsTotal = chips.stream()
                .mapToDouble(Chips::calculatePrice)
                .sum();

        return sandwichTotal + drinkTotal + chipsTotal;
    }

    // Check if the order is empty
    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }

    // Getters
    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public List<Sandwich> getSandwiches() { return sandwiches; }
    public List<Drink> getDrinks() { return drinks; }
    public List<Chips> getChips() { return chips; }
}