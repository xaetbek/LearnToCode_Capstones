package org.pluralsight.model.products;

import org.pluralsight.model.interfaces.Priceable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Order with only essential methods
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

    // Add products - ALL YOUR EXISTING METHODS STAY THE SAME
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chips) {
        this.chips.add(chips);
    }

    // Helper method to get all products as iPriceable
    public List<Priceable> getAllProducts() {
        List<Priceable> allProducts = new ArrayList<>();
        allProducts.addAll(sandwiches);  // Sandwich implements iPriceable
        allProducts.addAll(drinks);      // Drink implements iPriceable
        allProducts.addAll(chips);       // Chips implements iPriceable
        return allProducts;
    }

    // MAIN METHOD: Calculate total using streams and iPriceable interface
    public double calculateTotal() {
        return getAllProducts().stream()
                .mapToDouble(Priceable::calculatePrice)  // Method reference to interface method
                .sum();
    }

    // Check if order is empty
    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }

    // All the getters
    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public List<Sandwich> getSandwiches() { return new ArrayList<>(sandwiches); }
    public List<Drink> getDrinks() { return new ArrayList<>(drinks); }
    public List<Chips> getChips() { return new ArrayList<>(chips); }
}