package org.pluralsight.model.toppings;

/**
 * Represents a topping added to a sandwich.
 * Toppings can be regular (free) or premium (meats, cheeses).
 * Premium toppings cost more and charge extra for additional quantities.
 */
public class Topping {
    public enum Category {
        MEAT, CHEESE, REGULAR, SAUCE, SIDE
    }

    private final String name;
    private final Category category;
    private final boolean isExtra; // true if this is an extra portion

    /**
     * Creates a topping with a name, category, and extra flag.
     *
     * @param name      Name of the topping (e.g., "lettuce", "mayo").
     * @param category  Category of the topping (MEAT, CHEESE, etc.).
     * @param isExtra   Whether this is an extra portion.
     */
    public Topping(String name, Category category, boolean isExtra) {
        this.name = name;
        this.category = category;
        this.isExtra = isExtra;
    }

    /**
     * Returns the topping name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the category of the topping.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Checks if the topping is an extra.
     */
    public boolean isExtra() {
        return isExtra;
    }

    /**
     * Calculates the cost of this topping based on the sandwich size.
     *
     * @param size The size of the sandwich (e.g., "4\"", "8\"", "12\"").
     * @return The price of the topping.
     */
    public double getPrice(String size) {
        double base = switch (category) {
            case MEAT -> switch (size) {
                case "4\"" -> 1.00;
                case "8\"" -> 2.00;
                case "12\"" -> 3.00;
                default -> 0.0;
            };
            case CHEESE -> switch (size) {
                case "4\"" -> 0.75;
                case "8\"" -> 1.50;
                case "12\"" -> 2.25;
                default -> 0.0;
            };
            default -> 0.0; // Regular, Sauce, Side are included
        };

        // Add cost if this is an "extra"
        if (isExtra) {
            base += switch (category) {
                case MEAT -> switch (size) {
                    case "4\"" -> 0.50;
                    case "8\"" -> 1.00;
                    case "12\"" -> 1.50;
                    default -> 0.0;
                };
                case CHEESE -> switch (size) {
                    case "4\"" -> 0.30;
                    case "8\"" -> 0.60;
                    case "12\"" -> 0.90;
                    default -> 0.0;
                };
                default -> 0.0;
            };
        }

        return base;
    }

    /**
     * String representation for display (e.g., "extra ").
     */
    @Override
    public String toString() {
        return (isExtra ? "extra " : "") + name;
    }
}
