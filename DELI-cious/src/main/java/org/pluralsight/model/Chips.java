package org.pluralsight.model;

/**
 * Represents a bag of chips in the order.
 * Implements the Product interface.
 */
public class Chips implements Product {
    private final String type;  // e.g., BBQ, Sour Cream, Plain

    public Chips(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return "Chips";
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    /**
     * Returns the chip type and price.
     */
    @Override
    public String getDetails() {
        return type + " Chips - $1.50";
    }
}

