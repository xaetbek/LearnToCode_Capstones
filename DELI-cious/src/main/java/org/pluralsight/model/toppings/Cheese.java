package org.pluralsight.model.toppings;

import org.pluralsight.model.enums.CheeseType;
import org.pluralsight.model.enums.SandwichSize;
import org.pluralsight.service.PriceCalculator;

/**
 * Represents a cheese topping on a sandwich
 */
public class Cheese extends Topping {
    private CheeseType cheeseType;
    private boolean isExtra;

    public Cheese(CheeseType cheeseType, boolean isExtra) {
        super(cheeseType.getDisplayName());
        this.cheeseType = cheeseType;
        this.isExtra = isExtra;
    }

    @Override
    public double calculatePrice(SandwichSize size, boolean isExtra) {
        return PriceCalculator.getCheesePrice(size, isExtra);
    }

    public boolean isExtra() {
        return isExtra;
    }

    public CheeseType getCheeseType() {
        return cheeseType;
    }
}