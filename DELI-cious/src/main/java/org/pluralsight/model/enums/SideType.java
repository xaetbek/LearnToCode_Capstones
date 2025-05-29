package org.pluralsight.model.enums;

/**
 * Enum representing the available side types
 */
public enum SideType {
    COLESLAW("Coleslaw"),
    SAUCE("Sauce");

    private final String displayName;

    SideType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}