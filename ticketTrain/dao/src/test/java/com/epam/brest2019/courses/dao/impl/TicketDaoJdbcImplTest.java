//package com.epam.brest2019.courses.dao;
//
//import com.epam.brest2019.courses.model.Ticket;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
//public class TicketDaoJdbcImplTest {
//    //BREST
//    private static final Integer CITY_FROM = 1;
//    //MINSK
//    private static final Integer CITY_TO = 2;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoJdbcImplTest.class);
//
//    @Autowired
//    private TicketDao ticketDao;
//
//    @Test
//    public void findAll(){
//        List<Ticket> tickets = ticketDao.findAll();
//        assertNotNull(tickets);
//        assertTrue(tickets.size() > 0);
//    }
//
////    @Test
////    public void findById(){
////        LOGGER.debug("Add Ticket: {}",Ticket.class);
////        Ticket ticket = ticketDao.findById(1).get();
////
////        ticket.setTicketDirectionFrom(CITY_FROM);
////        ticket.setTicketDirectionTo(CITY_TO);
////
////        assertNotNull(ticket);
////        assertEquals(1, (int) ticket.getTicketId());
////        assertEquals(ticket.getTicketDirectionFrom(),(CITY_FROM));
////        assertEquals(ticket.getTicketDirectionTo(),(CITY_TO));
////    }
////
////    @Test
////    public void addTicket(){
////        Ticket testTicket = new Ticket();
////        testTicket.setTicketDirectionFrom(CITY_FROM);
////        testTicket.setTicketDirectionTo(CITY_TO);
////
////        Ticket newTicket = ticketDao.add(testTicket);
////
////        assertNotNull(newTicket.getTicketId());
////    }
////
////    @Test
////    public void updateTicket(){
////        LOGGER.debug("Add Ticket: {}",Ticket.class);
////        Ticket newTicket = new Ticket();
////        newTicket.setTicketDirectionFrom(CITY_FROM);
////        newTicket.setTicketDirectionTo(CITY_TO);
////        newTicket = ticketDao.add(newTicket);
////        //newTicket.setDirection(NEW_FLIGHT);
////        ticketDao.update(newTicket);
////        Ticket updatedTicket = ticketDao.findById(newTicket.getTicketId()).get();
////
////        assertEquals(newTicket.getTicketId(), updatedTicket.getTicketId());
////        assertEquals(newTicket.getTicketDirectionFrom(), updatedTicket.getTicketDirectionFrom());
////        assertEquals(newTicket.getTicketDirectionTo(), updatedTicket.getTicketDirectionTo());
////
////    }
////
////    @Test
////    public void deleteTicket(){
////        Ticket ticket = new Ticket(CITY_FROM, CITY_TO);
////        ticket = ticketDao.add(ticket);
////
////        List<Ticket> tickets = ticketDao.findAll();
////
////        int sizeBefore = tickets.size();
////
////        ticketDao.delete(ticket.getTicketId());
////        assertEquals((sizeBefore - 1), ticketDao.findAll().size());
////    }
////
////    @Test
////    public void searchTicket() {
////        LocalDate startDate =  LocalDate.of(2019,01,01);
////        LocalDate finishDate = LocalDate.of(2019, 12,12);
////
////        List<Ticket> tickets = ticketDao.searchTicket(startDate, finishDate, CITY_FROM, CITY_TO);
////
////        assertNotNull(tickets);
////        assertFalse(tickets.isEmpty());
////    }
////
////    @Test
////    public void findAllWithDirection() {
////        List<Ticket> tickets = ticketDao.findAllWithDirection();
////
////        assertNotNull(tickets);
////        assertFalse(tickets.isEmpty());
////        assertTrue(tickets.size() > 0);
////    }
//
//
//
//}
//
