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

    public void run() {
        loadTransactions();

        boolean running = true;
        while (running) {
            System.out.print("=== Home Screen ===" +
                    "\nD) Add Deposit" +
                    "\nP) Make Payment (Debit)" +
                    "\nL) Ledger" +
                    "\nX) Exit" +
                    "\nChoose an option: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("D")) {
                addDeposit();
            } else if (input.equalsIgnoreCase("P")) {
                System.out.println("Make payment function coming soon");
            } else if (input.equalsIgnoreCase("L")) {
                System.out.println("Ledger coming soon ");
            }else if (input.equalsIgnoreCase("X")) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid command. Please choose D, P, L or X.\n");
            }
        }
    }


    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File("transactions.csv");

        if (!file.exists()) {
            // If the file doesn't exist, return an empty list instead of failing
            return transactions;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    try {
                        LocalDate date = LocalDate.parse(parts[0]);
                        LocalTime time = LocalTime.parse(parts[1]);
                        String description = parts[2];
                        String vendor = parts[3];
                        double amount = Double.parseDouble(parts[4]);

                        Transaction t = new Transaction(date, time, description, vendor, amount);
                        transactions.add(t);
                    } catch (Exception e) {
                        // Skip malformed line
                        System.out.println("Skipping bad line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading transactions.csv", e);
        }
        return transactions;
    }

    // Save a single transaction to the CSV file
    public static void writeTransactionToCsv(String vendor, String description, double amount) {
        LocalDate transactionDate = LocalDate.now();
        LocalTime transactionTime = LocalTime.now();
        try {
            FileWriter fileWriter = new FileWriter("transactions.csv" , true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            File file = new File("transactions.csv");
            // Check if the file is empty
            if (!file.exists() || file.length() == 0) {
                // Write the header if the file is empty
                writer.write("Date|Time|Description|Vendor|Amount\n");
            }
            // Write the transaction details
            String formattedDate = transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String formattedTime = transactionTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            writer.append(formattedDate).append("|").append(formattedTime).append("|").append(description).append("|").append(vendor).append("|").append(String.valueOf(amount)).append("\n");
            writer.close();

            if (amount > 0) {
                System.out.println("Deposit: $" + amount + " | Vendor: " + vendor + " | Description: " + "'" + description + "'" + " | Status: Added\n");
            } else {
                System.out.println("Payment: $" + Math.abs(amount) + " | Vendor: " + vendor + " | Description: "  + "'" + description + "'" + " | Status: Payment Made\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing transactions to CSV: " + e.getMessage());
        }
    }

    public static void addDeposit() {
        System.out.println("Enter the amount to deposit:");
        System.out.print("> ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        scanner.nextLine();
        System.out.println("Enter a description for the deposit:");
        System.out.print("> ");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor name for the deposit:");
        System.out.print("> ");
        String vendor = scanner.nextLine();

        writeTransactionToCsv(vendor, description, amount);

    }
}
