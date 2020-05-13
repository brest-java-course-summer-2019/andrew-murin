package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ticket.class);

    private TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket add(Ticket ticket) {
        LOGGER.debug("Add new ticket: {}",ticket);
        return ticketDao.add(ticket);
    }

    @Override
    public void update(Ticket ticket) {
        LOGGER.debug("Update ticket: {}", ticket);
        ticketDao.update(ticket);
    }

    @Override
    public void delete(Integer ticketId) {
        LOGGER.debug("Delete ticket with id: {}", ticketId);
        ticketDao.delete(ticketId);
    }

    @Override
    public List<Ticket> findAll() {
        LOGGER.debug("Find all tickets");
        return ticketDao.findAll();
    }

    @Override
    public Ticket findById(Integer ticketId) {
        LOGGER.debug("Find ticket by Id: {}", ticketId);
        return ticketDao.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Failed to get ticket from DB"));
    }

    @Override
    public List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate,
                                     Integer directionFrom, Integer directionTo) {
        LOGGER.debug("Search tickets by date({} - {}, {}, {})", startDate, finishDate, directionFrom, directionTo);
        return ticketDao.searchTicket(startDate,finishDate, directionFrom, directionTo);
    }

    @Override
    public List<Ticket> findAllWithDirection() {
        LOGGER.debug("Find all tickets with direction()");
        return ticketDao.findAllWithDirection();
    }

}
