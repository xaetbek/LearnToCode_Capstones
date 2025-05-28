package org.pluralsight.model.products;

import org.pluralsight.service.PriceCalculator;

/**
 * Represents a bag of chips
 */
public class Chips {
    private String chipType;

    public Chips(String chipType) {
        this.chipType = chipType;
    }

    /**
     * Calculate the price of the chips
     * @return The price of the chips (fixed price)
     */
    public double calculatePrice() {
        return PriceCalculator.getChipsPrice();
    }

    // Getter
    public String getChipType() { return chipType; }
}