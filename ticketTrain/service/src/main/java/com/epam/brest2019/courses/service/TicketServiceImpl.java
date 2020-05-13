package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ticket.class);

    private TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public void add(Ticket ticket) {
        LOGGER.debug("Add new ticket: {}",ticket);
        ticketDao.add(ticket);
    }

    @Override
    public void update(Ticket ticket) {
        LOGGER.debug("Update ticket: {}", ticket);
        ticketDao.update(ticket);
    }

    @Override
    public void delete(String ticketId) {
        LOGGER.debug("Delete ticket with id: {}", ticketId);
        ticketDao.delete(ticketId);
    }

    @Override
    public List<Ticket> findAll() {
        LOGGER.debug("Find all tickets");
        return ticketDao.findAll();
    }

    @Override
    public Ticket findById(String ticketId) {
        LOGGER.debug("Find ticket by Id: {}", ticketId);
        return ticketDao.findById(ticketId);
    }

    @Override
    public List<Ticket> searchTicket(LocalDateTime startDate, LocalDateTime finishDate,
                                     String cityFrom, String cityTo) {
        LOGGER.debug("Search tickets by date({} - {}, {}, {})", startDate, finishDate, cityFrom, cityTo);
        return ticketDao.searchTicket(startDate,finishDate, cityFrom, cityTo);
    }


    @Override
    public List<Ticket> searchPaidTicketByDate(LocalDateTime startDate, LocalDateTime finishDate) {

        return ticketDao.searchPaidTicketByDate(startDate, finishDate);
    }

}
