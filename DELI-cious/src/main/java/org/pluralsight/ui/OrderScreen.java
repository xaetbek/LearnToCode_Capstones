package org.pluralsight.ui;

import org.pluralsight.model.products.*;
import org.pluralsight.service.*;

import java.util.Scanner;

public class OrderScreen {
    private static Scanner scanner = new Scanner(System.in);
    private static Order currentOrder;

    // Start a new order process
    public static void startNewOrder() {
        currentOrder = new Order();
        boolean orderInProgress = true;

        HomeScreen.displayMessage("Starting new order...");

        while (orderInProgress) {
            int choice = getOrderMenuChoice();

            switch (choice) {
                case 1:
                    addSandwichToOrder();
                    break;
                case 2:
                    addDrinkToOrder();
                    break;
                case 3:
                    addChipsToOrder();
                    break;
                case 4:
                    orderInProgress = !checkout();
                    break;
                case 0:
                    HomeScreen.displayMessage("Order cancelled. Returning to main menu.");
                    orderInProgress = false;
                    break;
                default:
                    OrderScreen.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    public static int getOrderMenuChoice() {
        System.out.println("\n========== Order Menu ==========");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        return getValidMenuChoice(0, 4, "Enter your choice: ");
    }

    // Add a sandwich to the current order
    private static void addSandwichToOrder() {
        try {
            Sandwich sandwich = BuildOrder.buildSandwich();
            currentOrder.addSandwich(sandwich);
            BuildOrder.displayMessage("\nSandwich added to order!");
            BuildOrder.displayMessage("Sandwich price: $" + String.format("%.2f", sandwich.calculatePrice()));
        } catch (Exception e) {
            BuildOrder.displayMessage("Error adding sandwich. Please try again.");
        }
    }

    // Add a drink to the current order
    private static void addDrinkToOrder() {
        try {
            Drink drink = BuildOrder.buildDrink();
            currentOrder.addDrink(drink);
            BuildOrder.displayMessage("\nDrink added to order!");
            BuildOrder.displayMessage("Drink price: $" + String.format("%.2f", drink.calculatePrice()));
        } catch (Exception e) {
            BuildOrder.displayMessage("Error adding drink. Please try again.");
        }
    }

    // Add chips to the current order
    private static void addChipsToOrder() {
        try {
            Chips chips = BuildOrder.buildChips();
            currentOrder.addChips(chips);
            BuildOrder.displayMessage("\nChips added to order!");
            BuildOrder.displayMessage("Chips price: $" + String.format("%.2f", chips.calculatePrice()));
        } catch (Exception e) {
            BuildOrder.displayMessage("Error adding chips. Please try again.");
        }
    }

    /**
     * Process the checkout for the current order
     * @return True if the order process should end, false if it should continue
     */
    private static boolean checkout() {
        // Display the order summary
        BuildOrder.displayOrderSummary(currentOrder);

        // Check if order is empty
        if (currentOrder.isEmpty()) {
            OrderScreen.displayMessage("Cannot checkout with an empty order.");
            return false; // Continue with order
        }

        // Get checkout choice
        int choice = OrderScreen.getCheckoutChoice();

        switch (choice) {
            case 1:
                // Confirm order
                confirmOrder();
                return true; // End order process
            case 0:
                // Cancel order
                OrderScreen.displayMessage("Order cancelled. Returning to main menu.");
                return true; // End order process
            default:
                OrderScreen.displayMessage("Invalid choice. Please try again.");
                return false; // Continue checkout process
        }
    }

    // Confirm the order and generate receipt */
    private static void confirmOrder() {
        try {
            // Generate and save receipt
            ReceiptGenerator.saveReceiptToFile(currentOrder);

            // Display confirmation
            displayMessage("\n" + "=".repeat(40));
            displayMessage("ORDER CONFIRMED!");
            displayMessage("Total: $" + String.format("%.2f", currentOrder.calculateTotal()));
            displayMessage("Receipt has been saved to receipts folder.");
            displayMessage("Thank you for your order!");
            displayMessage("=".repeat(40));

        } catch (Exception e) {
            displayMessage("Error processing order: " + e.getMessage());
            displayMessage("Order was not completed. Please try again.");
        }
    }

    /**
     * Get the current order (for testing purposes)
     * @return The current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    public static int getCheckoutChoice() {
        System.out.println("\n1) Confirm Order");
        System.out.println("0) Cancel Order");
        return getValidMenuChoice(0, 1, "Enter your choice: ");
    }

    private static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Please enter a valid response (cannot be empty).");
            }
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Please enter a valid number: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                System.out.println("Please enter a valid response (y/n, yes/no).");
                continue;
            }

            if (input.equals("y") || input.equals("yes") || input.equals("true") || input.equals("1")) {
                return true;
            } else if (input.equals("n") || input.equals("no") || input.equals("false") || input.equals("0")) {
                return false;
            } else {
                System.out.println("Invalid response. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    private static int getValidMenuChoice(int min, int max, String prompt) {
        while (true) {
            System.out.print(prompt);
            int choice = getIntInput();

            if (choice >= min && choice <= max) {
                return choice;
            } else {
                System.out.printf("Invalid choice. Please enter a number between %d and %d: ", min, max);
            }
        }
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}