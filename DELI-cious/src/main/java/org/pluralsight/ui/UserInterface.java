package org.pluralsight.ui;

import org.pluralsight.model.enums.*;
import org.pluralsight.model.products.*;
import org.pluralsight.model.toppings.*;
import org.pluralsight.service.PriceCalculator;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the DELI-cious POS system
 */
public class UserInterface {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Display the home menu and get user choice
     * @return The user's menu choice
     */
    public static int getHomeMenuChoice() {
        System.out.println("\n========== DELI-cious ==========");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Enter your choice: ");
        return getIntInput();
    }

    /**
     * Display the order menu and get user choice
     * @return The user's menu choice
     */
    public static int getOrderMenuChoice() {
        System.out.println("\n========== Order Menu ==========");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("Enter your choice: ");
        return getIntInput();
    }

    /**
     * Guide the user through building a custom sandwich
     * @return A fully configured Sandwich object
     */
    public static Sandwich buildSandwich() {
        System.out.println("\n========== Add Sandwich ==========");

        // Select bread type
        System.out.println("Select your bread:");
        BreadType[] breads = BreadType.values();
        for (int i = 0; i < breads.length; i++) {
            System.out.println((i + 1) + ") " + breads[i].getDisplayName());
        }
        System.out.print("Enter choice: ");
        int breadChoice = getIntInput() - 1;
        if (breadChoice < 0 || breadChoice >= breads.length) {
            System.out.println("Invalid choice. Defaulting to White bread.");
            breadChoice = 0;
        }

        // Select sandwich size
        System.out.println("\nSandwich size:");
        SandwichSize[] sizes = SandwichSize.values();
        for (int i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i].getDisplayName() +
                    " - Base Price: $" + String.format("%.2f", PriceCalculator.getSandwichBasePrice(sizes[i])));
        }
        System.out.print("Enter choice: ");
        int sizeChoice = getIntInput() - 1;
        if (sizeChoice < 0 || sizeChoice >= sizes.length) {
            System.out.println("Invalid choice. Defaulting to 4\" sandwich.");
            sizeChoice = 0;
        }

        Sandwich sandwich = new Sandwich(sizes[sizeChoice], breads[breadChoice]);

        // Add toppings
        addMeatsToSandwich(sandwich);
        addCheesesToSandwich(sandwich);
        addRegularToppingsToSandwich(sandwich);
        addSaucesToSandwich(sandwich);
        addSidesToSandwich(sandwich);

        // Toasted option
        System.out.print("\nWould you like the sandwich toasted? (y/n): ");
        String toasted = scanner.nextLine().trim().toLowerCase();
        sandwich.setToasted(toasted.equals("y") || toasted.equals("yes"));

        return sandwich;
    }

    /**
     * Add meat toppings to a sandwich
     * @param sandwich The sandwich to add meats to
     */
    private static void addMeatsToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect meats (enter 0 to finish):");
        MeatType[] meats = MeatType.values();

        while (true) {
            for (int i = 0; i < meats.length; i++) {
                System.out.println((i + 1) + ") " + meats[i].getDisplayName());
            }
            System.out.println("0) Done with meats");
            System.out.print("Enter choice: ");

            int choice = getIntInput() - 1;
            if (choice == -1) break;
            if (choice < 0 || choice >= meats.length) {
                System.out.println("Invalid choice.");
                continue;
            }

            System.out.print("Extra meat? (y/n): ");
            String extra = scanner.nextLine().trim().toLowerCase();
            boolean isExtra = extra.equals("y") || extra.equals("yes");

            sandwich.addMeat(meats[choice], isExtra);
            double price = PriceCalculator.getMeatPrice(sandwich.getSize(), isExtra);
            System.out.println("Added " + meats[choice].getDisplayName() +
                    (isExtra ? " (Extra)" : "") + " - $" + String.format("%.2f", price));
        }
    }

    /**
     * Add cheese toppings to a sandwich
     * @param sandwich The sandwich to add cheeses to
     */
    private static void addCheesesToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect cheeses (enter 0 to finish):");
        CheeseType[] cheeses = CheeseType.values();

        while (true) {
            for (int i = 0; i < cheeses.length; i++) {
                System.out.println((i + 1) + ") " + cheeses[i].getDisplayName());
            }
            System.out.println("0) Done with cheeses");
            System.out.print("Enter choice: ");

            int choice = getIntInput() - 1;
            if (choice == -1) break;
            if (choice < 0 || choice >= cheeses.length) {
                System.out.println("Invalid choice.");
                continue;
            }

            System.out.print("Extra cheese? (y/n): ");
            String extra = scanner.nextLine().trim().toLowerCase();
            boolean isExtra = extra.equals("y") || extra.equals("yes");

            sandwich.addCheese(cheeses[choice], isExtra);
            double price = PriceCalculator.getCheesePrice(sandwich.getSize(), isExtra);
            System.out.println("Added " + cheeses[choice].getDisplayName() +
                    (isExtra ? " (Extra)" : "") + " - $" + String.format("%.2f", price));
        }
    }

    /**
     * Add regular toppings to a sandwich
     * @param sandwich The sandwich to add toppings to
     */
    private static void addRegularToppingsToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect other toppings (enter 0 to finish):");
        RegularToppingType[] toppings = RegularToppingType.values();

        while (true) {
            for (int i = 0; i < toppings.length; i++) {
                System.out.println((i + 1) + ") " + toppings[i].getDisplayName() + " (FREE)");
            }
            System.out.println("0) Done with toppings");
            System.out.print("Enter choice: ");

            int choice = getIntInput() - 1;
            if (choice == -1) break;
            if (choice < 0 || choice >= toppings.length) {
                System.out.println("Invalid choice.");
                continue;
            }

            sandwich.addRegularTopping(toppings[choice]);
            System.out.println("Added " + toppings[choice].getDisplayName() + " (FREE)");
        }
    }

    /**
     * Add sauces to a sandwich
     * @param sandwich The sandwich to add sauces to
     */
    private static void addSaucesToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect sauces (enter 0 to finish):");
        SauceType[] sauces = SauceType.values();

        while (true) {
            for (int i = 0; i < sauces.length; i++) {
                System.out.println((i + 1) + ") " + sauces[i].getDisplayName() + " (FREE)");
            }
            System.out.println("0) Done with sauces");
            System.out.print("Enter choice: ");

            int choice = getIntInput() - 1;
            if (choice == -1) break;
            if (choice < 0 || choice >= sauces.length) {
                System.out.println("Invalid choice.");
                continue;
            }

            sandwich.addSauce(sauces[choice]);
            System.out.println("Added " + sauces[choice].getDisplayName() + " (FREE)");
        }
    }

    /**
     * Add sides to a sandwich
     * @param sandwich The sandwich to add sides to
     */
    private static void addSidesToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect sides (enter 0 to finish):");
        SideType[] sides = SideType.values();

        while (true) {
            for (int i = 0; i < sides.length; i++) {
                System.out.println((i + 1) + ") " + sides[i].getDisplayName() + " (FREE)");
            }
            System.out.println("0) Done with sides");
            System.out.print("Enter choice: ");

            int choice = getIntInput() - 1;
            if (choice == -1) break;
            if (choice < 0 || choice >= sides.length) {
                System.out.println("Invalid choice.");
                continue;
            }

            sandwich.addSide(sides[choice]);
            System.out.println("Added " + sides[choice].getDisplayName() + " (FREE)");
        }
    }

    /**
     * Guide the user through selecting a drink
     * @return A configured Drink object
     */
    public static Drink buildDrink() {
        System.out.println("\n========== Add Drink ==========");

        // Select drink size
        System.out.println("Select drink size:");
        DrinkSize[] sizes = DrinkSize.values();
        for (int i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i].getDisplayName() +
                    " - $" + String.format("%.2f", PriceCalculator.getDrinkPrice(sizes[i])));
        }
        System.out.print("Enter choice: ");
        int sizeChoice = getIntInput() - 1;
        if (sizeChoice < 0 || sizeChoice >= sizes.length) {
            System.out.println("Invalid choice. Defaulting to Small.");
            sizeChoice = 0;
        }

        // Select flavor
        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine().trim();
        if (flavor.isEmpty()) {
            flavor = "Cola";
        }

        return new Drink(sizes[sizeChoice], flavor);
    }

    /**
     * Guide the user through selecting chips
     * @return A configured Chips object
     */
    public static Chips buildChips() {
        System.out.println("\n========== Add Chips ==========");
        System.out.print("Enter chip type: ");
        String chipType = scanner.nextLine().trim();
        if (chipType.isEmpty()) {
            chipType = "Classic";
        }

        System.out.println("Price: $" + String.format("%.2f", PriceCalculator.getChipsPrice()));
        return new Chips(chipType);
    }

    /**
     * Display a complete order summary
     * @param order The order to display
     */
    public static void displayOrderSummary(Order order) {
        System.out.println("\n========== Order Summary ==========");

        if (order.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        // Display sandwiches
        for (int i = 0; i < order.getSandwiches().size(); i++) {
            Sandwich sandwich = order.getSandwiches().get(i);
            System.out.println("\nSandwich #" + (i + 1) + ":");
            System.out.println("  Size: " + sandwich.getSize().getDisplayName());
            System.out.println("  Bread: " + sandwich.getBreadType().getDisplayName());

            if (!sandwich.getMeats().isEmpty()) {
                System.out.print("  Meats: ");
                for (int j = 0; j < sandwich.getMeats().size(); j++) {
                    Meat meat = sandwich.getMeats().get(j);
                    System.out.print(meat.getName());
                    if (meat.isExtra()) System.out.print(" (Extra)");
                    if (j < sandwich.getMeats().size() - 1) System.out.print(", ");
                }
                System.out.println();
            }

            if (!sandwich.getCheeses().isEmpty()) {
                System.out.print("  Cheeses: ");
                for (int j = 0; j < sandwich.getCheeses().size(); j++) {
                    Cheese cheese = sandwich.getCheeses().get(j);
                    System.out.print(cheese.getName());
                    if (cheese.isExtra()) System.out.print(" (Extra)");
                    if (j < sandwich.getCheeses().size() - 1) System.out.print(", ");
                }
                System.out.println();
            }

            if (!sandwich.getRegularToppings().isEmpty()) {
                System.out.print("  Toppings: ");
                for (int j = 0; j < sandwich.getRegularToppings().size(); j++) {
                    RegularTopping topping = sandwich.getRegularToppings().get(j);
                    System.out.print(topping.getName());
                    if (j < sandwich.getRegularToppings().size() - 1) System.out.print(", ");
                }
                System.out.println();
            }

            if (!sandwich.getSauces().isEmpty()) {
                System.out.print("  Sauces: ");
                for (int j = 0; j < sandwich.getSauces().size(); j++) {
                    Sauce sauce = sandwich.getSauces().get(j);
                    System.out.print(sauce.getName());
                    if (j < sandwich.getSauces().size() - 1) System.out.print(", ");
                }
                System.out.println();
            }

            if (!sandwich.getSides().isEmpty()) {
                System.out.print("  Sides: ");
                for (int j = 0; j < sandwich.getSides().size(); j++) {
                    Side side = sandwich.getSides().get(j);
                    System.out.print(side.getName());
                    if (j < sandwich.getSides().size() - 1) System.out.print(", ");
                }
                System.out.println();
            }

            if (sandwich.isToasted()) {
                System.out.println("  Toasted: Yes");
            }

            System.out.println("  Price: $" + String.format("%.2f", sandwich.calculatePrice()));
        }

        // Display drinks
        for (Drink drink : order.getDrinks()) {
            System.out.println("\nDrink: " + drink.getSize().getDisplayName() + " " + drink.getFlavor());
            System.out.println("  Price: $" + String.format("%.2f", drink.calculatePrice()));
        }

        // Display chips
        for (Chips chip : order.getChips()) {
            System.out.println("\nChips: " + chip.getChipType());
            System.out.println("  Price: $" + String.format("%.2f", chip.calculatePrice()));
        }

        System.out.println("\n" + "=".repeat(35));
        System.out.println("Total: $" + String.format("%.2f", order.calculateTotal()));
        System.out.println("=".repeat(35));
    }

    /**
     * Display checkout options and get user choice
     * @return The user's checkout choice
     */
    public static int getCheckoutChoice() {
        System.out.println("\n1) Confirm Order");
        System.out.println("0) Cancel Order");
        System.out.print("Enter your choice: ");
        return getIntInput();
    }

    /**
     * Get integer input from user with error handling
     * @return The integer input, or -1 if invalid
     */
    private static int getIntInput() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }

    /**
     * Display a message to the user
     * @param message The message to display
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Clear the screen (add some spacing)
     */
    public static void clearScreen() {
        // Simple way to add some space
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }
}