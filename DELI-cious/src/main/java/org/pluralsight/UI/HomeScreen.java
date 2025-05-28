package org.pluralsight.UI;

import java.util.Scanner;

public class HomeScreen {

    public static void display() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to DELI-cious!");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    OrderScreen.display();
                    break;
                case "0":
                    System.out.println("Thank you for visiting DELI-cious!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
