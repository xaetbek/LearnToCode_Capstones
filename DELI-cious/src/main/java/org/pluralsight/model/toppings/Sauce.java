package org.pluralsight.model.toppings;

import org.pluralsight.model.enums.SauceType;
import org.pluralsight.model.enums.SandwichSize;

/**
 * Represents a sauce topping on a sandwich
 * Sauces are included in the sandwich price
 */
public class Sauce extends Topping {
    private SauceType sauceType;

    public Sauce(SauceType sauceType) {
        super(sauceType.getDisplayName());
        this.sauceType = sauceType;
    }

    @Override
    public double calculatePrice(SandwichSize size, boolean isExtra) {
        return 0.0; // Sauces are included
    }

    public SauceType getSauceType() {
        return sauceType;
    }
}