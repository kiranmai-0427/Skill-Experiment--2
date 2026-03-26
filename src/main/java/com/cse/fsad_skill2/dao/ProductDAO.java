package com.cse.fsad_skill2.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cse.fsad_skill2.entity.Product;
import com.cse.fsad_skill2.util.HibernateUtil;

public class ProductDAO {

    // INSERT
    public void saveProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(p);

        tx.commit();
        session.close();
    }

    // READ
    public Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product p = session.get(Product.class, id);
        session.close();
        return p;
    }

    // UPDATE
    public void updateProduct(int id, double price, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        if (p != null) {
            p.setPrice(price);
            p.setQuantity(quantity);
            session.update(p);
        } else {
            System.out.println("Product not found");
        }

        tx.commit();
        session.close();
    }

    // DELETE
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        if (p != null) {
            session.delete(p);
        } else {
            System.out.println("Product not found");
        }

        tx.commit();
        session.close();
    }
}