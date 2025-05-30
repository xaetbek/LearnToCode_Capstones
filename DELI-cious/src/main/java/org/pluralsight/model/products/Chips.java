package org.pluralsight.model.products;

import org.pluralsight.service.PriceCalculator;

// Represents a bag of chips
public class Chips extends AbstractMenuItem{
    private String chipType;

    public Chips(String chipType) {
        super(chipType + " Chips", PriceCalculator.getChipsPrice());
        this.chipType = chipType;
    }

    // Calculate the price of the chips
    public double calculatePrice() {
        return PriceCalculator.getChipsPrice();
    }

    // Getter
    public String getChipType() { return chipType; }
}