import java.io.*;
import java.util.*;

public class FileManager {

    public static <T> void saveList(List<T> list, String filename) {
        try(PrintWriter pw = new PrintWriter(filename)) {
            for(T item : list) {
                pw.println(item.toString());
            }
        } catch(Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

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