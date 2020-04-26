package com.epam.brest2019.courses.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

public class Transact<T> {

    private SessionFactory sessionFactory;

    public Transact (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    synchronized List<T> sessionFixture(String sqlQuery) {

        Transaction transaction = null;
        List<T> payments = null;

        try ( Session session = sessionFactory.openSession() ) {

            transaction = session.beginTransaction();

            Query query = session.createQuery(sqlQuery);
            payments = query.getResultList();

            transaction.commit();

        } catch (Exception ex) {
            if(transaction != null)
                transaction.rollback();
            ex.printStackTrace();

        }
        return payments;
    }

    synchronized List<T> sessionParameter(String sqlQuery, Map param) {

        Transaction transaction = null;
        List<T> payments = null;

        try ( Session session = sessionFactory.openSession() ) {

            transaction = session.beginTransaction();

            Query query = session.createQuery(sqlQuery);
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

    synchronized void dmlQueryFixture(T payment, String dmlQuery) {

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
