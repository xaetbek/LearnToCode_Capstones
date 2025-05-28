package org.pluralsight.service;

import org.pluralsight.model.enums.DrinkSize;
import org.pluralsight.model.enums.SandwichSize;

/**
 * Utility class for calculating prices of various products
 */
public class PriceCalculator {

    /**
     * Get the base price of a sandwich based on its size
     * @param size The sandwich size
     * @return The base price for the sandwich
     */
    public static double getSandwichBasePrice(SandwichSize size) {
        switch (size) {
            case FOUR_INCH: return 5.50;
            case EIGHT_INCH: return 7.00;
            case TWELVE_INCH: return 8.50;
            default: return 0.0;
        }
    }

    /**
     * Get the price of meat topping based on sandwich size and whether it's extra
     * @param size The sandwich size
     * @param isExtra Whether this is an extra portion
     * @return The price for the meat topping
     */
    public static double getMeatPrice(SandwichSize size, boolean isExtra) {
        double basePrice;
        switch (size) {
            case FOUR_INCH:
                basePrice = isExtra ? 0.50 : 1.00;
                break;
            case EIGHT_INCH:
                basePrice = isExtra ? 1.00 : 2.00;
                break;
            case TWELVE_INCH:
                basePrice = isExtra ? 1.50 : 3.00;
                break;
            default:
                basePrice = 0.0;
        }
        return basePrice;
    }

    /**
     * Get the price of cheese topping based on sandwich size and whether it's extra
     * @param size The sandwich size
     * @param isExtra Whether this is an extra portion
     * @return The price for the cheese topping
     */
    public static double getCheesePrice(SandwichSize size, boolean isExtra) {
        double basePrice;
        switch (size) {
            case FOUR_INCH:
                basePrice = isExtra ? 0.30 : 0.75;
                break;
            case EIGHT_INCH:
                basePrice = isExtra ? 0.60 : 1.50;
                break;
            case TWELVE_INCH:
                basePrice = isExtra ? 0.90 : 2.25;
                break;
            default:
                basePrice = 0.0;
        }
        return basePrice;
    }

    /**
     * Get the price of a drink based on its size
     * @param size The drink size
     * @return The price for the drink
     */
    public static double getDrinkPrice(DrinkSize size) {
        switch (size) {
            case SMALL: return 2.00;
            case MEDIUM: return 2.50;
            case LARGE: return 3.00;
            default: return 0.0;
        }
    }

    /**
     * Get the price of chips (fixed price)
     * @return The price for chips
     */
    public static double getChipsPrice() {
        return 1.50;
    }
}