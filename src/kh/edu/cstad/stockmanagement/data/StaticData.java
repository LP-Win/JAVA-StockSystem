package kh.edu.cstad.stockmanagement.data;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
    public static final List<Category> categories = new ArrayList<>();

    static {
        Category desktop = new Category("Desktop");
        desktop.addProduct(new Product("Dell", "Office", 30, 300.0, "I3 6Gen, 8GB, 256GB"));
        categories.add(desktop);

        Category laptop = new Category("Laptop");
        laptop.addProduct(new Product("ACER", "Office", 5, 400.0, "I5 6Gen, 8GB, 256GB"));
        laptop.addProduct(new Product("MSI", "Gaming", 3, 999.99, "I5 13Gen, 16GB, 512GB"));
        categories.add(laptop);

        Category monitor = new Category("Monitor");
        monitor.addProduct(new Product("ASUS", "Office", 8, 140.0, "24Inch, FHD, 200Hz"));
        categories.add(monitor);

        Category network = new Category("Network");
        network.addProduct(new Product("TP-link", "Router", 15, 30.0, "Wifi 5"));
        categories.add(network);
    }
}