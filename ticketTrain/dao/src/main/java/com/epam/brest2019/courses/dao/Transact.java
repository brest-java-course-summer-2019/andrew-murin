package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class Transact {

    private final SessionFactory sessionFactory;

    @Autowired
    public Transact (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    List<Payment> sessionFixture(String sqlQuery) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createSQLQuery(sqlQuery);
        List<Payment> payments = query.getResultList();

        transaction.commit();
        session.close();

        return payments;
    }

    List<Payment> sessionParameter(String sqlQuery, Map param) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createSQLQuery(sqlQuery);
        for(Object key : param.keySet()) {
            query.setParameter((String) key, param.get(key));
        }

        List<Payment> payments = query.getResultList();

        transaction.commit();
        session.close();

        return payments;
    }

    void dmlQueryFixture(Payment payment, String dmlQuery) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        switch (dmlQuery) {
            case "a":
                session.persist(payment);
                break;
            case "d":
                session.remove(payment);
                break;
            case "u":
                session.update(payment);
                break;
        }

        transaction.commit();
        session.close();
    }
}
