package org.pluralsight.ui;

import org.pluralsight.model.enums.*;
import org.pluralsight.model.products.*;
import org.pluralsight.model.toppings.*;
import org.pluralsight.service.OrderManager;
import org.pluralsight.service.PriceCalculator;

import java.util.Arrays;
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
            int choice = OrderScreen.getOrderMenuChoice();

            switch (choice) {
                case 1:
                    OrderManager.addSandwichToOrder();
                    break;
                case 2:
                    OrderManager.addDrinkToOrder();
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

    public static Sandwich buildSandwich() {
        System.out.println("\n========== Add Sandwich ==========");

        System.out.println("Select your bread:");
        BreadType[] breads = BreadType.values();
        for (int i = 0; i < breads.length; i++) {
            System.out.println((i + 1) + ") " + breads[i].getDisplayName());
        }
        int breadChoice = getValidMenuChoice(1, breads.length, "Enter choice: ") - 1;

        System.out.println("\nSandwich size:");
        SandwichSize[] sizes = SandwichSize.values();
        for (int i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i].getDisplayName() +
                    " - Base Price: $" + String.format("%.2f", PriceCalculator.getSandwichBasePrice(sizes[i])));
        }
        int sizeChoice = getValidMenuChoice(1, sizes.length, "Enter choice: ") - 1;

        Sandwich sandwich = new Sandwich(sizes[sizeChoice], breads[breadChoice]);

        addMeatsToSandwich(sandwich);
        addCheesesToSandwich(sandwich);
        addRegularToppingsToSandwich(sandwich);
        addSaucesToSandwich(sandwich);
        addSidesToSandwich(sandwich);

        sandwich.setToasted(getBooleanInput("\nWould you like the sandwich toasted? (y/n): "));

        return sandwich;
    }

    private static void addMeatsToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect meats (enter 0 to finish):");
        MeatType[] meats = MeatType.values();

        while (true) {
            // Use stream to display meat options with prices
            Arrays.stream(meats)
                    .forEach(meat -> {
                        int index = Arrays.asList(meats).indexOf(meat) + 1;
                        double regularPrice = PriceCalculator.getMeatPrice(sandwich.getSize(), false);
                        double extraPrice = PriceCalculator.getMeatPrice(sandwich.getSize(), true);
                        System.out.printf("%d) %s - Regular: $%.2f, Extra: $%.2f%n",
                                index, meat.getDisplayName(), regularPrice, extraPrice);
                    });
            System.out.println("0) Done with meats");

            int choice = getValidMenuChoice(0, meats.length, "Enter choice: ") - 1;
            if (choice == -1) break;

            boolean isExtra = getBooleanInput("Extra meat? (y/n): ");

            // Use stream to get selected meat safely
            MeatType selectedMeat = Arrays.stream(meats)
                    .skip(choice)
                    .findFirst()
                    .orElse(MeatType.TURKEY); // Safe fallback to first enum value

            sandwich.addMeat(selectedMeat, isExtra);
            double actualPrice = PriceCalculator.getMeatPrice(sandwich.getSize(), isExtra);
            System.out.println("Added " + selectedMeat.getDisplayName() +
                    (isExtra ? " (Extra)" : " (Regular)") + " - $" + String.format("%.2f", actualPrice));
        }
    }

    private static void addCheesesToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect cheeses (enter 0 to finish):");
        CheeseType[] cheeses = CheeseType.values();

        while (true) {
            // Use stream to display cheese options with prices
            Arrays.stream(cheeses)
                    .forEach(cheese -> {
                        int index = Arrays.asList(cheeses).indexOf(cheese) + 1;
                        double regularPrice = PriceCalculator.getCheesePrice(sandwich.getSize(), false);
                        double extraPrice = PriceCalculator.getCheesePrice(sandwich.getSize(), true);
                        System.out.printf("%d) %s - Regular: $%.2f, Extra: $%.2f%n",
                                index, cheese.getDisplayName(), regularPrice, extraPrice);
                    });
            System.out.println("0) Done with cheeses");

            int choice = getValidMenuChoice(0, cheeses.length, "Enter choice: ") - 1;
            if (choice == -1) break;

            boolean isExtra = getBooleanInput("Extra cheese? (y/n): ");

            // Use stream to get selected cheese safely
            CheeseType selectedCheese = Arrays.stream(cheeses)
                    .skip(choice)
                    .findFirst()
                    .orElse(CheeseType.AMERICAN); // Safe fallback to first enum value

            sandwich.addCheese(selectedCheese, isExtra);
            double actualPrice = PriceCalculator.getCheesePrice(sandwich.getSize(), isExtra);
            System.out.println("Added " + selectedCheese.getDisplayName() +
                    (isExtra ? " (Extra)" : " (Regular)") + " - $" + String.format("%.2f", actualPrice));
        }
    }

    private static void addRegularToppingsToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect other toppings (enter 0 to finish):");
        RegularToppingType[] toppings = RegularToppingType.values();

        while (true) {
            // Use stream to display topping options
            Arrays.stream(toppings)
                    .forEach(topping -> {
                        int index = Arrays.asList(toppings).indexOf(topping) + 1;
                        System.out.println(index + ") " + topping.getDisplayName() + " (FREE)");
                    });
            System.out.println("0) Done with toppings");

            int choice = getValidMenuChoice(0, toppings.length, "Enter choice: ") - 1;
            if (choice == -1) break;

            // Use stream to get selected topping safely
            RegularToppingType selectedTopping = Arrays.stream(toppings)
                    .skip(choice)
                    .findFirst()
                    .orElse(RegularToppingType.LETTUCE); // Safe fallback to first enum value

            sandwich.addRegularTopping(selectedTopping);
            System.out.println("Added " + selectedTopping.getDisplayName() + " (FREE)");
        }
    }

    private static void addSaucesToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect sauces (enter 0 to finish):");
        SauceType[] sauces = SauceType.values();

        while (true) {
            // Use stream to display sauce options
            Arrays.stream(sauces)
                    .forEach(sauce -> {
                        int index = Arrays.asList(sauces).indexOf(sauce) + 1;
                        System.out.println(index + ") " + sauce.getDisplayName() + " (FREE)");
                    });
            System.out.println("0) Done with sauces");

            int choice = getValidMenuChoice(0, sauces.length, "Enter choice: ") - 1;
            if (choice == -1) break;

            // Use stream to get selected sauce safely
            SauceType selectedSauce = Arrays.stream(sauces)
                    .skip(choice)
                    .findFirst()
                    .orElse(SauceType.MAYO); // Safe fallback to first enum value

            sandwich.addSauce(selectedSauce);
            System.out.println("Added " + selectedSauce.getDisplayName() + " (FREE)");
        }
    }

    private static void addSidesToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect sides (enter 0 to finish):");
        SideType[] sides = SideType.values();

        while (true) {
            // Use stream to display side options
            Arrays.stream(sides)
                    .forEach(side -> {
                        int index = Arrays.asList(sides).indexOf(side) + 1;
                        System.out.println(index + ") " + side.getDisplayName() + " (FREE)");
                    });
            System.out.println("0) Done with sides");

            int choice = getValidMenuChoice(0, sides.length, "Enter choice: ") - 1;
            if (choice == -1) break;

            // Use stream to get selected side safely
            SideType selectedSide = Arrays.stream(sides)
                    .skip(choice)
                    .findFirst()
                    .orElse(SideType.COLESLAW); // Safe fallback to first enum value

            sandwich.addSide(selectedSide);
            System.out.println("Added " + selectedSide.getDisplayName() + " (FREE)");
        }
    }

    public static Drink buildDrink() {
        System.out.println("\n========== Add Drink ==========");

        System.out.println("Select drink size:");
        DrinkSize[] sizes = DrinkSize.values();

        // Use stream to display drink sizes with prices
        Arrays.stream(sizes)
                .forEach(size -> {
                    int index = Arrays.asList(sizes).indexOf(size) + 1;
                    System.out.println(index + ") " + size.getDisplayName() +
                            " - $" + String.format("%.2f", PriceCalculator.getDrinkPrice(size)));
                });

        int sizeChoice = getValidMenuChoice(1, sizes.length, "Enter choice: ") - 1;

        String[] drinkFlavors = {
                "Cola", "Sprite", "Orange", "Water", "Coffee",
                "Tea", "Lemonade", "Root Beer", "Other (Custom)"
        };

        System.out.println("\nSelect drink flavor:");

        // Use stream to display flavor options
        Arrays.stream(drinkFlavors)
                .forEach(flavor -> {
                    int index = Arrays.asList(drinkFlavors).indexOf(flavor) + 1;
                    System.out.println(index + ") " + flavor);
                });

        int flavorChoice = getValidMenuChoice(1, drinkFlavors.length, "Enter choice: ") - 1;

        // Use ternary operator with stream operations
        String flavor = (flavorChoice == drinkFlavors.length - 1)
                ? getStringInput("Enter custom drink flavor: ")
                : Arrays.stream(drinkFlavors)
                .skip(flavorChoice)
                .findFirst()
                .orElse("Cola");

        DrinkSize selectedSize = Arrays.stream(sizes)
                .skip(sizeChoice)
                .findFirst()
                .orElse(DrinkSize.SMALL);

        System.out.println("Selected: " + selectedSize.getDisplayName() + " " + flavor);
        System.out.println("Price: $" + String.format("%.2f", PriceCalculator.getDrinkPrice(selectedSize)));

        return new Drink(selectedSize, flavor);
    }

    public static Chips buildChips() {
        System.out.println("\n========== Add Chips ==========");

        String[] chipVarieties = {
                "Classic", "BBQ", "Sour Cream & Onion", "Salt & Vinegar",
                "Cheddar", "Jalapeño", "Honey Mustard", "Other (Custom)"
        };

        System.out.println("Select chip variety:");

        // Use stream to display an array of objects as menu options
        Arrays.stream(chipVarieties)
                .forEach(variety -> {
                    int index = Arrays.asList(chipVarieties).indexOf(variety) + 1;
                    System.out.println(index + ") " + variety);
                });

        int chipChoice = getValidMenuChoice(1, chipVarieties.length, "Enter choice: ") - 1;

        // Use ternary operator and stream operations
        String chipType = (chipChoice == chipVarieties.length - 1)
                ? getStringInput("Enter custom chip type: ")
                : Arrays.stream(chipVarieties)
                .skip(chipChoice)
                .findFirst()
                .orElse("Classic");

        System.out.println("Selected: " + chipType);
        System.out.println("Price: $" + String.format("%.2f", PriceCalculator.getChipsPrice()));
        return new Chips(chipType);
    }

    public static void displayOrderSummary(Order order) {
        System.out.println("\n========== Order Summary ==========");

        if (order.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        // Display sandwiches using stream with index
        order.getSandwiches().stream()
                .forEach(sandwich -> {
                    int index = order.getSandwiches().indexOf(sandwich) + 1;
                    System.out.println("\nSandwich #" + index + ":");
                    System.out.println("  Size: " + sandwich.getSize().getDisplayName());
                    System.out.println("  Bread: " + sandwich.getBreadType().getDisplayName());

                    // Display meats with stream
                    if (!sandwich.getMeats().isEmpty()) {
                        String meats = sandwich.getMeats().stream()
                                .map(meat -> meat.getName() + (meat.isExtra() ? " (Extra)" : ""))
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("");
                        System.out.println("  Meats: " + meats);
                    }

                    // Display cheeses with stream
                    if (!sandwich.getCheeses().isEmpty()) {
                        String cheeses = sandwich.getCheeses().stream()
                                .map(cheese -> cheese.getName() + (cheese.isExtra() ? " (Extra)" : ""))
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("");
                        System.out.println("  Cheeses: " + cheeses);
                    }

                    // Display regular toppings with stream
                    if (!sandwich.getRegularToppings().isEmpty()) {
                        String toppings = sandwich.getRegularToppings().stream()
                                .map(RegularTopping::getName)
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("");
                        System.out.println("  Toppings: " + toppings);
                    }

                    // Display sauces with stream
                    if (!sandwich.getSauces().isEmpty()) {
                        String sauces = sandwich.getSauces().stream()
                                .map(Sauce::getName)
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("");
                        System.out.println("  Sauces: " + sauces);
                    }

                    // Display sides with stream
                    if (!sandwich.getSides().isEmpty()) {
                        String sides = sandwich.getSides().stream()
                                .map(Side::getName)
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("");
                        System.out.println("  Sides: " + sides);
                    }

                    if (sandwich.isToasted()) {
                        System.out.println("  Toasted: Yes");
                    }

                    System.out.println("  Price: $" + String.format("%.2f", sandwich.calculatePrice()));
                });

        // Display drinks with stream
        order.getDrinks().stream()
                .forEach(drink -> {
                    System.out.println("\nDrink: " + drink.getSize().getDisplayName() + " " + drink.getFlavor());
                    System.out.println("  Price: $" + String.format("%.2f", drink.calculatePrice()));
                });

        // Display chips with stream
        order.getChips().stream()
                .forEach(chip -> {
                    System.out.println("\nChips: " + chip.getChipType());
                    System.out.println("  Price: $" + String.format("%.2f", chip.calculatePrice()));
                });

        System.out.println("\n" + "=".repeat(35));
        System.out.println("Total: $" + String.format("%.2f", order.calculateTotal()));
        System.out.println("=".repeat(35));
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