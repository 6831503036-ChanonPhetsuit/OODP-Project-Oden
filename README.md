# OODP-Project-Oden

## Project Overview
This project is a console-based Oden ordering system developed in Java. It allows users to browse a menu, add, remove, and modify items in their order, and complete the checkout process with receipt generation. The system is designed using Object-Oriented Programming (OOP) principles to ensure a well-structured, maintainable, and extensible architecture. It also reflects the concept of modern self-ordering systems commonly used in restaurants today.

**Purpose**: The main purpose of this project is to develop a practical food ordering system that can be applied to real-life scenarios. It simulates self-service ordering technologies such as kiosks and mobile ordering applications, where customers can independently select and manage their orders. Additionally, the project aims to demonstrate key OOP concepts including inheritance, polymorphism, encapsulation, and abstraction, along with the use of collections and file I/O to build a scalable and organized system.

**Motivation**: The motivation behind this project is to bridge theoretical OOP concepts with real-world applications, particularly in the context of modern restaurant technologies. Self-ordering systems are increasingly popular as they improve efficiency, reduce staff workload, and enhance customer convenience. This project was designed to reflect these real-world systems while also providing hands-on experience in designing software that is modular, reusable, and adaptable to future changes, such as adding new menu categories or modifying pricing rules.

**Problem Statement**: The system is required to effectively manage the food ordering process with the following capabilities:

   -Load menu data from a file and categorize items (Meat, Seafood, Vegetable)
   -Allow users to select items and input quantities with proper validation
   -Support order management functions such as adding, removing, and clearing items
   -Apply category-based pricing rules (Seafood +10%, Vegetable -10%, Meat base price)
   -Generate and display a receipt, create a unique order ID, and save order data to a file
   -Handle errors gracefully, including invalid input and file operation issues

The system uses a menu-driven interface with options to order food, view orders, checkout, or exit. It emphasizes type safety, reusability, and separation of concerns through OOP design.

## Description of Each Class/Function

Below is a description of each class in the project, including its purpose, key methods, and how it contributes to the overall system. The classes demonstrate various OOP concepts like inheritance (MenuItem hierarchy), polymorphism (pricing and printing), composition (OrderItem aggregates MenuItem), interfaces (Printable), generics (collections), and exception handling.

1. **Main.java** (Entry Point and User Interface Controller)
   - **Purpose**: Serves as the application's entry point and handles all user interactions via a console-based menu system. It manages the main application loop, user input/output, and delegates tasks to other classes.
   - **Key Methods**:
     - `main(String[] args)`: Initializes the menu by loading from a file, then runs an infinite loop displaying the main menu (Order Food, View Order, Checkout, Exit) and routing user choices.
     - `orderFood()`: Displays the menu, allows item selection and quantity input, adds items to the order with exception handling for invalid quantities.
     - `viewOrder()`: Shows the current order, total price, and options to clear all items, remove specific items/quantities, or return to the main menu.
     - `checkout()`: Displays the order for confirmation, handles checkout (generates receipt and order ID, saves to file), or allows order cancellation.
   - **OOP Concepts**: Uses composition (aggregates Order and List<MenuItem>), polymorphism (calls polymorphic methods like `toString()` and `getPrice()`), generics (List<MenuItem>), and exception handling (catches InvalidOrderException).

2. **MenuItem.java** (Abstract Base Class for Menu Items)
   - **Purpose**: Defines the common structure and behavior for all menu items, serving as the root of an inheritance hierarchy. It encapsulates shared properties like name, price, and pieces per item.
   - **Key Methods**:
     - Constructor `MenuItem(String name, double price, int pieces)`: Initializes the item's properties.
     - `getName()`, `getPrice()`, `getPieces()`: Getter methods for accessing properties.
     - `getCategory()`: Abstract method that subclasses must implement to return their category (e.g., "Meat").
     - `toString()`: Returns a formatted string representation, calling the polymorphic `getCategory()`.
   - **OOP Concepts**: Abstraction (abstract class and method), inheritance (base for Meat, Seafood, Vegetable), encapsulation (protected fields), polymorphism (abstract method override).

3. **Meat.java** (Subclass for Meat Items)
   - **Purpose**: Represents meat-based Oden ingredients (e.g., Quail Egg, Chicken Ball). It inherits base behavior from MenuItem with no special pricing adjustments.
   - **Key Methods**:
     - Constructor `Meat(String name, double price, int pieces)`: Calls super() to initialize via MenuItem.
     - `getCategory()`: Overrides to return "Meat".
   - **OOP Concepts**: Inheritance (extends MenuItem), polymorphism (overrides `getCategory()`).

