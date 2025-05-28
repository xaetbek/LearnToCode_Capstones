package org.pluralsight.model;

/**
 * Represents a generic product in an order (e.g., sandwich, drink, chips).
 * Ensures all products have a name, price, and displayable details.
 */
public interface Product {
    String getName();
    double getPrice();
    String getDetails();
}
