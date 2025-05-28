package org.pluralsight.model.enums;

/**
 * Enum representing the available drink sizes
 */
public enum DrinkSize {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String displayName;

    DrinkSize(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}