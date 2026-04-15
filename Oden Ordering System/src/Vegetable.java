// INHERITANCE: Extends MenuItem abstract class - specializes behavior for vegetable products
public class Vegetable extends MenuItem {
    // INHERITANCE: Constructor calls parent MenuItem constructor via super()
    public Vegetable(String name, double price, int pieces) {
        super(name, price, pieces);
    }

    // PARAMETRIC POLYMORPHISM: Overrides getPrice() method from MenuItem
    // Implements price discount for vegetable products (-10% discount)
    @Override
    public double getPrice() {
        return price * 0.9;
    }

    // INHERITANCE/POLYMORPHIC IMPLEMENTATION: Overrides abstract getCategory() from MenuItem
    // Returns specific category identifier for Vegetable products
    public String getCategory() {
        return "Vegetable";
    }
}