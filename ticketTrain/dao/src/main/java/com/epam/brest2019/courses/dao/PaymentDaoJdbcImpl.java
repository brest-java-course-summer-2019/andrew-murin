//package com.epam.brest2019.courses.dao;
//
//
//import com.epam.brest2019.courses.model.Payment;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// *  Payment DAO Interface implementation
// */
//@Repository
//@PropertySource("classpath:sql_query_payment.properties")
//public class PaymentDaoJdbcImpl implements PaymentDao {
//
//    @Value("${payment.findAll}")
//    private String SELECT_ALL;
//
//    @Value("${payment.findById}")
//    private String FIND_BY_ID;
//
//    @Value("${payment.findByTicketId}")
//    private String FIND_BY_TICKET_ID;
//
//    @Value("${payment.add}")
//    private String ADD_PAYMENT;
//
//    @Value("${payment.update}")
//    private String UPDATE;
//
//    @Value("${payment.delete}")
//    private String DELETE;
//
//    @Value("${payment.findAllWitchDirection}")
//    private String FIND_ALL_WITH_DIRECTION;
//
//    @Value("${payment.searchByDate}")
//    private String SEARCH_BY_DATE;
//
//    private final SessionFactory sessionFactory;
//    private Transact transact;
//
//    @Autowired
//    public PaymentDaoJdbcImpl (SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//        transact = new Transact(sessionFactory);
//    }
//
//    @Override
//    public List<Payment> findAll() {
//        List<Payment> payments = transact.sessionFixture(SELECT_ALL);
//        return payments;
//    }
//
//    @Override
//    public List<Payment> findByTicketId(Integer ticketId) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("ticketId", ticketId);
//
//        List<Payment> payments = transact.sessionParameter(FIND_BY_TICKET_ID, map);
//        return payments;
//    }
//
//    @Override
//    public Payment findById(Integer paymentId) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Payment payment = session.find(Payment.class, paymentId);
//
//        transaction.commit();
//        session.close();
//
//        return payment;
//    }
//
//    @Override
//    public void add(Payment payment) {
//        transact.dmlQueryFixture(payment, "a");
//    }
//
//    @Override
//    public void delete(Payment paymentId) {
//        transact.dmlQueryFixture(paymentId, "d");
//    }
//
//    @Override
//    public void update(Payment payment) {
//        transact.dmlQueryFixture(payment, "u");
//    }
//
//    @Override
//    public List<Payment> findAllWitchDirection() {
//        List<Payment> payments = transact.sessionFixture(FIND_ALL_WITH_DIRECTION);
//        return payments;
//    }
//
//    @Override
//    public List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate) {
//        Map<String, LocalDate> map = new HashMap<>();
//        map.put("startDate", startDate);
//        map.put("finishDate", finishDate);
//
//        List<Payment> payments = transact.sessionParameter(SEARCH_BY_DATE, map);
//        return payments;
//    }
//
//}
