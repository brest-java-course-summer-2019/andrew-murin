package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private PaymentDao paymentDao;
    private JavaMailSender javaMailSender;


    @Autowired
    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public List<Payment> findAll() {
        LOGGER.debug("Find all:");
        return paymentDao.findAll();
    }

    @Override
    public List<Payment> findByTicketId(Integer ticketId) {
        LOGGER.debug("Find by ticket id: {}", ticketId);
        return paymentDao.findByTicketId(ticketId);
    }

    @Override
    public Payment findById(Integer paymentId) {
        LOGGER.debug("Find by id: {}", paymentId);

        return paymentDao.findById(paymentId);
    }

    @Override
    public void add(Payment payment) {
        LOGGER.debug("Add: {}", payment);
        sendMessage(payment);
        paymentDao.add(payment);
    }

    @Override
    public void update(Payment payment) {
        LOGGER.debug("Update: {}", payment);
        paymentDao.update(payment);
    }

    @Override
    public void delete(Integer paymentId) {
        LOGGER.debug("Delete: {}", paymentId);

        Payment payment = paymentDao.findById(paymentId);

        paymentDao.delete(payment);
    }

    @Override
    public List<Payment> findAllWitchDirection() {
        LOGGER.debug("Find all with direction()");
        return paymentDao.findAllWitchDirection();
    }

    @Override
    public List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate) {
        LOGGER.debug("Search paid-ticket by date({} - {})", startDate, finishDate);
        return paymentDao.searchByDate(startDate, finishDate);
    }

    private void sendMessage(Payment payment) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("payticketapplication@gmail.com");
        mail.setTo(payment.getEmail());
        mail.setSubject("Hi");
        mail.setText("It is simple a message from my ticketTrain-application :) Have a nice a day! ");

        javaMailSender.send(mail);
    }
}
