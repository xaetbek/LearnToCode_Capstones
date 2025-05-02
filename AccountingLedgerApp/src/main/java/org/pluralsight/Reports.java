package org.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Reports {
    public static void showReportMenu(List<String> transactions) {
        Scanner scanner = new Scanner(System.in);
        boolean inReports = true;

        while (inReports) {
            System.out.println("\n--- Reports Menu ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    generateMonthToDateReport(transactions);
                    break;
                case "2":
                    generatePreviousMonthReport(transactions);
                    break;
                case "3":
                    generateYearToDateReport(transactions);
                    break;
                case "4":
                    generatePreviousYearReport(transactions);
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine().trim();
                    searchByVendor(transactions, vendor);
                    break;
                case "0":
                    inReports = false;  // Exit report menu
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    public static void generateMonthToDateReport(List<String> transactions) {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        System.out.println("\n--- Month to Date Report ---");
        System.out.println("---date---|--time--|--description--|---vendor---|--amount--");

        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            if (date.isAfter(startOfMonth.minusDays(1))) {
                System.out.println(transaction);
            }
        }
    }

    public static void generatePreviousMonthReport(List<String> transactions) {
        LocalDate firstOfPreviousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate lastOfPreviousMonth = firstOfPreviousMonth.withDayOfMonth(firstOfPreviousMonth.lengthOfMonth());

        System.out.println("\n--- Previous Month Report ---");
        System.out.println("---date---|--time--|--description--|---vendor---|--amount--");
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            if ((date.isEqual(firstOfPreviousMonth) || date.isAfter(firstOfPreviousMonth)) &&
                    date.isBefore(lastOfPreviousMonth.plusDays(1))) {
                System.out.println(transaction);
            }
        }
    }

    public static void generateYearToDateReport(List<String> transactions) {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);

        System.out.println("\n--- Year to Date Report ---");
        System.out.println("---date---|--time--|--description--|---vendor---|--amount--");
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            if (date.isAfter(startOfYear.minusDays(1))) {
                System.out.println(transaction);
            }
        }
    }

    public static void generatePreviousYearReport(List<String> transactions) {
        LocalDate startOfLastYear = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate endOfLastYear = startOfLastYear.withDayOfYear(startOfLastYear.lengthOfYear());

        System.out.println("\n--- Previous Year Report ---");
        System.out.println("---date---|--time--|--description--|---vendor---|--amount--");
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            if ((date.isEqual(startOfLastYear) || date.isAfter(startOfLastYear)) &&
                    date.isBefore(endOfLastYear.plusDays(1))) {
                System.out.println(transaction);
            }
        }
    }

    public static void searchByVendor(List<String> transactions, String vendor) {
        System.out.println("\n--- Search by Vendor: " + vendor + " ---");
        System.out.println("---date---|--time--|--description--|---vendor---|--amount--");

        // Convert search term to lowercase once for efficiency
        String searchTerm = vendor.trim().toLowerCase();
        boolean found = false;

        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length >= 4) {  // Safety check
                // Get vendor name (trimmed and lowercase for comparison)
                String transactionVendor = parts[3].trim().toLowerCase();

                // Check if vendor contains the search term (partial match)
                if (transactionVendor.contains(searchTerm)) {
                    System.out.println(transaction);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No transactions found for vendor containing: " + vendor);
        }
    }
}

