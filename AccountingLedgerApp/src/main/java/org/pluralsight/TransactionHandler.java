package org.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all transaction-related operations including:
 * - Loading transactions from file
 * - Adding deposits and payments
 * - Formatting transaction data
 */
public class TransactionHandler {
    private static Scanner scanner = new Scanner(System.in);
    private static final String fileName = "transactions.csv"; // File where transactions are stored

    /**
     * Main application loop that displays the home menu and processes user input
     */

    public void run() {
        // Pre-load transactions when application starts
        loadTransactionStrings();

        boolean running = true;
        while (running) {
            // Display home screen options
            System.out.print("--- Home Screen ---" +
                    "\nD) Add Deposit" +
                    "\nP) Make Payment (Debit)" +
                    "\nL) Ledger" +
                    "\nX) Exit" +
                    "\nChoose an option: ");
            String input = scanner.nextLine();

            // Process user selection
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

    /**
     * Loads transactions from CSV file into a List of Strings
     * @return List of transaction strings in pipe-delimited format
     */
    public static List<String> loadTransactionStrings() {
        List<String> transactions = new ArrayList<>();
        File file = new File(fileName);

        // Return empty list if file doesn't exist yet
        if (!file.exists()) {
            return transactions;
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

    /**
     * Handles the deposit creation process:
     * - Gets transaction details from user
     * - Validates input
     * - Writes to transactions file
     */
    public static void addDeposit() {
        Scanner scanner = new Scanner(System.in);

        // Capture current date and time
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);  // Remove nanoseconds for cleaner format

        // Format date and time strings
        String formattedDate = date.toString();
        String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println("\n--- Add Deposit ---");

        // Get and format description (15 chars max)
        System.out.print("Enter description (max 15 chars): ");
        String description = scanner.nextLine();
        description = formatDescription(description);  // Format to exactly 15 chars

        // Get and format vendor name (12 chars max)
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        vendor = formatVendor(vendor);

        // Validate and get positive amount. Loop if invalid format entered
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

        // Write transaction to a file
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transaction + "\n");
            System.out.println("Deposit added successfully.\n");
        } catch (IOException e) {
            System.out.println("Failed to write to file: \n" + e.getMessage());
        }
    }

    /**
     * Handles the payment creation process:
     * - Gets transaction details from user
     * - Validates input
     * - Stores as negative amount
     * - Writes to transactions file
     */
    public static void makePayment() {
        Scanner scanner = new Scanner(System.in);

        // Capture current date and time
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);

        // Format date and time strings
        String formattedDate = date.toString(); // e.g., 2025-04-29
        String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Get and format description (15 chars max)
        System.out.println("\n--- Make Payment ---");
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        description = formatDescription(description);

        // Get and format vendor name (12 chars max)
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

        // Writes a payment transaction to the transactions file
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transaction + "\n");
            System.out.println("Payment recorded successfully.\n");
        } catch (IOException e) {
            System.out.println("Failed to write to file: \n" + e.getMessage());
        }
    }

    /**
     * Formats description to exactly 15 characters:
     * - Truncates if longer than 15 chars
     * - Pads with spaces if shorter than 15 chars
     * @param input Raw description input
     * @return Formatted 15-character string
     */
    private static String formatDescription(String input) {
        // Trim if longer than 15 characters
        if (input.length() > 15) {
            return input.substring(0, 15);
        }
        // Pad with spaces if shorter than 15 characters
        return String.format("%-15s", input);
    }

    /**
     * Formats vendor name to exactly 12 characters:
     * - Truncates if longer than 12 chars
     * - Pads with spaces if shorter than 12 chars
     * @param input Raw vendor input
     * @return Formatted 12-character string
     */
    private static String formatVendor(String input) {
        // Trim if longer than 10 characters
        if (input.length() > 12) {
            return input.substring(0, 12);
        }
        // Pad with spaces if shorter than 12 characters
        return String.format("%-12s", input);
    }
}
