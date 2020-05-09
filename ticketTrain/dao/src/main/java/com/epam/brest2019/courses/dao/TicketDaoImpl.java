package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


/**
 *  Ticket DAO Interface implementation
 */
@Repository
public class TicketDaoImpl implements TicketDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDaoImpl.class);

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TicketDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<Ticket> findAll() {

        return mongoTemplate.findAll(Ticket.class);
    }

    @Override
    public List<Ticket> findAllWithDirection() {
        return null;
    }

    @Override
    public List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate, Integer fromCity, Integer toCity) {
        return null;
    }

    @Override
    public Ticket findById(Integer ticketId) {
        return null;
    }

    @Override
    public void add(Ticket ticket) {

    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public void delete(Ticket ticketId) {

    }
}
