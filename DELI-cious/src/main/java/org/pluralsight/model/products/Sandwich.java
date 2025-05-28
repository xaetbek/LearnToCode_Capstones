package org.pluralsight.model.products;

import org.pluralsight.model.enums.*;
import org.pluralsight.model.toppings.*;
import org.pluralsight.service.PriceCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customizable sandwich
 */
public class Sandwich {
    private SandwichSize size;
    private BreadType breadType;
    private List<Meat> meats;
    private List<Cheese> cheeses;
    private List<RegularTopping> regularToppings;
    private List<Sauce> sauces;
    private List<Side> sides;
    private boolean isToasted;

    public Sandwich(SandwichSize size, BreadType breadType) {
        this.size = size;
        this.breadType = breadType;
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.isToasted = false;
    }

    /**
     * Add a meat topping to the sandwich
     * @param meatType The type of meat to add
     * @param isExtra Whether this is an extra portion
     */
    public void addMeat(MeatType meatType, boolean isExtra) {
        meats.add(new Meat(meatType, isExtra));
    }

    /**
     * Add a cheese topping to the sandwich
     * @param cheeseType The type of cheese to add
     * @param isExtra Whether this is an extra portion
     */
    public void addCheese(CheeseType cheeseType, boolean isExtra) {
        cheeses.add(new Cheese(cheeseType, isExtra));
    }

    /**
     * Add a regular topping to the sandwich
     * @param toppingType The type of regular topping to add
     */
    public void addRegularTopping(RegularToppingType toppingType) {
        regularToppings.add(new RegularTopping(toppingType));
    }

    /**
     * Add a sauce to the sandwich
     * @param sauceType The type of sauce to add
     */
    public void addSauce(SauceType sauceType) {
        sauces.add(new Sauce(sauceType));
    }

    /**
     * Add a side to the sandwich
     * @param sideType The type of side to add
     */
    public void addSide(SideType sideType) {
        sides.add(new Side(sideType));
    }

    /**
     * Set whether the sandwich should be toasted
     * @param toasted True if the sandwich should be toasted
     */
    public void setToasted(boolean toasted) {
        this.isToasted = toasted;
    }

    /**
     * Calculate the total price of the sandwich
     * @return The total price including base price and all toppings
     */
    public double calculatePrice() {
        double total = PriceCalculator.getSandwichBasePrice(size);

        // Add meat prices
        for (Meat meat : meats) {
            total += meat.calculatePrice(size, meat.isExtra());
        }

        // Add cheese prices
        for (Cheese cheese : cheeses) {
            total += cheese.calculatePrice(size, cheese.isExtra());
        }

        // Regular toppings, sauces, and sides are free
        return total;
    }

    // Getters
    public SandwichSize getSize() { return size; }
    public BreadType getBreadType() { return breadType; }
    public List<Meat> getMeats() { return meats; }
    public List<Cheese> getCheeses() { return cheeses; }
    public List<RegularTopping> getRegularToppings() { return regularToppings; }
    public List<Sauce> getSauces() { return sauces; }
    public List<Side> getSides() { return sides; }
    public boolean isToasted() { return isToasted; }
}