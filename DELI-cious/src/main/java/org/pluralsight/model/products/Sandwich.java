package org.pluralsight.model.products;

import org.pluralsight.model.enums.*;
import org.pluralsight.model.toppings.*;
import org.pluralsight.service.PriceCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Sandwich extends AbstractMenuItem (no Customizable interface needed)
public class Sandwich extends AbstractMenuItem {
    private SandwichSize size;
    private BreadType breadType;
    private List<Meat> meats;
    private List<Cheese> cheeses;
    private List<RegularTopping> regularToppings;
    private List<Sauce> sauces;
    private List<Side> sides;
    private boolean isToasted;

    public Sandwich(SandwichSize size, BreadType breadType) {
        // Call parent constructor
        super(size.getDisplayName() + " " + breadType.getDisplayName() + " Sandwich",
                PriceCalculator.getSandwichBasePrice(size));

        this.size = size;
        this.breadType = breadType;
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.isToasted = false;
    }

    // Implements abstract method from AbstractMenuItem
    @Override
    public double calculatePrice() {
        double meatTotal = meats.stream()
                .mapToDouble(meat -> meat.calculatePrice(size, meat.isExtra()))
                .sum();

        double cheeseTotal = cheeses.stream()
                .mapToDouble(cheese -> cheese.calculatePrice(size, cheese.isExtra()))
                .sum();

        return basePrice + meatTotal + cheeseTotal;
    }

    public void addMeat(MeatType meatType, boolean isExtra) {
        meats.add(new Meat(meatType, isExtra));
    }

    public void addCheese(CheeseType cheeseType, boolean isExtra) {
        cheeses.add(new Cheese(cheeseType, isExtra));
    }

    public void addRegularTopping(RegularToppingType toppingType) {
        regularToppings.add(new RegularTopping(toppingType));
    }

    public void addSauce(SauceType sauceType) {
        sauces.add(new Sauce(sauceType));
    }

    public void addSide(SideType sideType) {
        sides.add(new Side(sideType));
    }

    public void setToasted(boolean toasted) {
        this.isToasted = toasted;
    }

    // All your existing getters stay the same
    public SandwichSize getSize() { return size; }
    public BreadType getBreadType() { return breadType; }
    public List<Meat> getMeats() { return new ArrayList<>(meats); }
    public List<Cheese> getCheeses() { return new ArrayList<>(cheeses); }
    public List<RegularTopping> getRegularToppings() { return new ArrayList<>(regularToppings); }
    public List<Sauce> getSauces() { return new ArrayList<>(sauces); }
    public List<Side> getSides() { return new ArrayList<>(sides); }
    public boolean isToasted() { return isToasted; }
}