package org.pluralsight.model.enums;

/**
 * Enum representing the available cheese types
 */
public enum CheeseType {
    AMERICAN("American"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    SWISS("Swiss");

    private final String displayName;

    CheeseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}