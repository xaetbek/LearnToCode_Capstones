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
            System.out.println("\n╔════════════════════╗");
            System.out.println("║     LEDGER MENU    ║");
            System.out.println("╠════════════════════╣");
            System.out.println("║ A) All             ║");
            System.out.println("║ D) Deposits        ║");
            System.out.println("║ P) Payments        ║");
            System.out.println("║ R) Reports         ║");
            System.out.println("║ H) Home            ║");
            System.out.println("╚════════════════════╝");
            System.out.print("➤ Choose an option: ");
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
        List<String[]> ledgerTransactions = new ArrayList<>();

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
                    ledgerTransactions.add(parts);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
            return;
        }

        // Reverse to show most recent transactions first
        Collections.reverse(ledgerTransactions);

        // Print the filtered transactions
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("                          LEDGER: " + filterType + "             "     );
        System.out.println("┣━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━━┫");
        System.out.println("┃    DATE    ┃   TIME   ┃   DESCRIPTION   ┃     VENDOR   ┃   AMOUNT  ┃");
        System.out.println("┗━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━━┛");

        // Print transactions with perfect alignment
        for (String[] parts : ledgerTransactions) {
            System.out.printf(
                    "┃ %-10s ┃ %-8s ┃ %-15s ┃ %-12s ┃ %9s ┃\n",
                    parts[0], parts[1], parts[2], parts[3], parts[4]
            );
        }

        printLedgerFooter();
    }

    /**
     * Prints consistent footer matching Reports class style
     */
    private static void printLedgerFooter() {
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.println(); // Extra spacing
    }
}