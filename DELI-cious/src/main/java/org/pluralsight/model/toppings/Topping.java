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
     * Abstract method to be implemented by concrete subclasses.
     * Calculate the price of this topping based on sandwich size and whether it's extra
     */
    public abstract double calculatePrice(SandwichSize size, boolean isExtra);
}