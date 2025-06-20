package org.pluralsight.ui;

import org.pluralsight.model.enums.*;
import org.pluralsight.service.PriceCalculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Handles the main menu screen and related functionality
 */
public class HomeScreen {
    public static Scanner scanner = new Scanner(System.in);

    // Start the DELI-cious POS application
    public void start() {
        boolean running = true;

        // Display a welcome message
        HomeScreen.displayWelcome();

        while (running) {
            int choice = getMainMenuChoice();

            switch (choice) {
                case 1:
                    displayMenuItems();
                    break;
                case 2:
                    OrderScreen.startNewOrder();
                    break;
                case 3:
                    displayOrderHistory();
                    break;
                case 0:
                    displayMessage("Thank you for visiting DELI-cious!");
                    running = false;
                    break;
                default:
                    displayMessage("Invalid choice. Please try again.");
            }
        }
    }
    // Display welcome message
    public static void displayWelcome() {
        System.out.println();
        System.out.println("\n" + "~".repeat(40));
        System.out.println("~         Welcome to DELI-cious!       ~");
        System.out.println("~       Your Custom Sandwich Shop      ~");
        System.out.println("~".repeat(40));

    }

    // Display the main menu and get user choice with validation
    public static int getMainMenuChoice() {
        System.out.println("~~~~~ HOME ~~~~~");
        System.out.println("1. View Menu");
        System.out.println("2. New Order");
        System.out.println("3. Order History");
        System.out.println("0. Exit");
        return getValidMenuChoice(0, 3, "Choose an option: ");
    }

    // Display all available menu items with prices
    public static void displayMenuItems() {
        System.out.println("\n~~~~~~~~~~ DELI-cious Menu ~~~~~~~~~~");

        // Display Sandwich Base Prices
        System.out.println("\n🥪 SANDWICHES (Base Prices)");
        System.out.println("Choose from our fresh bread options:");

        BreadType[] breads = BreadType.values();
        for (BreadType bread : breads) {
            System.out.println("  • " + bread.getDisplayName() + " Bread");
        }

        System.out.println("\nSandwich Sizes & Base Prices:");
        SandwichSize[] sizes = SandwichSize.values();
        for (SandwichSize size : sizes) {
            System.out.printf("  • %-3s - $%.2f%n",
                    size.getDisplayName(),
                    PriceCalculator.getSandwichBasePrice(size));
        }

        // Display Premium Toppings
        System.out.println("\n🥩 PREMIUM TOPPINGS");
        System.out.println("Meats:");
        MeatType[] meats = MeatType.values();
        for (MeatType meat : meats) {
            System.out.printf("  • %-12s - 4\": $%.2f  |  8\": $%.2f  |  12\": $%.2f%n",
                    meat.getDisplayName(),
                    PriceCalculator.getMeatPrice(SandwichSize.FOUR_INCH, false),
                    PriceCalculator.getMeatPrice(SandwichSize.EIGHT_INCH, false),
                    PriceCalculator.getMeatPrice(SandwichSize.TWELVE_INCH, false));
        }

        System.out.println("\nCheeses:");
        CheeseType[] cheeses = CheeseType.values();
        for (CheeseType cheese : cheeses) {
            System.out.printf("  • %-12s - 4\": $%.2f  |   8\": $%.2f  |  12\": $%.2f%n",
                    cheese.getDisplayName(),
                    PriceCalculator.getCheesePrice(SandwichSize.FOUR_INCH, false),
                    PriceCalculator.getCheesePrice(SandwichSize.EIGHT_INCH, false),
                    PriceCalculator.getCheesePrice(SandwichSize.TWELVE_INCH, false));
        }

        System.out.println("\nExtra Portions Available:");
        System.out.printf("  • Extra Meat   - 4\": $%.2f  |   8\": $%.2f  |  12\": $%.2f%n",
                PriceCalculator.getMeatPrice(SandwichSize.FOUR_INCH, true),
                PriceCalculator.getMeatPrice(SandwichSize.EIGHT_INCH, true),
                PriceCalculator.getMeatPrice(SandwichSize.TWELVE_INCH, true));
        System.out.printf("  • Extra Cheese - 4\": $%.2f  |   8\": $%.2f  |  12\": $%.2f%n",
                PriceCalculator.getCheesePrice(SandwichSize.FOUR_INCH, true),
                PriceCalculator.getCheesePrice(SandwichSize.EIGHT_INCH, true),
                PriceCalculator.getCheesePrice(SandwichSize.TWELVE_INCH, true));

        // Display Free Toppings
        System.out.println("\n🥬 FREE TOPPINGS");
        System.out.println("Regular Toppings (Included):");
        RegularToppingType[] regularToppings = RegularToppingType.values();
        for (RegularToppingType topping : regularToppings) {
            System.out.println("  • " + topping.getDisplayName());
        }

        System.out.println("\nSauces (Included):");
        SauceType[] sauces = SauceType.values();
        for (SauceType sauce : sauces) {
            System.out.println("  • " + sauce.getDisplayName());
        }

        System.out.println("\nSides (Included):");
        SideType[] sides = SideType.values();
        for (SideType side : sides) {
            System.out.println("  • " + side.getDisplayName());
        }

        // Display Drinks
        System.out.println("\n🥤 DRINKS");
        DrinkSize[] drinkSizes = DrinkSize.values();
        for (DrinkSize size : drinkSizes) {
            System.out.printf("  • %-6s - $%.2f%n",
                    size.getDisplayName(),
                    PriceCalculator.getDrinkPrice(size));
        }
        System.out.println("  Available flavors: Cola, Sprite, Orange, Water, Coffee, Tea, etc.");

        // Display Chips
        System.out.println("\n🥔 CHIPS");
        System.out.printf("  • All varieties - $%.2f%n", PriceCalculator.getChipsPrice());
        System.out.println("  Available types: Classic, BBQ, Sour Cream & Onion, Salt & Vinegar, etc.");

        System.out.println("\n" + "~".repeat(45));
        System.out.println("All sandwiches can be toasted upon request!");
        System.out.println("~".repeat(45) + "\n");
    }

