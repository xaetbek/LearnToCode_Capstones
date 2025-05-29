package org.pluralsight.service;

import org.pluralsight.model.enums.DrinkSize;
import org.pluralsight.model.enums.SandwichSize;

// Utility class for calculating prices of various products
public class PriceCalculator {

    // Get the base price of a sandwich based on its size
    public static double getSandwichBasePrice(SandwichSize size) {
        switch (size) {
            case FOUR_INCH: return 5.50;
            case EIGHT_INCH: return 7.00;
            case TWELVE_INCH: return 8.50;
            default: return 0.0;
        }
    }

    // Get the price of meat topping based on sandwich size and whether it's extra
    public static double getMeatPrice(SandwichSize size, boolean isExtra) {
        double basePrice;
        double extraPrice;

        switch (size) {
            case FOUR_INCH:
                basePrice = 1.00;
                extraPrice = 0.50;
                break;
            case EIGHT_INCH:
                basePrice = 2.00;
                extraPrice = 1.00;
                break;
            case TWELVE_INCH:
                basePrice = 3.00;
                extraPrice = 1.50;
                break;
            default:
                basePrice = 0.0;
                extraPrice = 0.0;
        }

        // If extra, return base price + extra price, otherwise just base price
        return isExtra ? (basePrice + extraPrice) : basePrice;
    }

    // Get the price of cheese topping based on sandwich size and whether it's extra
    public static double getCheesePrice(SandwichSize size, boolean isExtra) {
        double basePrice;
        double extraPrice;

        switch (size) {
            case FOUR_INCH:
                basePrice = 0.75;
                extraPrice = 0.30;
                break;
            case EIGHT_INCH:
                basePrice = 1.50;
                extraPrice = 0.60;
                break;
            case TWELVE_INCH:
                basePrice = 2.25;
                extraPrice = 0.90;
                break;
            default:
                basePrice = 0.0;
                extraPrice = 0.0;
        }

        // If extra, return base price + extra price, otherwise just base price
        return isExtra ? (basePrice + extraPrice) : basePrice;
    }

    // Get the price of a drink based on its size
    public static double getDrinkPrice(DrinkSize size) {
        switch (size) {
            case SMALL: return 2.00;
            case MEDIUM: return 2.50;
            case LARGE: return 3.00;
            default: return 0.0;
        }
    }

    // Get the price of chips (fixed price)
    public static double getChipsPrice() {
        return 1.50;
    }
}