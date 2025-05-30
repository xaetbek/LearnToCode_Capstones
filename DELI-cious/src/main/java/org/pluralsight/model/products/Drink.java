package org.pluralsight.model.products;

import org.pluralsight.model.enums.DrinkSize;
import org.pluralsight.service.PriceCalculator;

// Represents a drink product
public class Drink extends AbstractMenuItem{
    private DrinkSize size;
    private String flavor;

    public Drink(DrinkSize size, String flavor) {
        super(size.getDisplayName() + " " + flavor, PriceCalculator.getDrinkPrice(size));
        this.size = size;
        this.flavor = flavor;
    }

    // Calculate the price of the drink based on its size and return the cost
    public double calculatePrice() {
        return PriceCalculator.getDrinkPrice(size);
    }

    // Getters
    public DrinkSize getSize() { return size; }
    public String getFlavor() { return flavor; }
}