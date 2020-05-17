package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 *  Ticket DAO Interface implementation
 */
@Repository
public class TicketDaoImpl implements TicketDao {


    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoImpl.class);

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TicketDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<Ticket> findAll() {
        LOGGER.debug("Find all tickets");
        return mongoTemplate.findAll(Ticket.class);
    }


    @Override
    public List<Ticket> searchTicket(LocalDateTime startDate, LocalDateTime finishDate, String fromCity, String toCity) {
        LOGGER.debug("Search tickets: " +
                "startDate {},finishDate {}, fromCity {}, toCity{}", startDate, finishDate, fromCity, toCity);

        Query query = new Query()
                .addCriteria(Criteria.where("ticketDate").gte(startDate).lte(finishDate))
                .addCriteria(Criteria.where("fromCity").is(fromCity))
                .addCriteria(Criteria.where("toCity").is(toCity));

        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class, "ticket");

        return tickets;
    }


    @Override
    public List<Ticket> searchPaidTicketByDate(LocalDateTime startDate, LocalDateTime finishDate) {
        LOGGER.debug("Search paidTicket by startDate {}, finishDate{}", startDate, finishDate);

        Query query = new Query()
                .addCriteria(Criteria.where("paymentDate").gte(startDate).lte(finishDate));

        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class, "ticket");

        return tickets;
    }


    @Override
    public Ticket findById(String ticketId) {
        LOGGER.debug("Find ticket by ticketId {}", ticketId);

        Ticket ticket = mongoTemplate.findById(ticketId, Ticket.class);

        return ticket;
    }


    @Override
    public Ticket add(Ticket ticket) {
        LOGGER.debug("Add ticket {}", ticket);
        return mongoTemplate.save(ticket, "ticket");
    }


    @Override
    public void update(Ticket ticket) {
        LOGGER.debug("Pay/update ticket {}", ticket);

        Query query = new Query()
                .addCriteria(Criteria.where("id").is(ticket.getId()));

        Update update = new Update()
                .set("paymentDate", ticket.getPaymentDate())
                .set("email", ticket.getEmail());

        mongoTemplate.updateFirst(query, update, Ticket.class, "ticket");
    }


    @Override
    public void delete(String ticketId) {
        LOGGER.debug("Delete ticket by ticketId {}", ticketId);

        Query query = new Query()
                .addCriteria(Criteria.where("id").is(ticketId));

        mongoTemplate.findAndRemove(query, Ticket.class, "ticket");
    }


}
