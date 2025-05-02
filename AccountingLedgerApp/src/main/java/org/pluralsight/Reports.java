package org.pluralsight;

import java.time.LocalDate;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles financial report generation with various time periods and vendor searches
 * Features beautiful box-drawing UI elements for enhanced visual presentation
 */
public class Reports {

    // Common header format for all reports
    private static final String REPORT_HEADER =
            "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                    "                     %-35s       \n" +  // Title will be centered here
                    "┣━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━┫\n" +
                    "┃    DATE    ┃   TIME   ┃   DESCRIPTION   ┃    VENDOR    ┃  AMOUNT  ┃\n" +
                    "┗━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━┛";

    /**
     * Displays the interactive reports menu and handles user selections
     */
    public static void showReportMenu(List<String> transactions) {
        Scanner scanner = new Scanner(System.in);
        boolean inReports = true;

        while (inReports) {
            // Display menu options with consistent formatting
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║         REPORTS MENU         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1) Month To Date             ║");
            System.out.println("║ 2) Previous Month            ║");
            System.out.println("║ 3) Year To Date              ║");
            System.out.println("║ 4) Previous Year             ║");
            System.out.println("║ 5) Search by Vendor          ║");
            System.out.println("║ 0) Back                      ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("➤ Choose an option: ");

            String choice = scanner.nextLine().trim();

            // Process user selection
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
                    System.out.print("🔎 Enter vendor name: ");
                    String vendor = scanner.nextLine().trim();
                    searchByVendor(transactions, vendor);
                    break;
                case "0":
                    inReports = false;  // Exit report menu
                    break;
                default:
                    System.out.println("⚠️  Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * Generates report for transactions from the first day of current month to today
     * @param transactions List of all transactions to filter
     */
    public static void generateMonthToDateReport(List<String> transactions) {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        System.out.printf(REPORT_HEADER + "\n", "MONTH TO DATE REPORT");

        // Create temporary list to reverse order
        List<String> tempList = new ArrayList<>();

        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            if (date.isAfter(startOfMonth.minusDays(1))) {
                tempList.add(transaction);
            }
        }

        // Reverse to show newest first
        Collections.reverse(tempList);

        for (String transaction : tempList) {
            String[] parts = transaction.split("\\|");
            System.out.printf("┃ %-9s ┃ %-6s ┃ %-13s ┃ %-10s ┃ %8s ┃\n",
                    parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        printReportFooter();
    }

    /**
     * Generates report for transactions from the previous complete month
     * @param transactions List of all transactions to filter
     */
    public static void generatePreviousMonthReport(List<String> transactions) {
        LocalDate firstOfPreviousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate lastOfPreviousMonth = firstOfPreviousMonth.withDayOfMonth(firstOfPreviousMonth.lengthOfMonth());

        System.out.printf(REPORT_HEADER + "\n", "  PREVIOUS MONTH REPORT");

        List<String> tempList = new ArrayList<>();
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            if ((date.isEqual(firstOfPreviousMonth) || date.isAfter(firstOfPreviousMonth)) &&
                    date.isBefore(lastOfPreviousMonth.plusDays(1))) {
                tempList.add(transaction);
            }
        }

        Collections.reverse(tempList);

        for (String transaction : tempList) {
            String[] parts = transaction.split("\\|");
            System.out.printf("┃ %-9s ┃ %-6s ┃ %-13s ┃ %-10s ┃ %8s ┃\n",
                    parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        printReportFooter();
    }

    /**
     * Generates report for transactions from January 1st to today
     * @param transactions List of all transactions to filter
     */
    public static void generateYearToDateReport(List<String> transactions) {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
        System.out.printf(REPORT_HEADER + "\n", "   YEAR TO DATE REPORT");

        // Create a temporary list before reverse
        List<String> tempList = new ArrayList<>();

        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            if (date.isAfter(startOfYear.minusDays(1))) {
                tempList.add(transaction);
            }
        }

        Collections.reverse(tempList);

        for (String transaction : tempList) {
            String[] parts = transaction.split("\\|");
            System.out.printf("┃ %-9s ┃ %-6s ┃ %-13s ┃ %-10s ┃ %8s ┃\n",
                    parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        printReportFooter();
    }

    /**
     * Generates report for transactions from the previous complete year
     * @param transactions List of all transactions to filter
     */
    public static void generatePreviousYearReport(List<String> transactions) {
        LocalDate startOfLastYear = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate endOfLastYear = startOfLastYear.withDayOfYear(startOfLastYear.lengthOfYear());

        System.out.printf(REPORT_HEADER + "\n", "  PREVIOUS YEAR REPORT");

        // Create a temporary list
        List<String> tempList = new ArrayList<>();

        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            if ((date.isEqual(startOfLastYear) || date.isAfter(startOfLastYear)) &&
                    date.isBefore(endOfLastYear.plusDays(1))) {
                tempList.add(transaction);
            }
        }

        Collections.reverse(tempList);

        for (String transaction : tempList) {
            String[] parts = transaction.split("\\|");
            System.out.printf("┃ %-9s ┃ %-6s ┃ %-13s ┃ %-10s ┃ %8s ┃\n",
                    parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        printReportFooter();
    }

    /**
     * Searches transactions by vendor name (case-insensitive partial match)
     * @param transactions List of transactions to search through
     * @param vendor Vendor name or partial name to search for
     */
    public static void searchByVendor(List<String> transactions, String vendor) {
        System.out.printf(REPORT_HEADER + "\n", "   VENDOR SEARCH: " + vendor.toUpperCase());

        String searchTerm = vendor.trim().toLowerCase();
        boolean found = false;

        // temporary list before reverse
        List<String> tempList = new ArrayList<>();
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length >= 4) {
                String transactionVendor = parts[3].trim().toLowerCase();
                if (transactionVendor.contains(searchTerm)) {
                    tempList.add(transaction);
                    found = true;
                }
            }
        }

        Collections.reverse(tempList);

        for (String transaction : tempList) {
            String[] parts = transaction.split("\\|");
            System.out.printf("┃ %-9s ┃ %-6s ┃ %-13s ┃ %-10s ┃ %8s ┃\n",
                    parts[0], parts[1], parts[2], parts[3], parts[4]);
            found = true;

        }

        if (!found) {
            System.out.println("\n⚠️  No transactions found for: " + vendor);
        }
        printReportFooter();
    }

    /**
     * Prints consistent footer for all reports
     */
    private static void printReportFooter() {
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.println();  // Add extra spacing after report
    }
}