package kh.edu.cstad.stockmanagement.util;

import kh.edu.cstad.stockmanagement.data.Category;
import kh.edu.cstad.stockmanagement.data.Product;
import java.time.LocalDate;
import java.util.Scanner;
import static kh.edu.cstad.stockmanagement.data.StaticData.categories;

public class ProdUtil {
    public static void manageProducts() {
        Scanner scanner = new Scanner(System.in);
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

    public static void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Category Index:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        try {
            System.out.print("Choose Category: ");
            int catIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (catIndex >= 0 && catIndex < categories.size()) {
                Category selectedCat = categories.get(catIndex);
                PrintUtil.printMessage("====== Please Enter Product Detail ======");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Type: ");
                String type = scanner.nextLine();
                System.out.print("Qty: ");
                int qty = Integer.parseInt(scanner.nextLine());
                System.out.print("Price: ");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.print("Detail: ");
                String extraInfo = scanner.nextLine();

                selectedCat.addProduct(new Product(name, type, qty, price, LocalDate.now(),extraInfo));
                PrintUtil.printMessage("Product added.");
            }
        } catch (Exception e) {
            PrintUtil.printMessage("Invalid input.");
        }
    }

    public static void editProduct() {
        Scanner scanner = new Scanner(System.in);
        PrintUtil.printMessage("====== Update Product ======");
        System.out.print("Enter Product Name/ID to Update: ");
        String query = scanner.nextLine();
        for (Category c : categories) {
            for (Product p : c.getProducts()) {
                if (p.getName().equalsIgnoreCase(query) || p.getId().equalsIgnoreCase(query)) {
                    if (!query.isEmpty()) {
                        PrintUtil.printMessage("====== Please Update Product Detail ======");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        p.setName(name);
                        System.out.print("Type: ");
                        String type = scanner.nextLine();
                        p.setType(type);
                        System.out.print("Qty: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        p.setQuantity(qty);
                        System.out.print("Price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        p.setPrice(price);
                        p.setDateAdded(LocalDate.now());

                        System.out.println("Enter [ Y ] to update info:");
                        String keep = scanner.nextLine();
                        if (keep.equalsIgnoreCase("Y")) {
                            System.out.print("Detail: ");
                            String extraInfo = scanner.nextLine();
                            p.setExtraInfo(extraInfo);
                        }
                    }
                    PrintUtil.printMessage("Product updated.");
                    return;
                }
            }
        }
        PrintUtil.printMessage("Product not found.");
    }

    public static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        PrintUtil.printMessage("====== Delete Product ======");
        System.out.print("Enter Product Name/ID to Delete: ");
        String query = scanner.nextLine();
        for (Category c : categories) {
            System.out.println("Please Enter [ DELETE ] to Confirm or [ X ] to Cancel.");
            String delete = scanner.nextLine();
            if (delete.equalsIgnoreCase("DELETE")) {
                c.getProducts().removeIf(p -> p.getName().equalsIgnoreCase(query) || p.getId().equalsIgnoreCase(query));
                PrintUtil.printMessage("Product deleted.");
                return;
            } else if (delete.equalsIgnoreCase("X")) {
                System.out.println("Deleting has been Cancelled.");
                return;
            }
        }
        PrintUtil.printMessage("Product not found.");
    }



}
