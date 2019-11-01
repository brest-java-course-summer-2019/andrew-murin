package com.epam.brest2019.courses.dao;


import com.epam.brest2019.courses.model.Payment;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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


    private SessionFactory sessionFactory;

    @Autowired
    public PaymentDaoJdbcImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Payment> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SQLQuery findAll = session.createSQLQuery(SELECT_ALL);
        List payments = findAll.list();
        transaction.commit();
        return payments;
    }

    @Override
    public List<Payment> findByTicketId(Integer ticketId) {
        return null;
    }

    @Override
    public Optional<Payment> findById(Integer paymentId) {
        return Optional.empty();
    }

    @Override
    public Payment add(Payment payment) {
        return null;
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(Integer paymentId) {

    }

    @Override
    public List<Payment> findAllWitchDirection() {
        return null;
    }

    @Override
    public List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate) {
        return null;
    }


//    @Override
//    public List<Payment> findByTicketId(Integer ticketId) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        List<Payment> payments = (List<Payment>) session.get(Payment.class, ticketId);
//        return payments;
//    }
//
//    @Override
//    public Payment findById(Integer paymentId) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        Payment payment = session.get(Payment.class, paymentId);
//        return payment;
//    }
//
//    @Override
//    public Integer add(Payment payment) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        Integer id = (Integer) session.save(payment);
//        transaction.commit();
//        return id;
//    }
//
//    @Override
//    public void update(Payment payment) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(payment);
//        transaction.commit();
//    }
//
//    @Override
//    public void delete(Integer paymentId) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.delete(paymentId);
//        transaction.commit();
//    }
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
