package com.cse.fsad_skill2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cse.fsad_skill2.entity.Product;
import com.cse.fsad_skill2.util.HibernateUtil;

public class ProductDAO {

    // ================= CRUD =================

    public void saveProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(p);

        tx.commit();
        session.close();
    }

    public Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product p = session.get(Product.class, id);
        session.close();
        return p;
    }

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

    // ================= HQL =================

    // Sort by price ASC
    public void sortByPriceAsc() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List list = s.createQuery("from Product order by price asc").list();

        for (Object obj : list) {
            Product p = (Product) obj;
            System.out.println(p.getName() + " - " + p.getPrice());
        }
        s.close();
    }

    // Sort by price DESC
    public void sortByPriceDesc() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List list = s.createQuery("from Product order by price desc").list();

        for (Object obj : list) {
            Product p = (Product) obj;
            System.out.println(p.getName() + " - " + p.getPrice());
        }
        s.close();
    }

    // Sort by quantity DESC
    public void sortByQuantityDesc() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List list = s.createQuery("from Product order by quantity desc").list();

        for (Object obj : list) {
            Product p = (Product) obj;
            System.out.println(p.getName() + " - " + p.getQuantity());
        }
        s.close();
    }

    // Pagination - first 3
    public void getFirst3Products() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List list = s.createQuery("from Product")
                .setFirstResult(0)
                .setMaxResults(3)
                .list();

        for (Object obj : list) {
            Product p = (Product) obj;
            System.out.println(p.getName());
        }
        s.close();
    }

    // Pagination - next 3
    public void getNext3Products() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List list = s.createQuery("from Product")
                .setFirstResult(3)
                .setMaxResults(3)
                .list();

        for (Object obj : list) {
            Product p = (Product) obj;
            System.out.println(p.getName());
        }
        s.close();
    }

    // Count all
    public void countAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Long count = (Long) s.createQuery("select count(*) from Product").uniqueResult();

        System.out.println("Total Products: " + count);
        s.close();
    }

    // Count available
    public void countAvailable() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Long count = (Long) s.createQuery("select count(*) from Product where quantity > 0").uniqueResult();

        System.out.println("Available Products: " + count);
        s.close();
    }

    // Min & Max price
    public void minMaxPrice() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Object[] result = (Object[]) s.createQuery(
                "select min(price), max(price) from Product"
        ).uniqueResult();

        System.out.println("Min Price: " + result[0]);
        System.out.println("Max Price: " + result[1]);

        s.close();
    }

    // GROUP BY
    public void groupByDescription() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List list = s.createQuery(
                "select description, count(*) from Product group by description"
        ).list();

        for (Object obj : list) {
            Object[] row = (Object[]) obj;
            System.out.println(row[0] + " -> " + row[1]);
        }
        s.close();
    }

    // WHERE
    public void filterByPrice() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List list = s.createQuery(
                "from Product where price between 1000 and 8000"
        ).list();

        for (Object obj : list) {
            Product p = (Product) obj;
            System.out.println(p.getName() + " - " + p.getPrice());
        }
        s.close();
    }

    // LIKE
    public void likeQueries() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.println("Starts with M:");
        List list1 = s.createQuery("from Product where name like 'M%'").list();
        for (Object obj : list1) {
            Product p = (Product) obj;
            System.out.println(p.getName());
        }

        System.out.println("Ends with r:");
        List list2 = s.createQuery("from Product where name like '%r'").list();
        for (Object obj : list2) {
            Product p = (Product) obj;
            System.out.println(p.getName());
        }

        System.out.println("Contains 'o':");
        List list3 = s.createQuery("from Product where name like '%o%'").list();
        for (Object obj : list3) {
            Product p = (Product) obj;
            System.out.println(p.getName());
        }

        System.out.println("Length = 5:");
        List list4 = s.createQuery("from Product where length(name)=5").list();
        for (Object obj : list4) {
            Product p = (Product) obj;
            System.out.println(p.getName());
        }

        s.close();
    }
}