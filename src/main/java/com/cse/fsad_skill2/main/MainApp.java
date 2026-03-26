package com.cse.fsad_skill2.main;

import com.cse.fsad_skill2.dao.ProductDAO;
import com.cse.fsad_skill2.entity.Product;

public class MainApp {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // ================= INSERT MORE DATA =================
        dao.saveProduct(new Product("Mouse", "Electronics", 500, 20));
        dao.saveProduct(new Product("Keyboard", "Electronics", 1500, 15));
        dao.saveProduct(new Product("Monitor", "Electronics", 10000, 8));
        dao.saveProduct(new Product("Chair", "Furniture", 3000, 12));
        dao.saveProduct(new Product("Table", "Furniture", 7000, 5));

        // ================= HQL OPERATIONS =================

        System.out.println("\n--- Sort Price ASC ---");
        dao.sortByPriceAsc();

        System.out.println("\n--- Sort Price DESC ---");
        dao.sortByPriceDesc();

        System.out.println("\n--- Sort Quantity DESC ---");
        dao.sortByQuantityDesc();

        System.out.println("\n--- First 3 Products ---");
        dao.getFirst3Products();

        System.out.println("\n--- Next 3 Products ---");
        dao.getNext3Products();

        System.out.println("\n--- Count All ---");
        dao.countAll();

        System.out.println("\n--- Count Available ---");
        dao.countAvailable();

        System.out.println("\n--- Min Max Price ---");
        dao.minMaxPrice();

        System.out.println("\n--- Group By Description ---");
        dao.groupByDescription();

        System.out.println("\n--- Filter Price ---");
        dao.filterByPrice();

        System.out.println("\n--- LIKE Queries ---");
        dao.likeQueries();
    }
}