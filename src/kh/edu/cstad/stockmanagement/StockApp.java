package kh.edu.cstad.stockmanagement;


import kh.edu.cstad.stockmanagement.util.PrintUtil;

import java.util.Scanner;

import static kh.edu.cstad.stockmanagement.util.CatUtil.manageCategories;
import static kh.edu.cstad.stockmanagement.util.ProdUtil.manageProducts;


public class StockApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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


}