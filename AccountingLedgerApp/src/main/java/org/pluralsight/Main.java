package org.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to Accounting Ledger App");

        while (true) {
            System.out.println("\nD - Add Deposit");
            System.out.println("P - Make Payment (Debit)");
            System.out.println("L - Ledger");
            System.out.println("X - Exit");
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("D")) {
                System.out.println("Add deposit coming soon");
            } else if (input.equalsIgnoreCase("P")) {
                System.out.println("Make payment function coming soon");
            } else if (input.equalsIgnoreCase("L")) {
                System.out.println("Ledger coming soon ");
            }else if (input.equalsIgnoreCase("X")) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid command. Please choose D, P, L or X.");
            }
        }

    }
}