package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.dto.TicketDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@Transactional
public class TicketServiceImpl implements TicketService {


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
        log.debug("Add new ticket: {}",ticket);
        return ticketDao.add(ticket);
    }

    @Override
    @CacheEvict(value = {"tickets", "paid-tickets"}, allEntries = true)
    public void payTicket(Ticket ticket) {
        log.debug("Pay ticket: ({})", ticket);
        //        sendMessage(ticket);
        ticketDao.payTicket(ticket);
    }

    @Override
    @CacheEvict(value = {"tickets", "paid-tickets"}, allEntries = true)
    public void delete(String id) {
        log.debug("Delete ticket by id: ({})", id);
        ticketDao.delete(id);
    }

    @Override
    @Cacheable(value = "tickets")
    public List<Ticket> findAll () {
        log.debug("Find all tickets. Next values will have cached.");
        return ticketDao.findAll();
    }

    @Override
    @Cacheable("paid-tickets")
    public List<Ticket> findAllPaidTickets() {
        log.debug("Find all paid-tickets. Next values will have cached.");
        return ticketDao.findAllPaidTickets();
    }

    @Override
    public Ticket findById (String id){
        log.debug("Find ticket by id: ({})", id);
        return ticketDao.findById(id);
    }

    @Override
    @CacheEvict(value = {"tickets"}, allEntries = true)
    public List<Ticket> searchTicket (LocalDateTime startDate, LocalDateTime finishDate,
                                      String cityFrom, String cityTo){
        log.debug("Search tickets by date & directions " +
                        "(startDate: {}, finishDate: {}, cityFrom: {}, cityTo: {})",
                startDate, finishDate, cityFrom, cityTo);

        return ticketDao.searchTicket(startDate, finishDate, cityFrom, cityTo);
    }


    @Override
    public List<Ticket> searchPaidTicketByDate (LocalDateTime startDate, LocalDateTime finishDate){
        log.debug("Search paid tickets by date (startDate: {}, finishDate: {})", startDate, finishDate);
        return ticketDao.searchPaidTicketByDate(startDate, finishDate);
    }

    @Override
    @CacheEvict(value = {"tickets"}, allEntries = true)
    public void update(Ticket ticket) {
        log.debug("Update ticket, (old ticket: {})", ticket);
        ticketDao.update(ticket);
    }

    @Override
    public TicketDto sumPaidTicketCost() {
        log.debug("Find all cost of paid tickets");
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
