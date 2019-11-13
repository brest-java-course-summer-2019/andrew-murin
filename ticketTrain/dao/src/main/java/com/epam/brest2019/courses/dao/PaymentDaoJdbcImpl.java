package com.epam.brest2019.courses.dao;


import com.epam.brest2019.courses.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@PropertySource("classpath:sql_query_payment.properties")
public class PaymentDaoJdbcImpl implements PaymentDao {

    @Value("${payment.findAll}")
    private String SELECT_ALL;

    @Value("${payment.findById}")
    private String FIND_BY_ID;

    @Value("${payment.findByTicketId}")
    private String FIND_BY_TICKET_ID;

    @Value("${payment.add}")
    private String ADD_PAYMENT;

    @Value("${payment.update}")
    private String UPDATE;

    @Value("${payment.delete}")
    private String DELETE;

    @Value("${payment.findAllWitchDirection}")
    private String FIND_ALL_WITH_DIRECTION;

    @Value("${payment.searchByDate}")
    private String SEARCH_BY_DATE;

    private final SessionFactory sessionFactory;


    @Autowired
    public PaymentDaoJdbcImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Payment> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery findAll = session.createSQLQuery(SELECT_ALL);

        List<Payment> payments = findAll.getResultList();

        transaction.commit();
        session.close();

        return payments;
    }


    @Override
    public List<Payment> findByTicketId(Integer ticketId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery findByTicketId = session.createSQLQuery(FIND_BY_TICKET_ID);

        findByTicketId.setParameter("ticketId", ticketId);

        List<Payment> payments = findByTicketId.getResultList();

        transaction.commit();
        session.close();

        return payments;
    }

    @Override
    public Payment findById(Integer paymentId) {
        return null;
    }


//    @Override
//    public Payment findById(Integer paymentId) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        NativeQuery findById = session.createSQLQuery(FIND_BY_ID);
//        findById.setParameter("paymentId", paymentId);
//
//        Payment payment = (Payment) findById;
//
//        transaction.commit();
//        session.close();
//
//        return payment;
//    }


//    @Override
//    public void add(Payment payment) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
////        NativeQuery addPayment = session.createSQLQuery(ADD_PAYMENT);
////        addPayment.setParameter("paymentDate", payment.getPaymentDate());
////        addPayment.setParameter("ticketId", payment.getTicketId());
//
//        session.persist(payment);
//
//        transaction.commit();
//        session.close();
//    }
//
//    @Override
//    public void delete(Integer paymentId) {
//
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        session.delete(paymentId);
//
//        transaction.commit();
//        session.close();
//    }


    @Override
    public void update(Payment payment) {

    }

    @Override
    public List<Payment> findAllWitchDirection() {
        return null;
    }

    @Override
    public List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate) {
        return null;
    }





//
//    @Override
//    public void update(Payment payment) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(payment);
//        transaction.commit();
//    }
//

//
//    @Override
//    public List<Payment> findAllWitchDirection() {
//        return null;
//    }
//
//    @Override
//    public List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate) {
//        return null;
//    }
}
