package org.pluralsight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionHandler {
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

    // Save a single transaction to the file
    public static void writeTransaction() {

    }
}
