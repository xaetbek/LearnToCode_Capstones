package org.pluralsight;

/**
 * Entry point for the Accounting Ledger Application.
 * This class initializes and launches the transaction handling system.
 */
public class Main {

    /**
     * Main method - The starting point of the application.
     *
     * @param args Command-line arguments (not currently used in this application)
     */
    public static void main(String[] args) {
        // Display welcome message to the user
        System.out.println("\nWelcome to Accounting Ledger App");

        // Initialize the transaction handler which contains the main application logic
        TransactionHandler ledgerApp = new TransactionHandler();

        // Start the application's main processing loop
        ledgerApp.run();
    }
}