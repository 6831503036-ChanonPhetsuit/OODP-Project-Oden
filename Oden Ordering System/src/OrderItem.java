// INTERFACE IMPLEMENTATION: Implements Printable interface - order items can be printed
// COMPOSITION: Aggregates MenuItem objects with quantity information
public class OrderItem implements Printable {
    // COMPOSITION: References MenuItem object - aggregates menu item data
    private MenuItem item;
    private int quantity;

    // CONSTRUCTOR: Associates a MenuItem with a quantity for the order
    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // GETTER: Returns the quantity of this order item
    public int getQuantity() {
        return quantity;
    }

    // GETTER: Returns the MenuItem associated with this order item
    public MenuItem getItem() {
        return item;
    }

    // QUANTITY MODIFICATION: Reduces quantity by specified amount
    // Returns true if quantity becomes 0 or less (item should be removed)
    public boolean reduceQuantity(int amount) {
        quantity -= amount;
        return quantity <= 0;
    }

    // CALCULATION: Computes total cost by multiplying item price (with polymorphic pricing) by quantity
    // Demonstrates polymorphism: getPrice() calls correct implementation for Meat, Seafood, or Vegetable
    public double getTotal() {
        return item.getPrice() * quantity;
    }

    // INTERFACE IMPLEMENTATION: Implements Printable.print() method
    // Prints order item details to console (output to keyboard)
    @Override
    public void print() {
        System.out.println(toString());
    }

    // STRING REPRESENTATION: Formats order item information for display
    // Uses MenuItem getters to format complete order details
    public String toString() {
        return item.getName() + " x" + quantity + " (" + (item.getPieces()*quantity) + " pcs) = " + getTotal();
    }
}