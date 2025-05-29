// ReceiptGenerator.java
package org.pluralsight.service;

import org.pluralsight.model.products.*;
import org.pluralsight.model.toppings.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

/**
 * Service class for generating and saving order receipts
 */
public class ReceiptGenerator {

    // Save a receipt to file in the receipts directory
    public static void saveReceiptToFile(Order order) {
        try {
            // Create receipts directory if it doesn't exist
            File receiptsDir = new File("receipts");
            if (!receiptsDir.exists()) {
                receiptsDir.mkdirs();
            }

            // Generate filename based on order date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String filename = "receipts/" + order.getOrderDateTime().format(formatter) + ".txt";

            // Write receipt to file
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println(generateReceipt(order));
            }

            System.out.println("Receipt saved to: " + filename);

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }

    // Generate a formatted receipt string for an order
    public static String generateReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Header
        receipt.append("DELI-cious Receipt\n");
        receipt.append("====================\n");
        receipt.append("Date: ").append(order.getOrderDateTime().format(formatter)).append("\n\n");

        // Sandwiches
        int sandwichNumber = 1; // Start numbering from 1
        for (Sandwich sandwich : order.getSandwiches()) {
            receipt.append("Sandwich #").append(sandwichNumber).append(":\n");
            receipt.append("- Size: ").append(sandwich.getSize().getDisplayName()).append("\n");
            receipt.append("- Bread: ").append(sandwich.getBreadType().getDisplayName()).append("\n");

            // Add meats
            if (!sandwich.getMeats().isEmpty()) {
                receipt.append("- Meats: ");
                for (int j = 0; j < sandwich.getMeats().size(); j++) {
                    Meat meat = sandwich.getMeats().get(j);
                    receipt.append(meat.getName());
                    if (meat.isExtra()) receipt.append(" (Extra)");
                    if (j < sandwich.getMeats().size() - 1) receipt.append(", ");
                }
                receipt.append("\n");
            }

            // Add cheeses
            if (!sandwich.getCheeses().isEmpty()) {
                receipt.append("- Cheeses: ");
                for (int j = 0; j < sandwich.getCheeses().size(); j++) {
                    Cheese cheese = sandwich.getCheeses().get(j);
                    receipt.append(cheese.getName());
                    if (cheese.isExtra()) receipt.append(" (Extra)");
                    if (j < sandwich.getCheeses().size() - 1) receipt.append(", ");
                }
                receipt.append("\n");
            }

            // Add regular toppings
            if (!sandwich.getRegularToppings().isEmpty()) {
                receipt.append("- Toppings: ");
                for (int j = 0; j < sandwich.getRegularToppings().size(); j++) {
                    RegularTopping topping = sandwich.getRegularToppings().get(j);
                    receipt.append(topping.getName());
                    if (j < sandwich.getRegularToppings().size() - 1) receipt.append(", ");
                }
                receipt.append("\n");
            }

            // Add sauces
            if (!sandwich.getSauces().isEmpty()) {
                receipt.append("- Sauces: ");
                for (int j = 0; j < sandwich.getSauces().size(); j++) {
                    Sauce sauce = sandwich.getSauces().get(j);
                    receipt.append(sauce.getName());
                    if (j < sandwich.getSauces().size() - 1) receipt.append(", ");
                }
                receipt.append("\n");
            }

            // Add sides
            if (!sandwich.getSides().isEmpty()) {
                receipt.append("- Sides: ");
                for (int j = 0; j < sandwich.getSides().size(); j++) {
                    Side side = sandwich.getSides().get(j);
                    receipt.append(side.getName());
                    if (j < sandwich.getSides().size() - 1) receipt.append(", ");
                }
                receipt.append("\n");
            }

            // Toasted status
            if (sandwich.isToasted()) {
                receipt.append("- Toasted: Yes\n");
            }

            receipt.append("- Price: $").append(String.format("%.2f", sandwich.calculatePrice())).append("\n\n");
            sandwichNumber++; // Increment for next sandwich
        }

        // Drinks
        int drinkNumber = 1;
        for (Drink drink : order.getDrinks()) {
            receipt.append("Drink #").append(drinkNumber).append(": ");
            receipt.append(drink.getSize().getDisplayName()).append(" ").append(drink.getFlavor()).append("\n");
            receipt.append("- Price: $").append(String.format("%.2f", drink.calculatePrice())).append("\n\n");
            drinkNumber++;
        }

        // Chips
        int chipsNumber = 1;
        for (Chips chip : order.getChips()) {
            receipt.append("Chips #").append(chipsNumber).append(": ");
            receipt.append(chip.getChipType()).append("\n");
            receipt.append("- Price: $").append(String.format("%.2f", chip.calculatePrice())).append("\n\n");
            chipsNumber++;
        }

        // Total
        receipt.append("==================\n");
        receipt.append("TOTAL: $").append(String.format("%.2f", order.calculateTotal())).append("\n");
        receipt.append("==================\n");
        receipt.append("Thank you for your business!\n");

        return receipt.toString();
    }
}