    /**
     * Display order history from saved receipts
     */
    public static void displayOrderHistory() {
        System.out.println("\n~~~~~~~~~~ Order History ~~~~~~~~~~");

        File receiptsDir = new File("receipts");
        if (!receiptsDir.exists() || !receiptsDir.isDirectory()) {
            System.out.println("No order history found. Place your first order to see history!");
            return;
        }

        File[] receiptFiles = receiptsDir.listFiles((dir, name) -> name.endsWith(".txt"));

        if (receiptFiles == null || receiptFiles.length == 0) {
            System.out.println("No previous orders found.");
            return;
        }

        System.out.println("Found " + receiptFiles.length + " previous order(s):");
        System.out.println();

        // Sort files by name (which includes date/time)
        java.util.Arrays.sort(receiptFiles, (a, b) -> b.getName().compareTo(a.getName()));

        for (int i = 0; i < Math.min(receiptFiles.length, 10); i++) { // Show last 10 orders
            File file = receiptFiles[i];
            String fileName = file.getName().replace(".txt", "");

            // Parse filename to display readable date/time
            try {
                String dateStr = fileName.substring(0, 8); // yyyyMMdd
                String timeStr = fileName.substring(9, 15); // HHmmss

                String year = dateStr.substring(0, 4);
                String month = dateStr.substring(4, 6);
                String day = dateStr.substring(6, 8);

                String hour = timeStr.substring(0, 2);
                String minute = timeStr.substring(2, 4);
                String second = timeStr.substring(4, 6);

                System.out.printf("%d. Order from %s/%s/%s at %s:%s:%s%n",
                        i + 1, month, day, year, hour, minute, second);

                // Show first few lines of receipt for preview
                displayReceiptPreview(file);
                System.out.println("   " + "-".repeat(35));

            } catch (Exception e) {
                System.out.println((i + 1) + ". Order: " + fileName);
            }
        }

        if (receiptFiles.length > 10) {
            System.out.println("... and " + (receiptFiles.length - 10) + " more orders");
        }

        System.out.println("\nWould you like to view a specific receipt? (y/n): ");
        boolean userChoice = getBooleanInput("");
        if (userChoice) {
            viewSpecificReceipt(receiptFiles);
        }
    }

    // Display a preview of a receipt file
    private static void displayReceiptPreview(File receiptFile) {
        try {
            Path path = Paths.get(receiptFile.getPath());
            try (Stream<String> lines = Files.lines(path)) {
                lines.limit(8) // Show first 8 lines
                        .forEach(line -> System.out.println("   " + line));
            }
        } catch (IOException e) {
            System.out.println("   Error reading receipt file.");
        }
    }

    /**
     * Allow user to view a complete receipt
     * @param receiptFiles Array of available receipt files
     */
    private static void viewSpecificReceipt(File[] receiptFiles) {
        int maxReceipts = Math.min(receiptFiles.length, 10);
        int choice = getValidMenuChoice(1, maxReceipts, "Enter receipt number (1-" + maxReceipts + "): ");

        File selectedFile = receiptFiles[choice - 1];
        System.out.println("\n" + "~".repeat(20));
        System.out.println("  FULL RECEIPT");
        System.out.println("~".repeat(20));

        try {
            Path path = Paths.get(selectedFile.getPath());
            Files.lines(path).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading receipt file: " + e.getMessage());
        }

        System.out.println("~".repeat(50));
    }

    // Get valid menu choice with range validation
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

    // Get boolean input from a user with validation and case-insensitive checking
    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                System.out.println("Please enter a valid response (y/n, yes/no).");
                continue;
            }

            // Accept various forms of yes/no responses
            if (input.equals("y") || input.equals("yes") || input.equals("true") || input.equals("1")) {
                return true;
            } else if (input.equals("n") || input.equals("no") || input.equals("false") || input.equals("0")) {
                return false;
            } else {
                System.out.println("Invalid response. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    // Get integer input from user with error handling and validation
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

    // Display a message to the user
    public static void displayMessage(String message) {
        System.out.println(message);
    }

}