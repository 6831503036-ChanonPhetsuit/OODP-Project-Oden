public class Seafood extends MenuItem {
    public Seafood(String name, double price, int pieces) {
        super(name, price, pieces);
    }

    @Override
    public double getPrice() {
        return price * 1.1; // +10% premium
    }

    public String getCategory() {
        return "Seafood";
    }
}