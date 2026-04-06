public class OrderItem implements Printable {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotal() {
        return item.getPrice() * quantity;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    public String toString() {
        return item.getName() + " x" + quantity + " (" + (item.getPieces()*quantity) + " pcs) = " + getTotal();
    }
}