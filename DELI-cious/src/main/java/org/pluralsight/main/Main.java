package org.pluralsight.main;

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
     */
    public static void main(String[] args) {
            // Initialize and start the Order Management System
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.start();

    }
}