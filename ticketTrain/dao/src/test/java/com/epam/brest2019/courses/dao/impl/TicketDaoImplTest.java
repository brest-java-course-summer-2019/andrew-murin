package com.epam.brest2019.courses.dao.impl;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.initDB.InitEmbeddedDataBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.epam.brest2019.courses.model.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketDaoImplTest extends AbstractTest{


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private InitEmbeddedDataBase embeddedMongo;

    @Autowired
    private TicketDao ticketDao;
    private Ticket ticket;


    @BeforeAll
    @DisplayName("It's initialize test-database")
    public void beforeAll() {
        mongoTemplate.getDb().getCollection("ticket").drop();

        String data = embeddedMongo.initEmbeddedMongo();

        ExecutableMongoScript initData = new ExecutableMongoScript(data);
        mongoTemplate.scriptOps().execute(initData, "Populate data");

        ticket = ticketDao.add(createFixture());
    }


    @Test
    public void findAll(){
        List<Ticket> tickets = ticketDao.findAll();
        log.debug("FindAll Tickets ({})", tickets);

        assertNotNull(tickets);
        assertTrue(tickets.size() > 0);
    }


    @Test
    @Order(1)
    public void findById(){
        Ticket foundTicket = ticketDao.findById(ticket.getId());
        log.debug("FindById Ticket ({})", ticket);

        assertEquals(foundTicket.getId(), ticket.getId());
    }


    @Test
    public void add(){
        List<Ticket> tickets = ticketDao.findAll();
        log.debug("Size before add ticket ({})", tickets);

        int sizeBefore = tickets.size();

        ticketDao.add(createFixture());

        assertEquals(sizeBefore + 1, ticketDao.findAll().size());
    }


    @Test
    public void updateTicket(){
        ticket.setToCity(City.GOMEL);
        log.debug("Updated ticket ({})", ticket);

        assertTrue(ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, GOMEL).isEmpty());

        ticketDao.update(ticket);

        assertNotNull(ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, GOMEL));
    }

    @Test
    public void delete(){
        List<Ticket> tickets = ticketDao.findAll();
        log.debug("Tickets before deleting of ticket ({})", tickets);

        int sizeBefore = tickets.size();

        ticketDao.delete(ticket.getId());

        assertEquals(sizeBefore, ticketDao.findAll().size() + 1);
    }


    @Test
    public void searchTicket() {
        List<Ticket> tickets = ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, MINSK);
        log.debug("searchTicket ({})", tickets);

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }



    private Ticket createFixture() {
        ticket = new Ticket();

        ticket.setTicketDate(ZonedDateTime.now());
        ticket.setFromCity(City.BREST);
        ticket.setToCity(City.MINSK);

        ticket.setTicketCost(new BigDecimal(25));

        return ticket;
    }

}
