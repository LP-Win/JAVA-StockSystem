package kh.edu.cstad.stockmanagement;

import java.time.LocalDate;

public class Product {
    private String id;
    private String name;
    private String type;
    private int quantity;
    private double price;
    private LocalDate dateAdded;
    private String extraInfo;

    public Product(String id, String name, String type, int quantity, double price, String extraInfo) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.dateAdded = LocalDate.now();
        this.extraInfo = extraInfo;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return name;
    }
}