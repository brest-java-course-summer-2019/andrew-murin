package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.dto.TicketDtoCost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;


/**
 *  Ticket DAO Interface implementation
 */
@Repository
@Slf4j
public class TicketDaoImpl implements TicketDao {


    private final MongoTemplate mongoTemplate;

    @Autowired
    public TicketDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }



    @Override
    public TicketDtoCost sumPaidTicketCost() {
        log.debug("Find all cost of paid tickets");
        log.debug("Sum paid ticket aggregation");

        Aggregation agg = Aggregation.newAggregation(
                match(Criteria.where("paymentDate").exists(true).and("email").exists(true)),
                group("id").sum("ticketCost").as("cost")
        );

        AggregationResults<TicketDtoCost> result = mongoTemplate.aggregate(agg, "ticket", TicketDtoCost.class);

        TicketDtoCost ticketDtoCost = new TicketDtoCost(result.getMappedResults().get(0).getCost());

        return ticketDtoCost;
    }


    @Override
    public List<Ticket> findAll() {
        log.debug("Find all tickets");

        Query query = new Query()
                .addCriteria(Criteria.where("paymentDate").exists(false));

        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class, "ticket");

        return tickets;
    }

    @Override
    public List<Ticket> findAllPaidTickets() {
        log.debug("Find all paid-tickets");

        Query query = new Query()
                .addCriteria(Criteria.where("paymentDate").exists(true));

        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class, "ticket");

        return tickets;
    }


    @Override
    public List<Ticket> searchTicket(LocalDateTime startDate, LocalDateTime finishDate, String cityFrom, String cityTo) {
        log.debug("Search tickets by date & directions " +
                        "(startDate: {}, finishDate: {}, cityFrom: {}, cityTo: {})",
                startDate, finishDate, cityFrom, cityTo);

        Query query = new Query()
                .addCriteria(Criteria.where("ticketDate").gte(startDate).lte(finishDate))
                .addCriteria(Criteria.where("fromCity").is(cityFrom))
                .addCriteria(Criteria.where("toCity").is(cityTo))
                .addCriteria(Criteria.where("paymentDate").exists(false));

        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class, "ticket");

        return tickets;
    }


    @Override
    public List<Ticket> searchPaidTicketByDate(LocalDateTime startDate, LocalDateTime finishDate) {
        log.debug("Search paid tickets by date (startDate: {}, finishDate: {})", startDate, finishDate);

        Query query = new Query()
                .addCriteria(Criteria.where("paymentDate").gte(startDate).lte(finishDate));

        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class, "ticket");

        return tickets;
    }


    @Override
    public Ticket findById(String id) {
        log.debug("Find ticket by id: ({})", id);

        Ticket ticket = mongoTemplate.findById(id, Ticket.class);

        return ticket;
    }


    @Override
    public Ticket add(Ticket ticket) {
        log.debug("Add ticket {}", ticket);
        return mongoTemplate.save(ticket, "ticket");
    }


    @Override
    public void payTicket(Ticket ticket) {
        log.debug("Pay ticket: ({})", ticket);

        Query query = new Query()
                .addCriteria(Criteria.where("id").is(ticket.getId()));

        Update update = new Update()
                .set("paymentDate", ticket.getPaymentDate())
                .set("email", ticket.getEmail());

        mongoTemplate.findAndModify(query, update, Ticket.class, "ticket");
    }

    @Override
    public void update(Ticket ticket) {
        log.debug("Update ticket, (old ticket: {})", ticket);

        Query query = new Query()
                .addCriteria(Criteria.where("id").is(ticket.getId()));


        Update update = new Update()
                .set("ticketCost", ticket.getTicketCost())
                .set("ticketDate", ticket.getTicketDate())
                .set("fromCity", ticket.getFromCity())
                .set("toCity", ticket.getToCity());

        mongoTemplate.findAndModify(query, update, Ticket.class, "ticket");
    }


    @Override
    public void delete(String id) {
        log.debug("Delete ticket by id: ({})", id);

        Query query = new Query()
                .addCriteria(Criteria.where("id").is(id));

        mongoTemplate.findAndRemove(query, Ticket.class, "ticket");
    }


}
