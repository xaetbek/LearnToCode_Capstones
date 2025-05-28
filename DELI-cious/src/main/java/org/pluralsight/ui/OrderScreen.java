package org.pluralsight.ui;

import org.pluralsight.model.enums.*;
import org.pluralsight.model.products.*;
import org.pluralsight.model.toppings.*;
import org.pluralsight.service.PriceCalculator;

import java.util.Scanner;

/**
 * Handles the order screen and sandwich/product customization
 */
public class OrderScreen {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Display the order menu and get user choice with validation
     * @return The user's menu choice
     */
    public static int getOrderMenuChoice() {
        System.out.println("\n========== Order Menu ==========");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        return getValidMenuChoice(0, 4, "Enter your choice: ");
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
        int breadChoice = getValidMenuChoice(1, breads.length, "Enter choice: ") - 1;

        // Select sandwich size
        System.out.println("\nSandwich size:");
        SandwichSize[] sizes = SandwichSize.values();
        for (int i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i].getDisplayName() +
                    " - Base Price: $" + String.format("%.2f", PriceCalculator.getSandwichBasePrice(sizes[i])));
        }
        int sizeChoice = getValidMenuChoice(1, sizes.length, "Enter choice: ") - 1;

        Sandwich sandwich = new Sandwich(sizes[sizeChoice], breads[breadChoice]);

        // Add toppings
        addMeatsToSandwich(sandwich);
        addCheesesToSandwich(sandwich);
        addRegularToppingsToSandwich(sandwich);
        addSaucesToSandwich(sandwich);
        addSidesToSandwich(sandwich);

        // Toasted option
        sandwich.setToasted(getBooleanInput("\nWould you like the sandwich toasted? (y/n): "));

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
                double regularPrice = PriceCalculator.getMeatPrice(sandwich.getSize(), false);
                double extraPrice = PriceCalculator.getMeatPrice(sandwich.getSize(), true);
                System.out.printf("%d) %s - $%.2f (Extra: $%.2f)%n",
                        i + 1, meats[i].getDisplayName(), regularPrice, extraPrice);
            }
            System.out.println("0) Done with meats");

            int choice = getValidMenuChoice(0, meats.length, "Enter choice: ") - 1;
            if (choice == -1) break;

            boolean isExtra = getBooleanInput("Extra meat? (y/n): ");

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
                double regularPrice = PriceCalculator.getCheesePrice(sandwich.getSize(), false);
                double extraPrice = PriceCalculator.getCheesePrice(sandwich.getSize(), true);
                System.out.printf("%d) %s - $%.2f (Extra: $%.2f)%n",
                        i + 1, cheeses[i].getDisplayName(), regularPrice, extraPrice);
            }
            System.out.println("0) Done with cheeses");

            int choice = getValidMenuChoice(0, cheeses.length, "Enter choice: ") - 1;
            if (choice == -1) break;

            boolean isExtra = getBooleanInput("Extra cheese? (y/n): ");

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

            int choice = getValidMenuChoice(0, toppings.length, "Enter choice: ") - 1;
            if (choice == -1) break;

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

            int choice = getValidMenuChoice(0, sauces.length, "Enter choice: ") - 1;
            if (choice == -1) break;

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

            int choice = getValidMenuChoice(0, sides.length, "Enter choice: ") - 1;
            if (choice == -1) break;

            sandwich.addSide(sides[choice]);
            System.out.println("Added " + sides[choice].getDisplayName() + " (FREE)");
        }
    }

    /**
     * Guide the user through selecting a drink
     * @return A configured Drink object
     */
    // Guide the user through selecting a drink
    public static Drink buildDrink() {
        System.out.println("\n========== Add Drink ==========");

        // Select drink size
        System.out.println("Select drink size:");
        DrinkSize[] sizes = DrinkSize.values();
        for (int i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i].getDisplayName() +
                    " - $" + String.format("%.2f", PriceCalculator.getDrinkPrice(sizes[i])));
        }
        int sizeChoice = getValidMenuChoice(1, sizes.length, "Enter choice: ") - 1;

        // Define available drink flavors
        String[] drinkFlavors = {
                "Cola",
                "Sprite",
                "Orange",
                "Water",
                "Coffee",
                "Tea",
                "Lemonade",
                "Root Beer",
                "Other (Custom)"
        };

        // Display flavor options
        System.out.println("\nSelect drink flavor:");
        for (int i = 0; i < drinkFlavors.length; i++) {
            System.out.println((i + 1) + ") " + drinkFlavors[i]);
        }

        int flavorChoice = getValidMenuChoice(1, drinkFlavors.length, "Enter choice: ") - 1;
        String flavor;

        // Handle custom flavor
        if (flavorChoice == drinkFlavors.length - 1) { // "Other (Custom)" option
            flavor = getStringInput("Enter custom drink flavor: ");
        } else {
            flavor = drinkFlavors[flavorChoice];
        }

        System.out.println("Selected: " + sizes[sizeChoice].getDisplayName() + " " + flavor);
        System.out.println("Price: $" + String.format("%.2f", PriceCalculator.getDrinkPrice(sizes[sizeChoice])));
        return new Drink(sizes[sizeChoice], flavor);
    }

    // Guide the user through selecting chips
    public static Chips buildChips() {
        System.out.println("\n========== Add Chips ==========");

        // Define available chip varieties
        String[] chipVarieties = {
                "Classic",
                "BBQ",
                "Sour Cream & Onion",
                "Salt & Vinegar",
                "Cheddar",
                "Jalapeño",
                "Honey Mustard",
                "Other (Custom)"
        };

        // Display chip options
        System.out.println("Select chip variety:");
        for (int i = 0; i < chipVarieties.length; i++) {
            System.out.println((i + 1) + ") " + chipVarieties[i]);
        }

        int chipChoice = getValidMenuChoice(1, chipVarieties.length, "Enter choice: ") - 1;
        String chipType;

        // Handle custom chip type
        if (chipChoice == chipVarieties.length - 1) { // "Other (Custom)" option
            chipType = getStringInput("Enter custom chip type: ");
        } else {
            chipType = chipVarieties[chipChoice];
        }

        System.out.println("Selected: " + chipType);
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
     * Display checkout options and get user choice with validation
     * @return The user's checkout choice
     */
    public static int getCheckoutChoice() {
        System.out.println("\n1) Confirm Order");
        System.out.println("0) Cancel Order");
        return getValidMenuChoice(0, 1, "Enter your choice: ");
    }

    /**
     * Get string input from user with validation
     * @param prompt The prompt message to display
     * @return Non-empty trimmed string input
     */
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

    /**
     * Get integer input from user with error handling and validation
     * @return The integer input
     */
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

    /**
     * Get boolean input from user with validation and case-insensitive checking
     * @param prompt The prompt message to display
     * @return True for yes responses, false for no responses
     */
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

    /**
     * Get valid menu choice with range validation
     * @param min Minimum valid choice
     * @param max Maximum valid choice
     * @param prompt The prompt message
     * @return Valid menu choice within range
     */
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

    /**
     * Display a message to the user
     * @param message The message to display
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }
}