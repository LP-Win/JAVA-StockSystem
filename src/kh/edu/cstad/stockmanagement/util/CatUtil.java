package kh.edu.cstad.stockmanagement.util;

import kh.edu.cstad.stockmanagement.data.Category;
import java.util.Scanner;
import static kh.edu.cstad.stockmanagement.data.StaticData.categories;

public class CatUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static void manageCategories() {
        while (true) {
            PrintUtil.printCategoryMenu();
            System.out.print("Action: ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "1": // Create
                    System.out.print("Enter New Category Name: ");
                    String name = scanner.nextLine().trim();

                    if (findCategoryByName(name) != null) {
                        PrintUtil.printMessage("Error: Category already exists.");
                    } else {
                        categories.add(new Category(name));
                        PrintUtil.printMessage("Category created successfully.");
                    }
                    break;

                case "2": // Edit
                    PrintUtil.printCategoryTable(categories);
                    System.out.print("Enter Category Name to Update: ");
                    String oldName = scanner.nextLine();

                    Category catToEdit = findCategoryByName(oldName);

                    if (catToEdit != null) {
                        System.out.print("Enter New Name: ");
                        catToEdit.setName(scanner.nextLine().trim());
                        PrintUtil.printMessage("Category updated.");
                    } else {
                        PrintUtil.printMessage("Category not found.");
                    }
                    break;

                case "3": // View
                    while (true) {
                        PrintUtil.printCategoryTable(categories);
                        System.out.print("Enter 'x' to go back: ");
                        String back = scanner.nextLine();
                        if (back.equalsIgnoreCase("x")) break;
                    }
                    break;

                case "4": // Delete
                    PrintUtil.printCategoryTable(categories);
                    System.out.print("Enter Category Name to Delete: ");
                    String delName = scanner.nextLine();

                    Category catDel = findCategoryByName(delName);

                    if (catDel != null) {
                        System.out.println("Please Enter [ DELETE ] to Confirm or [ X ] to Cancel.");
                        String delete = scanner.nextLine();
                        if (delete.equalsIgnoreCase("DELETE")) {
                            categories.remove(catDel);
                            PrintUtil.printMessage("Category deleted.");
                        } else if (delete.equalsIgnoreCase("X")) {
                            System.out.println("Deleting has been Cancelled.");
                        }
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

    private static Category findCategoryByName(String name) {
        for (Category c : categories) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}