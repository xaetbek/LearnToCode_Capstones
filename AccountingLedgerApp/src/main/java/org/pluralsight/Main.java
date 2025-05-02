package org.pluralsight;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nWelcome to Accounting Ledger App");

        TransactionHandler ledgerApp = new TransactionHandler();

        ledgerApp.run();
    }
}