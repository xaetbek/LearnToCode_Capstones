package org.pluralsight.model.toppings;

import org.pluralsight.model.enums.SideType;
import org.pluralsight.model.enums.SandwichSize;

/**
 * Represents a side topping on a sandwich (au jus, sauce)
 * Sides are included in the sandwich price
 */
public class Side extends Topping {
    private SideType sideType;

    public Side(SideType sideType) {
        super(sideType.getDisplayName());
        this.sideType = sideType;
    }

    @Override
    public double calculatePrice(SandwichSize size, boolean isExtra) {
        return 0.0; // Sides are included
    }

    public SideType getSideType() {
        return sideType;
    }
}