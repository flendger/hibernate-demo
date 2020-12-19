package ru.flendger.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductDAO {

    private final SessionFactory factory;
    private Session session;
    private Transaction transaction;

    public ProductDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public Session openSession() {
        session = factory.openSession();
        return session;
    }

    public Session getSession() {
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public Transaction openTransaction() {
        transaction = session.beginTransaction();
        return transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void closeTransaction() {
        transaction.commit();
        session.close();
    }

    public Product findById(Long id) {
        return session.get(Product.class, id);
    }

    public List<Product> findAll() {
        return null;
    }

    public Product saveOrUpdate(Product product) {
        getSession().saveOrUpdate(product);
        return product;
    }

    public void deleteById(Long id) {
        Product product = findById(id);
        if (product == null) {
            throw new NoSuchElementException(String.valueOf(id));
        }
        getSession().delete(product);
    }
}
