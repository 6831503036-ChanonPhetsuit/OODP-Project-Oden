import java.util.*;

public class Main {

    // Global variables
    static Scanner sc = new Scanner(System.in);
    static List<MenuItem> menu;
    static Order order = new Order();

    public static void main(String[] args) {

        // Load menu from file
        menu = FileManager.loadMenu("Oden Ordering System/src/menu.txt");

        while (true) {
            System.out.println("\n===== ODEN SHOP =====");
            System.out.println("1. Order Food");
            System.out.println("2. View Order");
            System.out.println("3. Checkout");
            System.out.println("0. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    orderFood();
                    break;
                case 2:
                    viewOrder();
                    break;
                case 3:
                    checkout();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

// =========================
    // 1. Order Food
    // =========================
    static void orderFood() {

        if (menu == null || menu.isEmpty()) {
            System.out.println("Menu not available");
            return;
        }

        while (true) {
            System.out.println("\n===== MENU =====");
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i));
            }
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose: ");
            int c = sc.nextInt();
            
            if (c == 0) {
                System.out.println("Returning to Main Menu...");
                break; 
            }
            
            if (c < 1 || c > menu.size()) {
                System.out.println("Invalid menu! Please try again.");
                continue;
            }

            System.out.print("Qty: ");
            int q = sc.nextInt();
            
            try {
                order.addItem(menu.get(c - 1), q);
                System.out.println(">> Added " + menu.get(c - 1).getName() + " x" + q + " to your order! <<");
            } catch (InvalidOrderException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // =========================
    // 2. View Order
    // =========================
    static void viewOrder() {

        if (order.isEmpty()) {
            System.out.println("Empty");
            return;
        }

        System.out.println("===== YOUR ORDER =====");

        List<OrderItem> items = order.getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }

        System.out.println("1. Clear All");
        System.out.println("2. Remove One");
        System.out.println("0. Back");

        System.out.print("Choose: ");
        int c = sc.nextInt();

        if (c == 1) {
            order.clear();
            System.out.println("All items removed");
        } 
        else if (c == 2) {
            System.out.print("Index: ");
            int idx = sc.nextInt();

            if (idx < 1 || idx > items.size()) {
                System.out.println("Invalid index");
                return;
            }

            order.remove(idx - 1);
            System.out.println("Item removed");
        }
    }

    // =========================
    // 3. Checkout
    // =========================
    static void checkout() {

        if (order.isEmpty()) {
            System.out.println("No order");
            return;
        }

        System.out.println("===== RECEIPT =====");

        for (OrderItem i : order.getItems()) {
            i.print();
        }

        double total = order.getTotal();
        System.out.println("Total: " + total + " Baht");

        int id = (int)(Math.random() * 900000) + 100000;
        System.out.println("Order ID: " + id);

        // Save receipt to file
        FileManager.saveList(order.getItems(), "receipt_" + id + ".txt");

        order.clear();
        System.out.println("Order completed!");
    }
}