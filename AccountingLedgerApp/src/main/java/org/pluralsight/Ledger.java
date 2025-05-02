package org.pluralsight;
import java.io.*;
import java.util.*;

public class Ledger {

    /**
     * Displays the ledger menu and handles user navigation
     * Allows viewing transactions by type (All/Deposits/Payments) or accessing reports
     */
    public static void showLedgerMenu() {
        Scanner scanner = new Scanner(System.in);
        // Load transactions from file at startup
        List<String> transactions = TransactionHandler.loadTransactionStrings();
        boolean inLedger = true;

        // Main menu loop
        while (inLedger) {
            System.out.println("\n--- Ledger Menu ---");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            // Process user selection
            if (choice.equalsIgnoreCase("A")) {
                displayLedger("ALL");
            } else if (choice.equalsIgnoreCase("D")) {
                displayLedger("DEPOSITS");
            } else if (choice.equalsIgnoreCase("P")) {
                displayLedger("PAYMENTS");
            } else if (choice.equalsIgnoreCase("R")) {
                Reports.showReportMenu(transactions);
            } else if (choice.equalsIgnoreCase("H")) {
                inLedger = false; // Exit to home
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays transactions filtered by type
     * @param filterType The type of transactions to show (ALL, DEPOSITS, PAYMENTS)
     */
    public static void displayLedger(String filterType) {
        List<String> lines = new ArrayList<>();

        // Read transactions from CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                // Skip the header line if present
                if (line.toLowerCase().startsWith("---date---|--time--|--description--|---vendor---|--amount--")) {
                    continue;
                }

                // Split transaction into components
                String[] parts = line.split("\\|");
                if (parts.length != 5) continue; // Skip malformed lines

                double amount = Double.parseDouble(parts[4]);

                // Determine if transaction should be shown based on filter
                boolean show = switch (filterType) {
                    case "DEPOSITS" -> amount > 0;  // Positive amounts only
                    case "PAYMENTS" -> amount < 0;  // Negative amounts only
                    case "ALL" -> true;             // All transactions
                    default -> false;
                };

                if (show) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
            return;
        }

        // Reverse to show most recent transactions first
        Collections.reverse(lines);

        // Print the filtered transactions
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("                     LEDGER: " + filterType + "            ");
        System.out.println("┣━━━━━━━━━┳━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━┳━━━━━━━━━┫");
        System.out.println("┃   DATE  ┃  TIME  ┃  DESCRIPTION  ┃   VENDOR   ┃  AMOUNT ┃");
        System.out.println("┗━━━━━━━━━┻━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━┻━━━━━━━━━┛");
        for (String entry : lines) {
            System.out.println(entry);
        }
    }
}