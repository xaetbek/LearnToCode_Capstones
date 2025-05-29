package org.pluralsight.model.toppings;

import org.pluralsight.model.enums.MeatType;
import org.pluralsight.model.enums.SandwichSize;
import org.pluralsight.service.PriceCalculator;

/**
 * Represents a meat topping on a sandwich
 */
public class Meat extends Topping {
    private MeatType meatType;
    private boolean isExtra;

    public Meat(MeatType meatType, boolean isExtra) {
        super(meatType.getDisplayName());
        this.meatType = meatType;
        this.isExtra = isExtra;
    }

    @Override
    public double calculatePrice(SandwichSize size, boolean isExtra) {
        return PriceCalculator.getMeatPrice(size, isExtra);
    }

    public boolean isExtra() {
        return isExtra;
    }
}