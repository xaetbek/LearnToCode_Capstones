package org.pluralsight.main;

import org.pluralsight.service.OrderManager;
import org.pluralsight.ui.HomeScreen;

/**
 * Main application class for DELI-cious Point of Sale System
 * Entry point for the application
 *
 * @author Khayotbek Azimov
 * @version 1.0
 * @since 2025
 */
public class Main {
    /**
     * Main method - entry point of the application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Initialize and start the Order Management System
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.start();

        } catch (Exception e) {
            System.err.println("An unexpected error occurred while starting the application:");
            System.err.println(e.getMessage());
            System.err.println("Please restart the application.");
        }
    }
}