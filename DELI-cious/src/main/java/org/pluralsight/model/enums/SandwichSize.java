package org.pluralsight.model.enums;

/**
 * Enum representing the available sandwich sizes
 */
public enum SandwichSize {
    FOUR_INCH("4\""),
    EIGHT_INCH("8\""),
    TWELVE_INCH("12\"");

    private final String displayName;

    SandwichSize(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}