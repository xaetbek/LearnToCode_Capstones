package org.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //empty constructor
    public Transaction() {
            this.date = LocalDate.now();
            this.time = LocalTime.now();
            this.description = "default";
            this.vendor = "user";
            this.amount = 0.0;
    }

    // returns a formatted string to display the transaction
    public String toString() {
        String formattedAmount = String.format("%,.2f", amount);
        String sign = amount < 0 ? "-$" : "$";
        return String.format(
                "Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: %s%s",
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                time.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                description,
                vendor,
                sign,
                Math.abs(amount)
        );
    }

    // returns a string in the format to save into CSV
    public String convertToCsvString() {
        return String.format(
                "%s|%s|%s|%s|%.2f",
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                time.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                description,
                vendor,
                amount
        );
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
