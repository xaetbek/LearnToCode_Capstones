package org.pluralsight.model.toppings;

import org.pluralsight.model.enums.SandwichSize;

/**
 * Abstract base class for all sandwich toppings
 */
public abstract class Topping {
    protected String name;

    public Topping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Calculate the price of this topping based on sandwich size and whether it's extra
     * @param size The sandwich size
     * @param isExtra Whether this is an extra portion
     * @return The price for this topping
     */
    public abstract double calculatePrice(SandwichSize size, boolean isExtra);
}