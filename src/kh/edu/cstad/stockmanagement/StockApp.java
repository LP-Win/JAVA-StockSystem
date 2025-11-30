package kh.edu.cstad.stockmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockApp {

    // Global State
    private static final List<Category> categories = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Category desktop = new Category("Desktop");
        desktop.addProduct(new Product("P01", "Dell", "Office", 30, 300, "I3 6Gen, 8GB, 256GB"));
        categories.add(desktop);

        Category laptop = new Category("Laptop");
        laptop.addProduct(new Product("P02", "ACER", "Office", 5, 400, "I5 6Gen, 8GB, 256GB"));
        laptop.addProduct(new Product("P05", "MSI", "Gaming", 3, 999.99, "I5 13Gen, 16GB, 512GB"));
        categories.add(laptop);

        Category monitor = new Category("Monitor");
        monitor.addProduct(new Product("P03", "ASUS", "Office", 8, 140, "24Inch, FHD, 200Hz"));
        categories.add(monitor);

        Category network = new Category("Network");
        network.addProduct(new Product("P04", "TP-link", "Routor", 15, 30, "Wifi 5"));
        categories.add(network);

        PrintUtil.printAppName();

        while (true) {
            PrintUtil.printMainMenu();
            System.out.print("Select Option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageCategories();
                    break;
                case "2":
                    manageProducts();
                    break;
                case "3":
                    PrintUtil.printMessage("Exiting system. Goodbye!");
                    return;
                default:
                    PrintUtil.printMessage("Invalid option.");
            }
        }
    }


    // CATEGORY MANAGEMENT
    private static void manageCategories() {
        while (true) {
            PrintUtil.printCategoryMenu();
            System.out.print("Action: ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "1": // Create
                    System.out.print("Enter New Category Name: ");
                    String name = scanner.nextLine().trim();
                    if (findCategory(name) != null) {
                        PrintUtil.printMessage("Error: Category already exists.");
                    } else {
                        categories.add(new Category(name));
                        PrintUtil.printMessage("Category created successfully.");
                    }
                    break;
                case "2": // Edit
                    PrintUtil.printCategoryTable(categories);
                    System.out.print("Enter Category Name to Edit: ");
                    String oldName = scanner.nextLine();
                    Category catToEdit = findCategory(oldName);
                    if (catToEdit != null) {
                        System.out.print("Enter New Name: ");
                        catToEdit.setName(scanner.nextLine().trim());
                        PrintUtil.printMessage("Category updated.");
                    } else {
                        PrintUtil.printMessage("Category not found.");
                    }
                    break;
                case "3": { // View
                    while (true) {
                        PrintUtil.printCategoryTable(categories);
                        System.out.print("Enter 'x' to go back: ");
                        String back = scanner.nextLine();
                        if (back.equalsIgnoreCase("x")) break;
                    }
                    break;
                }
                case "4": // Delete
                    PrintUtil.printCategoryTable(categories);
                    System.out.print("Enter Category Name to Delete: ");
                    String delName = scanner.nextLine();
                    Category catDel = findCategory(delName);
                    if (catDel != null) {
                        categories.remove(catDel);
                        PrintUtil.printMessage("Category deleted.");
                    } else {
                        PrintUtil.printMessage("Category not found.");
                    }
                    break;
                case "5":
                    return;
                default:
                    PrintUtil.printMessage("Invalid input.");
            }
        }
    }

    // PRODUCT MANAGEMENT
    private static void manageProducts() {
        if (categories.isEmpty()) {
            PrintUtil.printMessage("Please create a category first.");
            return;
        }
        while (true) {
            PrintUtil.printProductMenu();
            System.out.print("Action: ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "1": // Create
                    createProduct();
                    break;
                case "2": // Edit
                    editProduct();
                    break;
                case "3": { // View
                    while (true) {
                        PrintUtil.printProductTable(categories);
                        System.out.print("Enter 'x' to go back: ");
                        String back = scanner.nextLine();
                        if (back.equalsIgnoreCase("x")) break;
                    }
                    break;
                }
                case "4": // Delete
                    deleteProduct();
                    break;
                case "5":
                    return;
                default:
                    PrintUtil.printMessage("Invalid input.");
            }
        }
    }

    private static void createProduct() {
        System.out.println("Select Category Index:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        try {
            System.out.print("Choose Category: ");
            int catIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (catIndex >= 0 && catIndex < categories.size()) {
                Category selectedCat = categories.get(catIndex);
                System.out.println("====== Please Enter Product Detail ======");
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Type: ");
                String type = scanner.nextLine();
                System.out.print("Qty: ");
                int qty = Integer.parseInt(scanner.nextLine());
                System.out.print("Price: ");
                double price = Double.parseDouble(scanner.nextLine());

                selectedCat.addProduct(new Product(id, name, type, qty, price, ""));
                PrintUtil.printMessage("Product added.");
            }
        } catch (Exception e) {
            PrintUtil.printMessage("Invalid input.");
        }
    }

    private static void editProduct() {
        System.out.print("Enter Product Name/ID to Edit: ");
        String query = scanner.nextLine();
        for (Category c : categories) {
            for (Product p : c.getProducts()) {
                if (p.getName().equalsIgnoreCase(query) || p.getId().equalsIgnoreCase(query)) {
                    System.out.print("New Name (" + p.getName() + "): ");
                    String val = scanner.nextLine();
                    if (!val.isEmpty()) p.setName(val);
                    PrintUtil.printMessage("Product updated.");
                    return;
                }
            }
        }
        PrintUtil.printMessage("Product not found.");
    }

    private static void deleteProduct() {
        System.out.print("Enter Product Name/ID to Delete: ");
        String query = scanner.nextLine();
        for (Category c : categories) {
            if (c.getProducts().removeIf(p -> p.getName().equalsIgnoreCase(query) || p.getId().equalsIgnoreCase(query))) {
                PrintUtil.printMessage("Product deleted.");
                return;
            }
        }
        PrintUtil.printMessage("Product not found.");
    }

    // Helper
    private static Category findCategory(String name) {
        for (Category c : categories) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }
}