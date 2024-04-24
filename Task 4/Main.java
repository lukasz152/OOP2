import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String FIRST_NAME = "ok.txt";
    private static final String SECOND_NAME = "Lista_zakupow.txt";

    static public void printSeparator() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        ShoppingListManager constantProducts = new ShoppingListManager();
        int res = constantProducts.loadProducts(FIRST_NAME);
        if (res == 1) {
            System.out.println("File not found");
            return;
        }
        ShoppingListManager changingList = new ShoppingListManager();
        int res2 = changingList.loadProducts(SECOND_NAME);
        if (res2 == 1) {
            changingList.addAllCategories(constantProducts);
        }
        System.out.print("\nShopping List\n");
        while (true) {
            System.out.println("Options:\n1 -> Add a product to the shopping list\n2 -> Display all products from the shopping list\n3 -> Display all products from the shopping list for a specific category");
            System.out.println("4 -> Delete all products from the shopping list\n5 -> Delete all products from the shopping list for a specific category\n6 -> Delete a product from the shopping list (user selects a category, then a product)");
            System.out.println("7 -> Save to disk (End of program)");
            printSeparator();
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            int change;
            try {
                change = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("ENTER A NUMBER FROM [1-7]");
                continue;
            }
            if (change == 1) {
                constantProducts.printOnlyCategory();
                System.out.println("Enter the category to which you want to add a product");
                String category = scanner.nextLine();
                category = Character.toUpperCase(category.charAt(0)) + category.substring(1).toLowerCase();
                if (!constantProducts.getProductList().containsKey(category)) {
                    System.out.println("Category does not exist!");
                    continue;
                }
                System.out.print("AVAILABLE ");
                constantProducts.printOnlyProducts(category);
                System.out.println();
                printSeparator();
                System.out.println("\nEnter the name of the product to add:");
                String product = scanner.nextLine();
                product = Character.toUpperCase(product.charAt(0)) + product.substring(1).toLowerCase();
                if (constantProducts.compare(constantProducts, category, product) == 0) {
                    printSeparator();
                    System.out.println();
                    System.out.println("THE PRODUCT DOES NOT EXIST IN THE DEFAULT LIST!");
                    printSeparator();
                    System.out.println();
                    continue;
                }
                if (changingList.compare(changingList, category, product) == 1) {
                    printSeparator();
                    System.out.println();
                    System.out.println("THE PRODUCT ALREADY EXISTS");
                    printSeparator();
                    System.out.println();
                    continue;
                }
                changingList.addProduct(category, product);
                printSeparator();
                System.out.println();
                System.out.println("ADDED \"" + product + "\" TO \"" + category + "\"");
                printSeparator();
                System.out.println();
            } else if (change == 2) {
                printSeparator();
                System.out.println();
                changingList.printAllListOfProducts();
                System.out.println();
                printSeparator();
                System.out.println();
            } else if (change == 3) {
                System.out.println("Enter the category from which you want to display products:");
                constantProducts.printOnlyCategory();
                String category = scanner.nextLine();
                category = Character.toUpperCase(category.charAt(0)) + category.substring(1).toLowerCase();
                if (!changingList.getProductList().containsKey(category)) {
                    System.out.println("No products for the category " + category + "!");
                    continue;
                }
                changingList.printOnlyProducts(category);
                System.out.println();
                printSeparator();
                System.out.println();
            } else if (change == 4) {
                changingList.deleteAllProducts();
                printSeparator();
                System.out.println();
                System.out.println("DELETED ALL PRODUCTS!");
                printSeparator();
                System.out.println();
            } else if (change == 5) {
                System.out.println("Enter the category from which you want to delete products");
                constantProducts.printOnlyCategory();
                String category = scanner.nextLine();
                category = Character.toUpperCase(category.charAt(0)) + category.substring(1).toLowerCase();
                if (!changingList.getProductList().containsKey(category)) {
                    System.out.println("Category does not exist!");
                    continue;
                }
                printSeparator();
                System.out.println();
                changingList.deleteProducts(category);
                System.out.println("DELETED PRODUCTS FROM " + category);
                printSeparator();
                System.out.println();
            } else if (change == 6) {
                System.out.println("Enter the category from which you want to delete a product");
                changingList.printOnlyCategory();
                String category = scanner.nextLine();
                category = Character.toUpperCase(category.charAt(0)) + category.substring(1).toLowerCase();
                if (!changingList.getProductList().containsKey(category)) {
                    System.out.println("Category does not exist!");
                    continue;
                }
                if (changingList.getProductList().get(category).isEmpty()) {
                    printSeparator();
                    System.out.println();
                    System.out.print("NO PRODUCTS FOR " + category);
                    System.out.println();
                    printSeparator();
                    System.out.println();
                    continue;
                }
                System.out.println("Enter the product to delete:\n");
                changingList.printOnlyProducts(category);
                System.out.println();
                printSeparator();
                System.out.println();
                String product = scanner.nextLine();
                product = Character.toUpperCase(product.charAt(0)) + product.substring(1).toLowerCase();
                printSeparator();
                System.out.println();
                if (Objects.equals(changingList.getProductList().get(category), product)) {
                    System.out.println("PRODUCT NOT FOUND!");
                    continue;
                }
                changingList.deleteOnlyOneProduct(category, product);
                System.out.println("DELETED \"" + product + "\" FROM CATEGORY: \"" + category + "\"");
                printSeparator();
                System.out.println();
            } else if (change == 7) {
                int res3 = changingList.save(SECOND_NAME);
                printSeparator();
                if (res3 == 0)
                    System.out.println("\nSAVED SHOPPING LIST TO FILE -> \"" + SECOND_NAME + "\"");
                else {
                    return;
                }
                printSeparator();
                System.out.println();
                break;
            } else {
                System.out.println("ENTER A NUMBER FROM [1-7]");
            }
        }
    }
}