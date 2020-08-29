package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.dto.TicketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
//@Transactional
public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ticket.class);

    private TicketDao ticketDao;
//    private JavaMailSender javaMailSender;

//    public TicketServiceImpl(TicketDao ticketDao, JavaMailSender javaMailSender) {
//        this.ticketDao = ticketDao;
//        this.javaMailSender = javaMailSender;
//    }
//
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    @CacheEvict(value = "tickets", allEntries = true)
    public Ticket add(Ticket ticket) {
        LOGGER.debug("Add new ticket: {}",ticket);
        return ticketDao.add(ticket);
    }

    @Override
    @CacheEvict(value = {"tickets", "paid-tickets"}, allEntries = true)
    public void payTicket(Ticket ticket) {
        LOGGER.debug("Update ticket: {}", ticket);
        //        sendMessage(ticket);
        ticketDao.payTicket(ticket);
    }

    @Override
    @CacheEvict(value = {"tickets", "paid-tickets"}, allEntries = true)
    public void delete(String ticketId) {
        LOGGER.debug("Delete ticket with id: {}", ticketId);
        ticketDao.delete(ticketId);
    }

    @Override
    @Cacheable(value = "tickets")
    public List<Ticket> findAll () {
        System.out.println("++++++ WORKING +++++");
        LOGGER.debug("Find all tickets");
        return ticketDao.findAll();
    }

    @Override
    @Cacheable("paid-tickets")
    public List<Ticket> findAllPaidTickets() {
        System.out.println("------WORKING-------");
        LOGGER.debug("Find all paid-tickets");
        return ticketDao.findAllPaidTickets();
    }

    @Override
//    @Cacheable(cacheNames = "tickets")
    public Ticket findById (String ticketId){
        LOGGER.debug("Find ticket by Id: {}", ticketId);
        return ticketDao.findById(ticketId);
    }

    @Override
    public List<Ticket> searchTicket (LocalDateTime startDate, LocalDateTime finishDate,
                                      String cityFrom, String cityTo){
        LOGGER.debug("Search tickets by date({} - {}, {}, {})", startDate, finishDate, cityFrom, cityTo);
        return ticketDao.searchTicket(startDate, finishDate, cityFrom, cityTo);
    }


    @Override
    public List<Ticket> searchPaidTicketByDate (LocalDateTime startDate, LocalDateTime finishDate){
        LOGGER.debug("Find all paid tickets by date");
        return ticketDao.searchPaidTicketByDate(startDate, finishDate);
    }

    @Override
//    @CachePut("tickets")
    public void update(Ticket ticket) {
        LOGGER.debug("Update ticket: {}", ticket);
        ticketDao.update(ticket);
    }

    @Override
    public TicketDto sumPaidTicketCost() {
        LOGGER.debug("Find cost of all paid tickets");
        return ticketDao.sumPaidTicketCost();
    }


    private void sendMessage(Ticket payment) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("payticketapplication@gmail.com");
        mail.setTo(payment.getEmail());
        mail.setSubject("Hi");
        mail.setText("It is simple a message from my ticketTrain-application :) Have a nice a day! ");

//        javaMailSender.send(mail);
    }
}
