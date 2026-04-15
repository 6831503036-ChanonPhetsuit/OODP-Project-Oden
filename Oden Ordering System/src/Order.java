import java.util.*;

// Order: Manages collection of OrderItems for a customer purchase
public class Order {
    // COLLECTION WITH GENERICS: List<OrderItem> stores typed collection of order items
    // Generic type ensures type safety - only OrderItem objects can be added
    private List<OrderItem> items = new ArrayList<>();

    // CUSTOM EXCEPTION: Throws InvalidOrderException when quantity validation fails
    // EXCEPTION THROWING: Enforces business rule - quantity must be positive
    // COLLECTION OPERATION: Adds new OrderItem to collection if validation passes
    public void addItem(MenuItem item, int amount) throws InvalidOrderException {
        if (amount <= 0) throw new InvalidOrderException("Amount must be > 0");
        items.add(new OrderItem(item, amount));
    }

    // COLLECTION OPERATION: Clears all items from the order
    public void clear() {
        items.clear();
    }

    // COLLECTION OPERATION: Removes specific item by index with bounds checking
    public void remove(int index) {
        if(index >= 0 && index < items.size()) items.remove(index);
    }

    // COLLECTION GETTER: Returns the collection of OrderItems
    // COLLECTION WITH GENERICS: Returns List<OrderItem> for client iteration
    public List<OrderItem> getItems() {
        return items;
    }

    // COLLECTION CHECK: Verifies if order contains any items
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // ITERATING OVER COLLECTION: Uses enhanced for-loop to traverse List<OrderItem>
    // Calculates total price by summing all item totals with polymorphic pricing
    public double getTotal() {
        double sum = 0;
        for(OrderItem i : items) sum += i.getTotal();
        return sum;
    }
}