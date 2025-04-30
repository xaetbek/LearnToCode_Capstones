package org.pluralsight;
import java.io.*;
import java.util.*;

public class Ledger {

    public static void showLedgerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean inLedger = true;

        while (inLedger) {
            System.out.println("\n--- Ledger Menu ---");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("A")) {
                displayLedger("ALL");
            } else if (choice.equalsIgnoreCase("D")) {
                displayLedger("DEPOSIT");
            } else if (choice.equalsIgnoreCase("P")) {
                displayLedger("PAYMENT");
            } else if (choice.equalsIgnoreCase("R")) {
                //showReportMenu(); // you can move this later too
            } else if (choice.equalsIgnoreCase("H")) {
                inLedger = false;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void displayLedger(String filterType) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                // skip the header
                if (line.toLowerCase().startsWith("date|time|description|vendor|amount")) {
                    continue;
                }

                String[] parts = line.split("\\|");
                if (parts.length != 5) continue;

                double amount = Double.parseDouble(parts[4]);

                boolean show = switch (filterType) {
                    case "DEPOSITS" -> amount > 0;
                    case "PAYMENTS" -> amount < 0;
                    case "ALL" -> true;
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

        Collections.reverse(lines);

        System.out.println("\n--- Ledger: " + filterType + " ---");
        for (String entry : lines) {
            System.out.println(entry);
        }
    }
}
