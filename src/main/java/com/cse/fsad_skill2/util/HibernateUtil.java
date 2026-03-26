package com.cse.fsad_skill2.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.cse.fsad_skill2.entity.Product;

public class HibernateUtil {

    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Product.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("SessionFactory creation failed");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}