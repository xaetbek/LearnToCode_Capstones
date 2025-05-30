package org.pluralsight.model.interfaces;

// Interface for items that can be priced
public interface iPriceable {
    // Method that all priceable items must implement
    double calculatePrice();

    // Method to get display name for the item
    String getDisplayName();

    // Default method for formatted price
    default String getFormattedPrice() {
        return String.format("$%.2f", calculatePrice());
    }
}