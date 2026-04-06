public class Meat extends MenuItem {
    public Meat(String name, double price, int pieces) {
        super(name, price, pieces);
    }

    public String getCategory() {
        return "Meat";
    }
}