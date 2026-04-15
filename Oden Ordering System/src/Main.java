import java.util.*;

// Main: Entry point for the Oden Ordering System application
public class Main {

    // INPUT FROM KEYBOARD: Scanner object for reading user input from console
    // GLOBAL VARIABLE: Shared across all methods for continuous I/O operations
    static Scanner sc = new Scanner(System.in);
    
    // COLLECTION WITH GENERICS: List<MenuItem> stores typed collection of menu items
    // Generic type ensures only MenuItem objects can be stored
    static List<MenuItem> menu;
    
    // AGGREGATION: Order object maintains current customer's purchase
    static Order order = new Order();

    // APPLICATION ENTRY POINT: Main method - initializes menu and runs main menu loop
    // FILE I/O: Loads menu data from text file using FileManager
    // COLLECTION WITH GENERICS: Receives List<MenuItem> from file loading operation
    public static void main(String[] args) {
        // FILE READ: Loads menu from external file - initializes List<MenuItem>
        menu = FileManager.loadMenu("Oden Ordering System/src/menu.txt");

        // MAIN APPLICATION LOOP: Continuous menu until user exits
        while (true) {
            // OUTPUT TO KEYBOARD: Display main menu options
            System.out.println("\n===== ODEN SHOP =====");
            System.out.println("1. Order Food");
            System.out.println("2. View Order");
            System.out.println("3. Checkout");
            System.out.println("0. Exit");

            // INPUT FROM KEYBOARD: Read integer choice from user
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            // CONTROL FLOW: Route to appropriate function based on user choice
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

    // FUNCTION: Handles food ordering menu - allows user to select items from menu
    // COLLECTION WITH GENERICS: Works with List<MenuItem> to display and access menu items
    // INPUT FROM KEYBOARD: Reads user menu selections and quantities
    // CUSTOM EXCEPTION HANDLING: Catches InvalidOrderException from Order.addItem()
    static void orderFood() {
        // NULL & EMPTY CHECKS: Validates menu is loaded and contains items
        if (menu == null || menu.isEmpty()) {
            System.out.println("Menu not available");
            return;
        }

        while (true) {
            // OUTPUT TO KEYBOARD: Display menu options with indexed list
            System.out.println("\n===== MENU =====");
            
            // COLLECTION ITERATION: Enhanced for-loop with index to display List<MenuItem>
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i));
            }
            System.out.println("0. Back to Main Menu");

            // INPUT FROM KEYBOARD: Read integer selection from user
            System.out.print("Choose: ");
            int c = sc.nextInt();
            
            // NAVIGATION: Allow user to return to main menu
            if (c == 0) {
                System.out.println("Returning to Main Menu...");
                break; 
            }
            
            // INPUT VALIDATION: Check menu selection is within bounds
            if (c < 1 || c > menu.size()) {
                System.out.println("Invalid choice");
                continue;
            }

            // INPUT FROM KEYBOARD: Read quantity from user
            System.out.print("Amount: ");
            int q = sc.nextInt();
            
            // CUSTOM EXCEPTION HANDLING: Catches InvalidOrderException thrown by Order.addItem()
            // When quantity is invalid (<= 0), exception is caught and error shown
            try {
                // POLYMORPHISM: menu.get(c-1) returns MenuItem (actual type: Meat, Seafood, or Vegetable)
                order.addItem(menu.get(c - 1), q);
                System.out.println(">> Added " + menu.get(c - 1).getName() + " x" + q + " to your order! <<");
            } catch (InvalidOrderException e) {
                // EXCEPTION MESSAGE DISPLAY: Shows custom error message from exception
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // FUNCTION: Displays current order items and allows modification (remove/clear)
    // COLLECTION WITH GENERICS: Works with List<OrderItem> to display and manipulate items
    // INPUT FROM KEYBOARD: Reads user selection for item removal
    static void viewOrder() {
        // NULL CHECK: Validates order has items before displaying
        if (order.isEmpty()) {
            System.out.println("Empty");
            return;
        }

        // LOOP: Keeps user in viewOrder menu until they choose to go back
        while (true) {
            // OUTPUT TO KEYBOARD: Shows order header
            System.out.println("===== YOUR ORDER =====");

            // COLLECTION WITH GENERICS: Retrieves List<OrderItem> from Order
            List<OrderItem> items = order.getItems();
            
            // COLLECTION ITERATION: Displays all items with index
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }

            // CALCULATION & OUTPUT: Display total price of all items with polymorphic pricing
            System.out.println("Total: " + order.getTotal() + " Baht");

            // OUTPUT TO KEYBOARD: Show order management options
            System.out.println("1. Clear All");
            System.out.println("2. Remove One");
            System.out.println("0. Back");

            // INPUT FROM KEYBOARD: Read user action choice
            System.out.print("Choose: ");
            int c = sc.nextInt();

            // CLEAR ALL ITEMS: Empties entire order with confirmation
            if (c == 1) {
                // CHECK IF ORDER EMPTY: Show message if no items to clear
                if (order.isEmpty()) {
                    System.out.println("Menu is Empty");
                    continue;
                }
                
                // CONFIRMATION DIALOG: Ask user to confirm clearing all items
                System.out.println("Are you sure to remove all items?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Choose: ");
                int confirm = sc.nextInt();
                
                // CONDITIONAL: Process confirmation response
                if (confirm == 1) {
                    order.clear();
                    System.out.println("All items removed");
                } else if (confirm == 2) {
                    System.out.println("Cancelled");
                } else {
                    System.out.println("Invalid choice");
                }
            } 
            // REMOVE SINGLE ITEM: Removes specific item by user-entered index with quantity
            else if (c == 2) {
                // CHECK IF ORDER EMPTY: Show message if no items to remove
                if (order.isEmpty()) {
                    System.out.println("Menu is Empty");
                    continue;
                }
                
                // INPUT FROM KEYBOARD: Get index of item to remove
                System.out.print("Index: ");
                int idx = sc.nextInt();

                // INPUT VALIDATION: Check index is within bounds
                if (idx < 1 || idx > items.size()) {
                    System.out.println("Invalid choice");
                    continue;
                }

                // GET SELECTED ITEM: Retrieve the item at the specified index
                OrderItem selectedItem = items.get(idx - 1);
                
                // AUTO REMOVE: If quantity is 1, remove entire item automatically
                if (selectedItem.getQuantity() == 1) {
                    System.out.println(">> Removed " + selectedItem.toString() + " <<");
                    order.remove(idx - 1);
                    System.out.println("Item removed");
                } 
                // QUANTITY REMOVAL: If quantity > 1, ask how many to remove
                else {
                    System.out.println("Current quantity: " + selectedItem.getQuantity());
                    System.out.print("How many to remove: ");
                    
                    // INPUT VALIDATION: Handle non-numeric input
                    int removeAmount;
                    try {
                        removeAmount = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid choice");
                        sc.nextLine(); // Clear the invalid input
                        continue;
                    }
                    
                    // VALIDATION: Check if remove quantity is valid
                    if (removeAmount <= 0 || removeAmount > selectedItem.getQuantity()) {
                        System.out.println("Invalid choice");
                        continue;
                    }
                    
                    // REMOVE ENTIRE ITEM: If removing exactly the available quantity
                    if (removeAmount == selectedItem.getQuantity()) {
                        System.out.println(">> Removed " + selectedItem.toString() + " <<");
                        order.remove(idx - 1);
                        System.out.println("Item removed");
                    } 
                    // REDUCE QUANTITY: Remove specified amount from item
                    else {
                        System.out.println(">> Removed " + removeAmount + " x " + selectedItem.getItem().getName() + " <<");
                        selectedItem.reduceQuantity(removeAmount);
                        System.out.println("Quantity updated");
                    }
                }
            }
            // NAVIGATION: Allow user to return to main menu
            else if (c == 0) {
                System.out.println("Returning to Main Menu...");
                break;
            }
            // INPUT VALIDATION: Invalid menu choice - stay in viewOrder menu
            else {
                System.out.println("Invalid choice");
            }
        }
    }

    // FUNCTION: Completes purchase - displays receipt and saves to file
    // COLLECTION WITH GENERICS: Iterates over List<OrderItem> for display
    // FILE I/O: Saves receipt items to text file using FileManager
    // INTERFACE USAGE: Calls Printable.print() on each OrderItem
    static void checkout() {
        // NULL CHECK: Validates order contains items before checkout
        if (order.isEmpty()) {
            System.out.println("No order");
            return;
        }

        // LOOP: Keep user in checkout menu until they make a decision
        while (true) {
            // OUTPUT TO KEYBOARD: Display order header
            System.out.println("===== YOUR ORDER =====");

            // COLLECTION ITERATION: Enhanced for-loop to iterate List<OrderItem>
            // INTERFACE IMPLEMENTATION: Calls print() method on each OrderItem (implements Printable)
            for (OrderItem i : order.getItems()) {
                i.print();
            }

            // CALCULATION: Compute total price with polymorphic pricing for each item type
            double total = order.getTotal();
            // OUTPUT TO KEYBOARD: Display total cost
            System.out.println("Total: " + total + " Baht");

            // OUTPUT TO KEYBOARD: Show checkout options
            System.out.println("1. Checkout");
            System.out.println("2. Cancel Order");
            System.out.println("0. Back");

            // INPUT FROM KEYBOARD: Read user choice
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            // CHECKOUT: Process the order
            if (choice == 1) {
                // OUTPUT TO KEYBOARD: Display receipt header
                System.out.println("===== RECEIPT =====");

                // COLLECTION ITERATION: Show final receipt
                for (OrderItem i : order.getItems()) {
                    i.print();
                }
                System.out.println("Total: " + total + " Baht");

                // GENERATE UNIQUE ID: Creates random 6-digit order ID
                int id = (int)(Math.random() * 900000) + 100000;
                // OUTPUT TO KEYBOARD: Display order ID
                System.out.println("Order ID: " + id);

                // FILE I/O (WRITE): Saves order items to receipt file using FileManager
                // PARAMETRIC POLYMORPHISM: saveList<T>() generic method handles List<OrderItem>
                // FILE NAME: Includes order ID in filename for tracking
                FileManager.saveList(order.getItems(), "receipt_" + id + ".txt");

                // COLLECTION OPERATION: Clear order after successful checkout
                order.clear();
                // OUTPUT TO KEYBOARD: Confirm order completion
                System.out.println("Order completed!");
                break;
            }
            // CANCEL ORDER: Cancel the entire order
            else if (choice == 2) {
                order.clear();
                System.out.println("Order Canceled");
                break;
            }
            // NAVIGATION: Return to main menu
            else if (choice == 0) {
                System.out.println("Returning to Main Menu...");
                break;
            }
            // INPUT VALIDATION: Invalid choice - stay in checkout menu
            else {
                System.out.println("Invalid choice");
            }
        }
    }
}