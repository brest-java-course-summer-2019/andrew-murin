package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void delete(Integer ticketId) {
        LOGGER.debug("Delete ticket with id: {}", ticketId);
        Ticket ticket = ticketDao.findById(ticketId);
        ticketDao.delete(ticket);
    }

    @Override
    public List<Ticket> find() {
        LOGGER.debug("Find  tickets");
        return ticketDao.find();
    }

    @Override
    public Ticket findById(Integer ticketId) {
        LOGGER.debug("Find ticket by Id: {}", ticketId);
        return ticketDao.findById(ticketId);
    }

    @Override
    public List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate,
                                     Integer directionFrom, Integer directionTo) {
        LOGGER.debug("Search tickets by date({} - {}, {}, {})", startDate, finishDate, directionFrom, directionTo);
        return ticketDao.searchTicket(startDate,finishDate, directionFrom, directionTo);
    }

    @Override
    public List<Ticket> findWithDirection() {
        LOGGER.debug("Find  tickets with direction()");
        return ticketDao.findWithDirection();
    }

}
