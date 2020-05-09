package com.epam.brest2019.courses.dao;


import com.epam.brest2019.courses.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 *  Payment DAO Interface implementation
 */
@Repository
public class PaymentDaoImpl implements PaymentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDaoImpl.class);


    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public List<Payment> findAllWitchDirection() {
        return null;
    }

    @Override
    public List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate) {
        return null;
    }

    @Override
    public Payment findById(Integer paymentId) {
        return null;
    }

    @Override
    public List<Payment> findByTicketId(Integer ticketId) {
        return null;
    }

    @Override
    public void add(Payment payment) {

    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(Payment paymentId) {

    }
}
