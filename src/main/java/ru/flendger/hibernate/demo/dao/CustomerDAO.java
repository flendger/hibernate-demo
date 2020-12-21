package ru.flendger.hibernate.demo.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.flendger.hibernate.demo.models.Customer;
import ru.flendger.hibernate.demo.services.SessionService;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CustomerDAO {

    private final SessionService sessionService;

    public Customer findById(Long id) {
        return sessionService.getSession().get(Customer.class, id);
    }

    public List<Customer> findAll() {
        return sessionService.getSession().createQuery("from Customer", Customer.class).getResultList();
    }

    public Customer saveOrUpdate(Customer customer) {
        sessionService.getSession().saveOrUpdate(customer);
        return customer;
    }

    public void deleteById(Long id) {
        Customer customer = findById(id);
        if (customer == null) {
            throw new NoSuchElementException(String.valueOf(id));
        }
        sessionService.getSession().delete(customer);
    }
}
