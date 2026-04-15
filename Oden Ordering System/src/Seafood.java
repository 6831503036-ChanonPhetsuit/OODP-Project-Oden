// INHERITANCE: Extends MenuItem abstract class - specializes behavior for seafood products
public class Seafood extends MenuItem {
    // INHERITANCE: Constructor calls parent MenuItem constructor via super()
    public Seafood(String name, double price, int pieces) {
        super(name, price, pieces);
    }

    // PARAMETRIC POLYMORPHISM: Overrides getPrice() method from MenuItem
    // Implements price premium for seafood products (+10% markup)
    @Override
    public double getPrice() {
        return price * 1.1;
    }

    // INHERITANCE/POLYMORPHIC IMPLEMENTATION: Overrides abstract getCategory() from MenuItem
    // Returns specific category identifier for Seafood products
    public String getCategory() {
        return "Seafood";
    }
}