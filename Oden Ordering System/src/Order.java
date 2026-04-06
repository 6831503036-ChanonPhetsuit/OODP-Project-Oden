import java.util.*;

public class Order {
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(MenuItem item, int qty) throws InvalidOrderException {
        if (qty <= 0) throw new InvalidOrderException("Quantity must be > 0");
        items.add(new OrderItem(item, qty));
    }

    public void clear() {
        items.clear();
    }

    public void remove(int index) {
        if(index >= 0 && index < items.size()) items.remove(index);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotal() {
        double sum = 0;
        for(OrderItem i : items) sum += i.getTotal();
        return sum;
    }
}