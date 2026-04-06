public class Vegetable extends MenuItem {
    public Vegetable(String name, double price, int pieces) {
        super(name, price, pieces);
    }

    @Override
    public double getPrice() {
        return price * 0.9; // ลด 10%
    }

    public String getCategory() {
        return "Vegetable";
    }
}