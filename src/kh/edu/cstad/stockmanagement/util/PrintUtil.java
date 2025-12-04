package kh.edu.cstad.stockmanagement.util;

import kh.edu.cstad.stockmanagement.data.Category;
import kh.edu.cstad.stockmanagement.data.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class PrintUtil {

    public static void print(String text, boolean isNewLine) {
        if (isNewLine)
            System.out.println(text);
        else
            System.out.print(text);
    }

    public static void printMessage(String text) {
        Table table = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
        table.setColumnWidth(0, 50, 80);
        table.addCell(text);
        print(table.render(), true);
    }

    public static void printAppName() {
        Table table = new Table(1, BorderStyle.ASTERISKS);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.setColumnWidth(0, 50, 80);
        table.addCell("Stock Management System", cellStyle);
        print(table.render(), true);
    }

    public static void printMainMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.setColumnWidth(0, 50, 80);
        table.addCell("Main Menu", cellStyle);
        table.addCell("(1). Category Management");
        table.addCell("(2). Product Management");
        table.addCell("(3). Exit System");
        print(table.render(), true);
    }

    public static void printCategoryMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.setColumnWidth(0, 50, 80);
        table.addCell("Category Management", cellStyle);
        table.addCell("(1). Create Category");
        table.addCell("(2). Edit Category");
        table.addCell("(3). View Categories");
        table.addCell("(4). Delete Category");
        table.addCell("(5). Return to Main Menu");
        print(table.render(), true);
    }

    public static void printProductMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.setColumnWidth(0, 50, 80);
        table.addCell("Product Management", cellStyle);
        table.addCell("(1). Create Product");
        table.addCell("(2). Edit Product");
        table.addCell("(3). View Product");
        table.addCell("(4). Delete Product");
        table.addCell("(5). Return to Main Menu");
        print(table.render(), true);
    }

    // Print Product Data
    public static void printProductTable(List<Category> categories) {
        Table table = new Table(8, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Name", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Type", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Qty", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Price", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Date", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Category", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Other", new CellStyle(CellStyle.HorizontalAlign.center));

        boolean hasData = false;
        for (Category c : categories) {
            for (Product p : c.getProducts()) {
                hasData = true;
                table.addCell(p.getId());
                table.addCell(p.getName());
                table.addCell(p.getType());
                table.addCell(String.valueOf(p.getQuantity()));
                table.addCell("$" + p.getPrice());
                table.addCell(p.getDateAdded().toString());
                table.addCell(c.getName());
                table.addCell(p.getExtraInfo());
            }
        }

        if (hasData) {
            print(table.render(), true);
        } else {
            printMessage("No products found.");
        }
    }


    // Print Category Data
    public static void printCategoryTable(List<Category> categories) {
        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        table.addCell("Category Name");
        table.addCell("Product Count");

        for (Category c : categories) {
            table.addCell(c.getName());
            table.addCell(String.valueOf(c.getProducts().size()));
        }
        print(table.render(), true);
    }
}