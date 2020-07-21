package com.epam.brest2019.courses.dao;


import com.epam.brest2019.courses.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Payment DAO Interface implementation
 */
@Repository
@PropertySource("classpath:/jpql_query_payment.properties")
public class PaymentDaoJdbcImpl implements PaymentDao {

    @Value("${payment.find}")
    private String SELECT_;

    @Value("${payment.findByTicketId}")
    private String FIND_BY_TICKET_ID;

    @Value("${payment.findById}")
    private String FIND_BY_PAYMENT_ID;

    @Value("${payment.findWitchDirection}")
    private String FIND__WITH_DIRECTION;

    @Value("${payment.searchByDate}")
    private String SEARCH_BY_DATE;


    private static final String ADD = "a";
    private static final String UPDATE = "u";
    private static final String DELETE = "d";

    private static final String TICKET_ID = "ticketId";
    private static final String PAYMENT_ID = "paymentId";
    private static final String STRART_DATE = "startDate";
    private static final String FINISH_DATE = "finishDate";


    private final SessionFactory sessionFactory;
    private Transact transact;

    @Autowired
    public PaymentDaoJdbcImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        transact = new Transact(sessionFactory);
    }

    @Override
    public List<Payment> find() {
        List<Payment> payments = transact.sessionFixture(SELECT_);
        return payments;
    }

    @Override
    public List<Payment> findByTicketId(Integer ticketId) {
        Map<String, Integer> map = new HashMap<>();
        map.put(TICKET_ID, ticketId);

        List<Payment> payments = transact.sessionParameter(FIND_BY_TICKET_ID, map);
        return payments;
    }

    @Override
    public Payment findById(Integer paymentId) {

        Transaction transaction = null;
        Payment payment = null;

        try ( Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();

            payment = session.get(Payment.class, paymentId);

            transaction.commit();

        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
                ex.printStackTrace();
            }
        }

        return payment;
    }

    @Override
    public void add(Payment payment) {
        transact.dmlQueryFixture(payment, ADD);
    }

    @Override
    public void delete(Payment paymentId) {
        transact.dmlQueryFixture(paymentId, DELETE);
    }

    @Override
    public void update(Payment payment) {
        transact.dmlQueryFixture(payment, UPDATE);
    }

    @Override
    public List<Payment> findWitchDirection() {
        List<Payment> payments = transact.sessionFixture(FIND__WITH_DIRECTION);
        return payments;
    }

    @Override
    public List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate) {
        Map<String, LocalDate> map = new HashMap<>();
        map.put(STRART_DATE, startDate);
        map.put(FINISH_DATE, finishDate);

        List<Payment> payments = transact.sessionParameter(SEARCH_BY_DATE, map);
        return payments;
    }

}
