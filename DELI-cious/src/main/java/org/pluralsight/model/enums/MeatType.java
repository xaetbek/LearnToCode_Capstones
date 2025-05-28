package org.pluralsight.model.enums;

/**
 * Enum representing the available meat types
 */
public enum MeatType {
    STEAK("Steak"),
    TURKEY("Turkey"),
    SALAMI("Salami"),
    ROAST_BEEF("Roast Beef"),
    CHICKEN("Chicken"),
    PASTRAMI("Pastrami");

    private final String displayName;

    MeatType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}