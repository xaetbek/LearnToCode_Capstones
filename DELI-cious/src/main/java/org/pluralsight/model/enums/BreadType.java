package org.pluralsight.model.enums;

/**
 * Enum representing the available bread types
 */
public enum BreadType {
    WHITE("White"),
    WHEAT("Wheat"),
    RYE("Rye"),
    WRAP("Wrap");

    private final String displayName;

    BreadType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}