4. **Seafood.java** (Subclass for Seafood Items with Pricing Markup)
   - **Purpose**: Represents seafood-based Oden ingredients (e.g., Fish Tofu, Octopus). Applies a 10% price increase to reflect premium pricing.
   - **Key Methods**:
     - Constructor `Seafood(String name, double price, int pieces)`: Calls super() to initialize via MenuItem.
     - `getPrice()`: Overrides to return `price * 1.1` (10% markup).
     - `getCategory()`: Overrides to return "Seafood".
   - **OOP Concepts**: Inheritance (extends MenuItem), polymorphism (overrides `getPrice()` and `getCategory()` for specialized behavior).

5. **Vegetable.java** (Subclass for Vegetable Items with Pricing Discount)
   - **Purpose**: Represents vegetable-based Oden ingredients (e.g., Daikon, Potato). Applies a 10% price discount to encourage selection.
   - **Key Methods**:
     - Constructor `Vegetable(String name, double price, int pieces)`: Calls super() to initialize via MenuItem.
     - `getPrice()`: Overrides to return `price * 0.9` (10% discount).
     - `getCategory()`: Overrides to return "Vegetable".
   - **OOP Concepts**: Inheritance (extends MenuItem), polymorphism (overrides `getPrice()` and `getCategory()` for specialized behavior).

6. **Order.java** (Manages the Collection of Order Items)
   - **Purpose**: Represents a customer's order as a collection of OrderItem objects. It handles adding, removing, and calculating totals for the order.
   - **Key Methods**:
     - `addItem(MenuItem item, int amount)`: Adds an item with quantity validation; throws InvalidOrderException for invalid amounts.
     - `clear()`: Removes all items.
     - `remove(int index)`: Removes an item by index with bounds checking.
     - `getItems()`: Returns the List<OrderItem>.
     - `isEmpty()`: Checks if the order has no items.
     - `getTotal()`: Iterates over items to sum totals using polymorphic pricing.
   - **OOP Concepts**: Composition (aggregates List<OrderItem>), generics (List<OrderItem>), exception throwing (custom InvalidOrderException), polymorphism (calls `getTotal()` on OrderItem).

7. **OrderItem.java** (Represents an Individual Order Item)
   - **Purpose**: Combines a MenuItem with a quantity, calculating totals and supporting printing. It implements the Printable interface for uniform output.
   - **Key Methods**:
     - Constructor `OrderItem(MenuItem item, int quantity)`: Associates an item with a quantity.
     - `getQuantity()`, `getItem()`: Getters for properties.
     - `reduceQuantity(int amount)`: Decreases quantity and indicates if it reaches zero.
     - `getTotal()`: Calculates cost using polymorphic `item.getPrice() * quantity`.
     - `print()`: Implements Printable to output the item details.
     - `toString()`: Formats the item for display (e.g., "Quail Egg x2 (8 pcs) = 50.0").
   - **OOP Concepts**: Composition (aggregates MenuItem), interfaces (implements Printable), polymorphism (uses MenuItem's polymorphic methods).

8. **Printable.java** (Interface for Printable Objects)
   - **Purpose**: Defines a contract for objects that can be printed to the console, enabling polymorphic printing across different types.
   - **Key Methods**:
     - `print()`: Abstract method that implementing classes must define (e.g., outputs to System.out).
   - **OOP Concepts**: Interfaces (contract for polymorphism), abstraction (no implementation, just signature).

9. **InvalidOrderException.java** (Custom Exception for Order Validation)
   - **Purpose**: Represents errors related to invalid order operations, such as non-positive quantities. It extends Exception for custom error messaging.
   - **Key Methods**:
     - Constructor `InvalidOrderException(String msg)`: Passes a custom message to the parent Exception.
   - **OOP Concepts**: Inheritance (extends Exception), exception handling (used for business rule validation).

10. **FileManager.java** (Handles File I/O Operations)
    - **Purpose**: Manages reading the menu from a file and saving receipts. It supports loading from files or input streams and uses generics for reusable saving.
    - **Key Methods**:
      - `saveList(List<T> list, String filename)`: Generic method to save any list to a file using PrintWriter.
      - `loadMenu(String filename)` / `loadMenu(InputStream is)`: Loads and parses menu data into List<MenuItem>, handling file existence and parsing errors.
      - `parseMenu(BufferedReader br)`: Private helper to parse CSV lines into MenuItem subclasses (Meat, Seafood, Vegetable).
    - **OOP Concepts**: Generics (parametric polymorphism in `saveList`), polymorphism (creates subclass instances based on type), exception handling (catches I/O and parsing errors), file I/O (BufferedReader, PrintWriter).

This design ensures the system is modular, extensible (e.g., adding new item categories), and robust, with clear separation between UI (Main), data models (MenuItem hierarchy, Order, OrderItem), utilities (FileManager), and error handling (InvalidOrderException, Printable). The menu.txt file contains sample data in CSV format (type,name,price,pieces) for loading.
