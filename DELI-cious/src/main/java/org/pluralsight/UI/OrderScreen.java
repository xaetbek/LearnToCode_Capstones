package org.pluralsight.UI;


import org.pluralsight.model.Sandwich;
import org.pluralsight.model.Topping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the ordering process for the DELI-cious app.
 * Supports adding sandwiches, drinks, chips, and checking out.
 */
public class OrderScreen {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Sandwich> sandwiches = new ArrayList<>();

    /**
     * Displays the order menu and processes user selections.
     */
    public static void display() {
        System.out.println("\nStarting a new order...");

        while (true) {
            System.out.println("\nOrder Menu:");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Sandwich sandwich = buildSandwich();
                    sandwiches.add(sandwich);
                    System.out.println("\nSandwich added:");
                    System.out.println(sandwich);
                    break;
                case "2":
                    // Future: Add drink logic
                    System.out.println("Adding drink... (not implemented yet)");
                    break;
                case "3":
                    // Future: Add chips logic
                    System.out.println("Adding chips... (not implemented yet)");
                    break;
                case "4":
                    checkout();
                    return;
                case "0":
                    System.out.println("Order canceled. Returning to home screen.");
                    sandwiches.clear(); // discard current order
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Handles the sandwich building logic with prompts for size, bread, toppings, and toast option.
     */
    private static Sandwich buildSandwich() {
        System.out.println("\n--- Build Your Sandwich ---");

        System.out.print("Choose sandwich size (4\", 8\", 12\"): ");
        String size = scanner.nextLine();

        System.out.print("Choose bread (white, wheat, rye, wrap): ");
        String bread = scanner.nextLine();

        List<Topping> toppings = new ArrayList<>();

        // Add meat
        while (true) {
            System.out.print("Add meat (steak, ham, salami, roast beef, chicken, bacon) or press enter to skip: ");
            String meat = scanner.nextLine().trim().toLowerCase();
            if (meat.isEmpty()) break;
            System.out.print("Add extra? (yes/no): ");
            boolean isExtra = scanner.nextLine().trim().equalsIgnoreCase("yes");
            toppings.add(new Topping(meat, Topping.Category.MEAT, isExtra));
        }

        // Add cheese
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

        System.out.print("Would you like the sandwich toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("yes");

        return new Sandwich(size, bread, toasted, toppings);
    }

    /**
     * Finalizes the order and prints a summary.
     * Future enhancement: save to receipt file.
     */
    private static void checkout() {
        System.out.println("\n--- Checkout ---");

        double total = sandwiches.stream()
                .mapToDouble(Sandwich::getPrice)
                .sum();

        sandwiches.forEach(s -> {
            System.out.println("\n" + s);
        });

        System.out.printf("\nTotal: $%.2f%n", total);
        System.out.println("Thank you for your order!");
        // Future: Save to receipt file here
    }
}
