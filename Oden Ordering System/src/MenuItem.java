// ABSTRACT CLASS & INHERITANCE: Base class for all menu item categories (Meat, Seafood, Vegetable)
// Defines common properties and behavior for food items
public abstract class MenuItem {
    // INHERITANCE: Protected fields accessible to subclasses (Meat, Seafood, Vegetable)
    protected String name;
    protected double price;
    protected int pieces;

    // CONSTRUCTOR: Initializes MenuItem properties for all subclass instances
    public MenuItem(String name, double price, int pieces) {
        this.name = name;
        this.price = price;
        this.pieces = pieces;
    }

    // GETTER METHODS: Accessor methods for protected fields
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getPieces() { return pieces; }

    // ABSTRACT METHOD: Must be implemented by subclasses (Meat, Seafood, Vegetable)
    // Enables polymorphic behavior - each subclass returns its category name
    public abstract String getCategory();

    // PARAMETRIC POLYMORPHISM: Calls abstract getCategory() method
    // Each subclass provides its own category implementation
    @Override
    public String toString() {
        return "[" + getCategory() + "] " + name + " (" + pieces + " pcs) - " + getPrice() + " Baht";
    }
}