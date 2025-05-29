package org.pluralsight.model.products;

import org.pluralsight.model.interfaces.iPriceable;

// Abstract base class for all orderable menu items
public abstract class AbstractMenuItem implements iPriceable {
    protected String name;
    protected double basePrice;

    public AbstractMenuItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    // Implementing iPriceable interface
    @Override
    public String getDisplayName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    // Abstract method - each menu item calculates price differently
    @Override
    public abstract double calculatePrice();

    // Default implementation using iPriceable interface
    @Override
    public String toString() {
        return getDisplayName() + " - " + getFormattedPrice();
    }
}