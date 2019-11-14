package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.Map;

public class Transact {

    private final SessionFactory sessionFactory;

    public Transact (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    List<Payment> sessionFixture(String sqlQuery) {

        Transaction transaction = null;
        List<Payment> payments = null;

        try ( Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();

            NativeQuery query = session.createSQLQuery(sqlQuery);
             payments = query.getResultList();

            transaction.commit();

        } catch (Exception ex) {
            if(transaction != null)
                transaction.rollback();

        }
        return payments;
    }

    List<Payment> sessionParameter(String sqlQuery, Map param) {

        Transaction transaction = null;
        List<Payment> payments = null;

        try ( Session session = sessionFactory.openSession() ) {

            transaction = session.beginTransaction();

            NativeQuery query = session.createSQLQuery(sqlQuery);
            for (Object key : param.keySet()) {
                query.setParameter((String) key, param.get(key));
            }

            payments = query.getResultList();
            transaction.commit();

        } catch (Exception ex) {
            if(transaction != null)
                transaction.rollback();

            ex.printStackTrace();
        }
        return payments;
    }

    void dmlQueryFixture(Payment payment, String dmlQuery) {

        Transaction transaction = null;

        try ( Session session = sessionFactory.openSession() ) {

            transaction = session.beginTransaction();

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

        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            ex.printStackTrace();
        }
    }
}
