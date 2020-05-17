package com.epam.brest2019.courses.dao.impl;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.dao.config.MongoConfigTest;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
@ContextConfiguration(classes = MongoConfigTest.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketDaoImplTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoImplTest.class);

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TicketDao ticketDao;
    private Ticket ticket;



    private final static LocalDateTime START_DATE =  LocalDate.of(2019,01,01).atTime(LocalTime.now());
    private final static LocalDateTime FINISH_DATE = LocalDate.of(2019, 12,12).atTime(LocalTime.now());

    private final static String BREST = City.BREST.getCity();
    private final static String MINSK = City.MINSK.getCity();
    private final static String GRODNO = City.GRODNO.getCity();
    private final static String GOMEL = City.GOMEL.getCity();
    private final static String MOGILEV = City.MOGILEV.getCity();
    private final static String VITEBSK = City.VITEBSK.getCity();


    @BeforeAll
    @DisplayName("It's initialize test-database")
    public void beforeAll() {
        mongoTemplate.getDb().getCollection("ticket").drop();

        String data = "";

        try ( InputStream stream = this.getClass().getClassLoader().getResourceAsStream("mongo-init.txt") ) {
            byte[] bdata = FileCopyUtils.copyToByteArray(stream);
            data = new String(bdata, StandardCharsets.UTF_8);

        } catch (Exception ex){
            LOGGER.debug("Error initialization test database: {}", ex.getMessage());
        }

        ExecutableMongoScript initData = new ExecutableMongoScript(data);
        mongoTemplate.scriptOps().execute(initData, "Populate data");

        ticket = ticketDao.add(createFixture());
    }


    @Test
    public void findAll(){
        List<Ticket> tickets = ticketDao.findAll();
        LOGGER.debug("FindAll Tickets ({})", tickets);

        assertNotNull(tickets);
        assertTrue(tickets.size() > 0);
    }


    @Test
    @Order(1)
    public void findById(){
        Ticket foundTicket = ticketDao.findById(ticket.getId());
        LOGGER.debug("FindById Ticket ({})", ticket);

        assertEquals(foundTicket.getId(), ticket.getId());
    }


    @Test
    public void add(){
        List<Ticket> tickets = ticketDao.findAll();
        LOGGER.debug("Size before add ticket ({})", tickets);

        int sizeBefore = tickets.size();

        ticketDao.add(createFixture());

        assertEquals(sizeBefore + 1, ticketDao.findAll().size());
    }


    @Test
    public void updateTicket(){
        ticket.setToCity(City.GOMEL);
        LOGGER.debug("Updated ticket ({})", ticket);

        assertTrue(ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, GOMEL).isEmpty());

        ticketDao.update(ticket);

        assertNotNull(ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, GOMEL));
    }

    @Test
    public void delete(){
        List<Ticket> tickets = ticketDao.findAll();
        LOGGER.debug("Tickets before deleting of ticket ({})", tickets);

        int sizeBefore = tickets.size();

        ticketDao.delete(ticket.getId());

        assertEquals(sizeBefore, ticketDao.findAll().size() + 1);
    }


    @Test
    public void searchTicket() {
        List<Ticket> tickets = ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, MINSK);
        LOGGER.debug("searchTicket ({})", tickets);

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

