package org.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String FILE_NAME = "inventory.csv";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What do you want to do?\n");
            System.out.println("D - Add Deposit");
            System.out.println("P - Make Payment (Debit)");
            System.out.println("L - Ledger");
            System.out.println("X - Exit");
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            switch (input) {
                case "D":
                    System.out.println("Add deposit coming soon");;
                    break;
                case "P":
                    System.out.print("Make payment function coming soon ");
                    break;
                case "L":
                    System.out.print("Ledger coming soon ");
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid command. Please choose D, P, L or X.");
            }
        }

    }
}