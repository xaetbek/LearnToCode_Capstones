package org.pluralsight.model.toppings;

import org.pluralsight.model.enums.RegularToppingType;
import org.pluralsight.model.enums.SandwichSize;

/**
 * Represents a regular topping on a sandwich (vegetables, etc.)
 * Regular toppings are included in the sandwich price
 */
public class RegularTopping extends Topping {
    private RegularToppingType toppingType;

    public RegularTopping(RegularToppingType toppingType) {
        super(toppingType.getDisplayName());
        this.toppingType = toppingType;
    }

    @Override
    public double calculatePrice(SandwichSize size, boolean isExtra) {
        return 0.0; // Regular toppings are included
    }

    public RegularToppingType getToppingType() {
        return toppingType;
    }
}