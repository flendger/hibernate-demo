package ru.flendger.hibernate.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lesson5App {

    private static SessionFactory factory;


    public static void main(String[] args) {
        init();

        ProductDAO productDAO = new ProductDAO(factory);
        Product p1 = new Product(1L, "p1", 77);

        productDAO.openSession();
        productDAO.openTransaction();

        p1 = productDAO.saveOrUpdate(p1);
        System.out.println(p1);

        productDAO.closeTransaction();
    }

    private static void init() {
        factory = new Configuration()
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory();
    }
}
