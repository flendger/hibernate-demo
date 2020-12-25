package ru.flendger.hibernate.demo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.flendger.hibernate.demo.dao.CustomerDAO;
import ru.flendger.hibernate.demo.dao.ProductDAO;
import ru.flendger.hibernate.demo.models.Product;
import ru.flendger.hibernate.demo.services.SalesService;
import ru.flendger.hibernate.demo.services.SessionService;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SessionService sessionService = context.getBean("sessionService", SessionService.class);
        ProductDAO productDAO = context.getBean("productDAO", ProductDAO.class);
        CustomerDAO customerDAO = context.getBean("customerDAO", CustomerDAO.class);
        SalesService salesService = context.getBean("salesService", SalesService.class);

        sessionService.beginTransaction();

        System.out.println(productDAO.findAll());
        Product product = productDAO.findById(2L);
        product.setPrice(225);

        System.out.println(customerDAO.findAll());

        salesService.getSales(2L).forEach(System.out::println);
        salesService.getSales(customerDAO.findById(5L), productDAO.findById(5L)).forEach(System.out::println);

        sessionService.commit();
        sessionService.closeSession();
    }
}
