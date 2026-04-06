public abstract class MenuItem {
    protected String name;
    protected double price;
    protected int pieces;

    public MenuItem(String name, double price, int pieces) {
        this.name = name;
        this.price = price;
        this.pieces = pieces;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getPieces() { return pieces; }

    public abstract String getCategory();

    @Override
    public String toString() {
        return "[" + getCategory() + "] " + name + " (" + pieces + " pcs) - " + getPrice() + " Baht";
    }
}