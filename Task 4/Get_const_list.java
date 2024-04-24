import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

class ShoppingListManager {
    private HashMap<String, List<String>> productList;

    ShoppingListManager() {
        productList = new HashMap<>();
    }

    static public void printSeparator() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
    }

    public int compare(ShoppingListManager compareList, String category, String product) {
        for (String key : compareList.getProductList().keySet()) {
            for (int i = 0; i < productList.get(key).size(); i++) {
                String currentProduct = productList.get(key).get(i);
                if (currentProduct.equals(product)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public void addProduct(String category, String product) {
        if (productList.containsKey(category)) {
            productList.get(category).add(product);
        } else {
            if (product == null) {
                addOnlyCategories(category);
            } else {
                addOnlyCategories(category);
                productList.get(category).add(product);
            }
        }
    }

    public void addOnlyCategories(String category) {
        productList.put(category, new ArrayList<>());
    }

    public void addAllCategories(ShoppingListManager shoppingList) {
        for (String key : shoppingList.getProductList().keySet()) {
            addOnlyCategories(key);
        }
    }

    public void printOnlyCategory() {
        int i = 0;
        System.out.println("AVAILABLE CATEGORIES:");
        for (String key : productList.keySet()) {
            System.out.print(key + ",");
            i++;
        }
        if (i == 0) {
            System.out.println("NO AVAILABLE CATEGORIES");
            System.out.println();
            printSeparator();
        }
        System.out.println();
        printSeparator();
        System.out.println();
    }

    public void printOnlyProducts(String category) {
        int i;
        for (i = 0; i < productList.get(category).size(); i++) {
            if (i == 0) {
                System.out.print("PRODUCTS FOR :\"" + category + "\"\n");
            }
            System.out.print(i + 1 + "." + productList.get(category).get(i) + "  ");
        }
        if (i == 0) {
            System.out.print("NO PRODUCTS FOR :\"" + category + "\"");
        }
    }

    public void printAllListOfProducts() {
        int i = 0;
        for (String key : productList.keySet()) {
            printOnlyProducts(key);
            System.out.println();
            i++;
        }
        if (i == 0) {
            System.out.println("NO AVAILABLE CATEGORIES");
        }
    }

    public void deleteProducts(String category) {
        while (!productList.get(category).isEmpty()) {
            String product = productList.get(category).get(0);
            productList.get(category).remove(product);
        }
    }

    public void deleteAllProducts() {
        for (String key : productList.keySet()) {
            deleteProducts(key);
        }
    }

    public void deleteOnlyOneProduct(String category, String product) {
        productList.get(category).remove(product);
    }

    public int save(String fileName) {
        File file;
        try {
            file = new File(fileName);
        } catch (NullPointerException e) {
            System.out.println("File error");
            return 1;
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file");
            return 1;
        }
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file");
            return 1;
        }
        List<String> keys = List.copyOf(productList.keySet());
        for (int i = productList.size() - 1; i >= 0; i--) {
            String category = keys.get(i);
            try {
                writer.write(category + "\n");
            } catch (IOException e) {
                System.out.println("Writing error");
                return 1;
            }
            for (int j = 0; j < productList.get(category).size(); j++) {
                String product = productList.get(category).get(j);
                if (product == null) continue;
                try {
                    writer.write("\t" + product + "\n");
                } catch (IOException e) {
                    System.out.println("Writing error");
                    return 1;
                }
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("File closing error");
            return 1;
        }
        return 0;
    }

    public HashMap<String, List<String>> getProductList() {
        return productList;
    }

    public int loadProducts(String fileName) {
        FileReader file;
        try {
            file = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            return 1;
        }
        BufferedReader reader = new BufferedReader(file);
        String category;
        try {
            category = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading error");
            return 1;
        }
        addProduct(category, null);
        String line;
        while (category != null) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("Reading error");
                return 1;
            }
            if (line != null && line.startsWith(" ")) {
                String withoutSpaces = line.replaceAll("\\s+", " ").trim();
                addProduct(category, withoutSpaces);
            } else if (line != null && line.startsWith("\t")) {
                String withoutTabs = line.replaceAll("\t", "");
                addProduct(category, withoutTabs);
            } else {
                if (line != null && Objects.equals(line, fileName)) break;
                if (line == null) break;
                category = line;
                addProduct(category, null);
            }
        }
        return 0;
    }
}