package org.pluralsight.model.products;

import org.pluralsight.service.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's entire order consisting of multiple products.
 * Each product can be a sandwich, drink, or chips.
 */
public class Order {
    private final List<Product> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a new product to the order.
     */
    public void addItem(Product item) {
        items.add(item);
    }

    /**
     * Calculates total cost of the order.
     */
    public double getTotal() {
        return items.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    /**
     * Returns a formatted summary of the entire order.
     */
    public String getOrderDetails() {
        StringBuilder sb = new StringBuilder("--- Order Summary ---\n");
        for (Product item : items) {
            sb.append("- ").append(item.getDetails()).append("\n");
        }
        sb.append("\nTotal: $").append(String.format("%.2f", getTotal()));
        return sb.toString();
    }

    /**
     * Returns a copy of all ordered products.
     */
    public List<Product> getItems() {
        return new ArrayList<>(items);
    }
}

