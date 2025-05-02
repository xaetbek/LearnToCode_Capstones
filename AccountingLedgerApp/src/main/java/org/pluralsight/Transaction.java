package org.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a financial transaction with date, time, description,
 * vendor, and amount information. This class serves as the core data model
 * for the accounting ledger system.
 */
public class Transaction {
    // Fields with detailed comments
    private LocalDate date;        // The date when the transaction occurred (YYYY-MM-DD)
    private LocalTime time;        // The time when the transaction occurred (HH:MM:SS)
    private String description;    // Brief explanation of the transaction (max 15 chars)
    private String vendor;         // The entity involved in the transaction (max 10 chars)
    private double amount;         // Transaction amount (positive for deposits, negative for payments)

    /**
     * Constructs a Transaction with specified details.
     *
     * @param date        The date of the transaction (LocalDate)
     * @param time        The time of the transaction (LocalTime)
     * @param description Brief description of the transaction
     * @param vendor      The vendor or party involved
     * @param amount      The monetary amount (positive/negative for deposits/payments)
     */
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    /**
     * Default constructor creates a transaction with:
     * - Current date and time
     * - "default" description
     * - "user" as vendor
     * - 0.0 amount
     * Useful for creating placeholder transactions.
     */
    public Transaction() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.description = "default";
        this.vendor = "user";
        this.amount = 0.0;
    }

    // Getter and Setter methods with brief purpose descriptions

    /** @return The transaction date */
    public LocalDate getDate() {
        return date;
    }

    /** @param date The date to set (LocalDate) */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /** @return The transaction time */
    public LocalTime getTime() {
        return time;
    }

    /** @param time The time to set (LocalTime) */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * @return The transaction description
     * (trimmed to 15 characters in actual usage)
     */
    public String getDescription() {
        return description;
    }

    /** @param description The description to set */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The vendor name
     * (trimmed to 10 characters in actual usage)
     */
    public String getVendor() {
        return vendor;
    }

    /** @param vendor The vendor name to set */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * @return The transaction amount
     * (positive for deposits, negative for payments)
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount The amount to set
     * (positive for deposits, negative for payments)
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}