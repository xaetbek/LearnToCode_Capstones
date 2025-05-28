package org.pluralsight.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a customizable sandwich that a customer can order.
 * Implements the Product interface to support polymorphic treatment in orders.
 */
public class Sandwich implements Product {
    private final String size;      // 4", 8", or 12"
    private final String bread;     // Bread type: white, wheat, rye, wrap
    private final boolean toasted;  // Toasting option
    private final List<Topping> toppings;  // List of selected toppings

    public Sandwich(String size, String bread, boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
    }

    /**
     * Adds a list of toppings to the sandwich.
     */
    public void addToppings(List<Topping> toppingList) {
        toppings.addAll(toppingList);
    }

    /**
     * Gets the base price of the sandwich based on its size.
     */
    private double getBasePrice() {
        return switch (size) {
            case "4\"" -> 5.50;
            case "8\"" -> 7.00;
            case "12\"" -> 8.50;
            default -> 0.0;
        };
    }

    /**
     * Calculates total sandwich price including toppings.
     */
    @Override
    public double getPrice() {
        return getBasePrice() + toppings.stream()
                .mapToDouble(t -> t.getPrice(size))
                .sum();
    }

    @Override
    public String getName() {
        return size + " Sandwich";
    }

    /**
     * Provides a formatted string describing the sandwich details.
     */
    @Override
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(" sandwich on ").append(bread);
        if (toasted) sb.append(" (toasted)");
        sb.append("\n  Toppings: ")
                .append(toppings.stream()
                        .map(Topping::toString)
                        .collect(Collectors.joining(", ")));
        sb.append("\n  Price: $").append(String.format("%.2f", getPrice()));
        return sb.toString();
    }

    /**
     * Returns a copy of the topping list for read-only purposes.
     */
    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }
}
