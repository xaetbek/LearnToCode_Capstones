package org.pluralsight.UI;

import java.util.Scanner;

public class OrderScreen {

    public static void display() {
        Scanner scanner = new Scanner(System.in);
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
                    // TODO: Call AddSandwich logic
                    System.out.println("Adding sandwich...");
                    break;
                case "2":
                    // TODO: Call AddDrink logic
                    System.out.println("Adding drink...");
                    break;
                case "3":
                    // TODO: Call AddChips logic
                    System.out.println("Adding chips...");
                    break;
                case "4":
                    // TODO: Show checkout screen
                    System.out.println("Proceeding to checkout...");
                    return;
                case "0":
                    System.out.println("Order canceled. Returning to home screen.");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}

