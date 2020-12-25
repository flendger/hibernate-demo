package ru.flendger.hibernate.demo.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.flendger.hibernate.demo.models.Product;
import ru.flendger.hibernate.demo.services.SessionService;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ProductDAO {

    private final SessionService sessionService;

    public Product findById(Long id) {
        return sessionService.getSession().get(Product.class, id);
    }

    public List<Product> findAll() {
        return sessionService.getSession().createQuery("from Product", Product.class).getResultList();
    }

    public Product saveOrUpdate(Product product) {
        sessionService.getSession().saveOrUpdate(product);
        return product;
    }

    public void deleteById(Long id) {
        Product product = findById(id);
        if (product == null) {
            throw new NoSuchElementException(String.valueOf(id));
        }
        sessionService.getSession().delete(product);
    }
}
