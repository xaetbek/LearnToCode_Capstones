package org.pluralsight.model.enums;

/**
 * Enum representing the available sauce types
 */
public enum SauceType {
    MAYO("Mayo"),
    MUSTARD("Mustard"),
    KETCHUP("Ketchup"),
    RANCH("Ranch"),
    THOUSAND_ISLANDS("Thousand Islands"),
    VINAIGRETTE("Vinaigrette");

    private final String displayName;

    SauceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}