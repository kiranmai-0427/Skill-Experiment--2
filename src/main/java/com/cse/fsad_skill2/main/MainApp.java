package com.cse.fsad_skill2.main;

import com.cse.fsad_skill2.dao.ProductDAO;
import com.cse.fsad_skill2.entity.Product;

public class MainApp {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // INSERT
        dao.saveProduct(new Product("Laptop", "Gaming Laptop", 75000, 5));
        dao.saveProduct(new Product("Phone", "Android Phone", 20000, 10));

        // READ
        Product p = dao.getProduct(1);
        if (p != null) {
            System.out.println("Product: " + p.getName() + " Price: " + p.getPrice());
        }

        // UPDATE
        dao.updateProduct(1, 80000, 7);

        // DELETE
        dao.deleteProduct(2);
    }
}