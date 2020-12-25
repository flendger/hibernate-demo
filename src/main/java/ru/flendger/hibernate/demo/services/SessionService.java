package ru.flendger.hibernate.demo.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SessionService {

    private SessionFactory factory;
    private Session session;

    @PostConstruct
    private void init() {
        factory = new Configuration()
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession() {
        if (session == null) {
            session = factory.openSession();
        }

        return session;
    }

    public void closeSession() {
        if (session == null) {
            return;
        }
        session.close();
        session = null;
    }

    public void beginTransaction() {
        Session curSession = getSession();
        if (!curSession.getTransaction().isActive()) {
            curSession.beginTransaction();
        }
    }

    public void commit() {
        Transaction transaction = getSession().getTransaction();
        if (!transaction.isActive()) {
            return;
        }
        transaction.commit();
    }

    public void rollBack() {
        Transaction transaction = getSession().getTransaction();
        if (!transaction.isActive()) {
            return;
        }
        transaction.rollback();
    }
}
