package org.pluralsight.model;

import org.pluralsight.interfaces.Product;

/**
 * Represents a drink item in the order.
 * Implements the Product interface.
 */
public class Drink implements Product {
    private final String size;    // small, medium, or large
    private final String flavor;  // e.g., Coke, Sprite

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public String getName() {
        return size + " Drink";
    }

    /**
     * Calculates drink price based on size.
     */
    @Override
    public double getPrice() {
        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
    }

    /**
     * Returns a formatted description of the drink.
     */
    @Override
    public String getDetails() {
        return size + " " + flavor + " - $" + String.format("%.2f", getPrice());
    }
}
