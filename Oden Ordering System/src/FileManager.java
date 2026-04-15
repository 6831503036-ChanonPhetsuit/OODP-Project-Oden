import java.io.*;
import java.util.*;

// FileManager: Handles all file operations for the ordering system (read/write menu and receipts)
public class FileManager {

    // PARAMETRIC POLYMORPHISM: Generic method <T> saves any type of List to file
    // TYPE PARAMETER <T>: Makes method reusable for different data types (List<OrderItem>, List<String>, etc.)
    // FILE I/O: Uses PrintWriter to write list items to file
    // EXCEPTION HANDLING: Catches general Exception during file write operations
    public static <T> void saveList(List<T> list, String filename) {
        try(PrintWriter pw = new PrintWriter(filename)) {
            for(T item : list) {
                pw.println(item.toString());
            }
        } catch(Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // FILE I/O: Loads menu items from a text file using BufferedReader
    // COLLECTION WITH GENERICS: Returns List<MenuItem> - collection of menu items
    // EXCEPTION HANDLING: Catches file not found and parsing exceptions
    // TRY-WITH-RESOURCES: Automatically closes BufferedReader and FileReader
    public static List<MenuItem> loadMenu(String filename) {
        List<MenuItem> menu = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Error: File '" + filename + "' not found at " + file.getAbsolutePath());
            return menu;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            menu = parseMenu(br);
        } catch(Exception e) {
            System.out.println("Error processing menu data: " + e.getMessage());
            e.printStackTrace();
        }
        return menu;
    }

    // FILE I/O: Loads menu items from an InputStream (supports file streams)
    // COLLECTION WITH GENERICS: Returns List<MenuItem> containing parsed menu items
    // EXCEPTION HANDLING: Catches parsing and I/O exceptions
    public static List<MenuItem> loadMenu(InputStream is) {
        List<MenuItem> menu = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            menu = parseMenu(br);
        } catch(Exception e) {
            System.out.println("Error processing menu data: " + e.getMessage());
            e.printStackTrace();
        }
        return menu;
    }

    // FILE I/O: Parses menu file line by line using BufferedReader
    // COLLECTION WITH GENERICS: Populates and returns List<MenuItem>
    // EXCEPTION HANDLING: Throws IOException for file read errors
    // INHERITANCE/POLYMORPHISM: Creates instances of Meat, Seafood, or Vegetable (subclasses of MenuItem)
    // Reads CSV format: type,name,price,pieces -> instantiates appropriate MenuItem subclass
    private static List<MenuItem> parseMenu(BufferedReader br) throws IOException {
        List<MenuItem> menu = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null) {
            line = line.trim();
    
            if (line.isEmpty() || line.startsWith("//")) continue;

            String[] parts = line.split(",");
            if (parts.length < 4) continue;

            String type = parts[0].trim();
            String name = parts[1].trim();
            double price = Double.parseDouble(parts[2].trim());
            int pieces = Integer.parseInt(parts[3].trim());

            switch(type) {
                case "Meat":
                    menu.add(new Meat(name,price,pieces));
                    break;
                case "Seafood":
                    menu.add(new Seafood(name,price,pieces));
                    break;
                case "Vegetable":
                    menu.add(new Vegetable(name,price,pieces));
                    break;
            }
        }
        return menu;
    }
}