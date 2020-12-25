package ru.flendger.hibernate.demo.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.flendger.hibernate.demo.models.Customer;
import ru.flendger.hibernate.demo.models.Product;
import ru.flendger.hibernate.demo.models.SaleTransaction;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SalesService {

    private final SessionService sessionService;

    public List<SaleTransaction> getSales(Long customerId) {
        Session session = sessionService.getSession();
        return session.createQuery("from SaleTransaction where customer.id = :id", SaleTransaction.class)
                .setParameter("id", customerId)
                .getResultList();
    }

    public List<SaleTransaction> getSales(Customer customer, Product product) {
        Session session = sessionService.getSession();
        return session.createQuery("from SaleTransaction  where customer = :customer and product = :product", SaleTransaction.class)
                .setParameter("customer", customer)
                .setParameter("product", product)
                .getResultList();
    }
}
