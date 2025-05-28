package org.pluralsight.model.products;

import org.pluralsight.service.Product;
import org.pluralsight.model.toppings.Topping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    public Sandwich(String size, String bread, boolean toasted, List<Topping> toppings) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
        this.toppings = toppings;
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

    /**
     * Builds a Sandwich object through user input (interactive CLI).
     * Uses advanced OOP concepts including encapsulation, generics, and streams.
     */
    public static Sandwich build() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Build Your Sandwich ---");

        System.out.print("Choose sandwich size (4\", 8\", 12\"): ");
        String size = scanner.nextLine();

        System.out.print("Choose bread (white, wheat, rye, wrap): ");
        String bread = scanner.nextLine();

        List<Topping> toppings = new ArrayList<>();

        // Add meat toppings
        while (true) {
            System.out.print("Add meat (steak, ham, salami, roast beef, chicken, bacon) or press enter to skip: ");
            String meat = scanner.nextLine().trim().toLowerCase();
            if (meat.isEmpty()) break;
            System.out.print("Add extra? (yes/no): ");
            boolean isExtra = scanner.nextLine().trim().equalsIgnoreCase("yes");
            toppings.add(new Topping(meat, Topping.Category.MEAT, isExtra));
        }

        // Add cheese toppings
        while (true) {
            System.out.print("Add cheese (american, provolone, cheddar, swiss) or press enter to skip: ");
            String cheese = scanner.nextLine().trim().toLowerCase();
            if (cheese.isEmpty()) break;
            System.out.print("Add extra? (yes/no): ");
            boolean isExtra = scanner.nextLine().trim().equalsIgnoreCase("yes");
            toppings.add(new Topping(cheese, Topping.Category.CHEESE, isExtra));
        }

        // Add regular toppings
        while (true) {
            System.out.print("Add regular topping (lettuce, onions, tomatoes, etc.) or press enter to skip: ");
            String regular = scanner.nextLine().trim().toLowerCase();
            if (regular.isEmpty()) break;
            toppings.add(new Topping(regular, Topping.Category.REGULAR, false));
        }

        // Add sauces
        while (true) {
            System.out.print("Add sauce (mayo, mustard, ranch, etc.) or press enter to skip: ");
            String sauce = scanner.nextLine().trim().toLowerCase();
            if (sauce.isEmpty()) break;
            toppings.add(new Topping(sauce, Topping.Category.SAUCE, false));
        }

        // Toasting option
        System.out.print("Would you like the sandwich toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("yes");

        return new Sandwich(size, bread, toasted, toppings);
    }
}
