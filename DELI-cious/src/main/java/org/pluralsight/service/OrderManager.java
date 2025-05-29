package org.pluralsight.service;

import org.pluralsight.model.products.*;
import org.pluralsight.ui.HomeScreen;
import org.pluralsight.ui.OrderScreen;

/**
 * Main controller class that manages the order process and coordinates
 * between the user interface and business logic
 */
public class OrderManager {


    // Add a sandwich to the current order
    private void addSandwichToOrder() {
        try {
            Sandwich sandwich = OrderScreen.buildSandwich();
            currentOrder.addSandwich(sandwich);
            OrderScreen.displayMessage("\nSandwich added to order!");
            OrderScreen.displayMessage("Sandwich price: $" + String.format("%.2f", sandwich.calculatePrice()));
        } catch (Exception e) {
            OrderScreen.displayMessage("Error adding sandwich. Please try again.");
        }
    }

    // Add a drink to the current order
    private void addDrinkToOrder() {
        try {
            Drink drink = OrderScreen.buildDrink();
            currentOrder.addDrink(drink);
            OrderScreen.displayMessage("\nDrink added to order!");
            OrderScreen.displayMessage("Drink price: $" + String.format("%.2f", drink.calculatePrice()));
        } catch (Exception e) {
            OrderScreen.displayMessage("Error adding drink. Please try again.");
        }
    }

    // Add chips to the current order
    private void addChipsToOrder() {
        try {
            Chips chips = OrderScreen.buildChips();
            currentOrder.addChips(chips);
            OrderScreen.displayMessage("\nChips added to order!");
            OrderScreen.displayMessage("Chips price: $" + String.format("%.2f", chips.calculatePrice()));
        } catch (Exception e) {
            OrderScreen.displayMessage("Error adding chips. Please try again.");
        }
    }

    /**
     * Process the checkout for the current order
     * @return True if the order process should end, false if it should continue
     */
    private boolean checkout() {
        // Display the order summary
        OrderScreen.displayOrderSummary(currentOrder);

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
    private void confirmOrder() {
        try {
            // Generate and save receipt
            ReceiptGenerator.saveReceiptToFile(currentOrder);

            // Display confirmation
            OrderScreen.displayMessage("\n" + "=".repeat(40));
            OrderScreen.displayMessage("ORDER CONFIRMED!");
            OrderScreen.displayMessage("Total: $" + String.format("%.2f", currentOrder.calculateTotal()));
            OrderScreen.displayMessage("Receipt has been saved to receipts folder.");
            OrderScreen.displayMessage("Thank you for your order!");
            OrderScreen.displayMessage("=".repeat(40));

        } catch (Exception e) {
            OrderScreen.displayMessage("Error processing order: " + e.getMessage());
            OrderScreen.displayMessage("Order was not completed. Please try again.");
        }
    }

    /**
     * Get the current order (for testing purposes)
     * @return The current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }
}