package org.pluralsight.model.enums;

/**
 * Enum representing the available regular topping types
 */
public enum RegularToppingType {
    LETTUCE("Lettuce"),
    PEPPERS("Peppers"),
    ONIONS("Onions"),
    TOMATOES("Tomatoes"),
    JALAPENOS("Jalapeños"),
    CUCUMBERS("Cucumbers"),
    PICKLES("Pickles"),
    GUACAMOLE("Guacamole"),
    MUSHROOMS("Mushrooms");

    private final String displayName;

    RegularToppingType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}