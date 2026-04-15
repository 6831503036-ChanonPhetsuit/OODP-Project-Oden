// INHERITANCE: Extends MenuItem abstract class - specializes behavior for meat products
public class Meat extends MenuItem {
    // INHERITANCE: Constructor calls parent MenuItem constructor via super()
    public Meat(String name, double price, int pieces) {
        super(name, price, pieces);
    }

    // INHERITANCE/POLYMORPHIC IMPLEMENTATION: Overrides abstract getCategory() method from MenuItem
    // Returns specific category identifier for Meat products
    public String getCategory() {
        return "Meat";
    }
}