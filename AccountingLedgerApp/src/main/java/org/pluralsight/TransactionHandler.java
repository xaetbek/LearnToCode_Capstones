package org.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionHandler {
    private static Scanner scanner = new Scanner(System.in);
    private static final String fileName = "transactions.csv";

    public void run() {
        loadTransactionStrings();

        boolean running = true;
        while (running) {
            System.out.print("--- Home Screen ---" +
                    "\nD) Add Deposit" +
                    "\nP) Make Payment (Debit)" +
                    "\nL) Ledger" +
                    "\nX) Exit" +
                    "\nChoose an option: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("D")) {
                addDeposit();
            } else if (input.equalsIgnoreCase("P")) {
                makePayment();
            } else if (input.equalsIgnoreCase("L")) {
                Ledger.showLedgerMenu();
            }else if (input.equalsIgnoreCase("X")) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid command. Please choose D, P, L or X.\n");
            }
        }
    }

    // Convert transactions list to String
    public static List<String> loadTransactionStrings() {
        List<String> transactions = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return transactions;  // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip header line
                if (line.toLowerCase().startsWith("---date---|--time--|--description--|---vendor---|--amount--")) {
                    continue;
                }
                transactions.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
        return transactions;
    }


    public static void addDeposit() {
        Scanner scanner = new Scanner(System.in);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);

        String formattedDate = date.toString();
        String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println("\n--- Add Deposit ---");

        // Get and format description
        System.out.print("Enter description (max 15 chars): ");
        String description = scanner.nextLine();
        description = formatDescription(description);  // Format to exactly 15 chars

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        vendor = formatVendor(vendor);

        // Amount validation with loop
        double amount;
        while (true) {
            System.out.print("Enter amount (positive number): ");
            String amountStr = scanner.nextLine();

            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    System.out.println("Amount must be positive. Try again.");
                    continue;  // Restart the loop
                }
                break;  // Exit loop if valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number (e.g. 25.50).");
            }
        }

        String transaction = String.format("%s|%s|%s|%s|%.2f",
                formattedDate, formattedTime, description, vendor, amount);

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transaction + "\n");
            System.out.println("Deposit added successfully.\n");
        } catch (IOException e) {
            System.out.println("Failed to write to file: \n" + e.getMessage());
        }
    }

    public static void makePayment() {
        Scanner scanner = new Scanner(System.in);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);

        String formattedDate = date.toString(); // e.g., 2025-04-29
        String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println("\n--- Make Payment ---");
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        description = formatDescription(description);

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        vendor = formatVendor(vendor);

        // Amount validation with loop
        double amount;
        while (true) {
            System.out.print("Enter amount (positive number): ");
            String amountStr = scanner.nextLine();

            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    System.out.println("Amount must be positive. Try again.");
                    continue;  // Restart the loop
                }
                break;  // Exit loop if valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number (e.g. 25.50).");
            }
        }

        // Store payment as negative
        amount = -Math.abs(amount);

        String transaction = String.format("%s|%s|%s|%s|%.2f", formattedDate, formattedTime, description, vendor, amount);

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transaction + "\n");
            System.out.println("Payment recorded successfully.\n");
        } catch (IOException e) {
            System.out.println("Failed to write to file: \n" + e.getMessage());
        }
    }

    // Helper method to format description
    private static String formatDescription(String input) {
        // Trim if longer than 15 characters
        if (input.length() > 15) {
            return input.substring(0, 15);
        }
        // Pad with spaces if shorter than 15 characters
        return String.format("%-15s", input);
    }
    // Helper method to format vendor
    private static String formatVendor(String input) {
        // Trim if longer than 10 characters
        if (input.length() > 12) {
            return input.substring(0, 12);
        }
        // Pad with spaces if shorter than 12 characters
        return String.format("%-12s", input);
    }
}
