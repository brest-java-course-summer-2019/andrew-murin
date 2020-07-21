package com.epam.brest2019.courses.service.mockTest;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceMockTest.class);

    private static final Integer CITY_FROM = 1;
    private static final Integer CITY_TO = 6;


    @Mock
    private TicketDao ticketDao;

    @Captor
    private ArgumentCaptor<Ticket> ticketCaptor;

    @InjectMocks
    private TicketServiceImpl ticketService;

//    @AfterEach
//    void after(){
//        Mockito.verifyNoMoreInteractions(ticketDao);
//    }


    private  static final LocalDate START_DATE = LocalDate.of(2019, 01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019, 12,12);

    @Test
    public void find(){
        LOGGER.debug("find");

        Mockito.when(ticketDao.find()).thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.find();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(ticketDao).find();
    }

    @Test
    public void findById(){
        LOGGER.debug("findById");

        int id = 1;
        Mockito.when(ticketDao.findById(id)).thenReturn(createFixture());

        Ticket ticket = ticketService.findById(id);

        assertNotNull(ticket);
        assertEquals(CITY_TO, ticket.getToCity().getCityId());

        Mockito.verify(ticketDao).findById(id);
    }

    @Test
    public void update(){
        LOGGER.debug("update");

        ticketService.update(createFixture());

        Mockito.verify(ticketDao).update(ticketCaptor.capture());

        Ticket ticket = ticketCaptor.getValue();
        assertNotNull(ticket);
        assertEquals(ticket.getToCity().getCityId(), CITY_TO);
    }

    @Test
    public void delete(){
        LOGGER.debug("Delete");
        int id = 2;

        Ticket ticket = ticketService.findById(id);
        ticketService.delete(id);

        Mockito.verify(ticketDao).delete(ticket);
    }

    @Test
    public void add(){
        LOGGER.debug("Add");

        Ticket ticket = createFixture();

        ticketService.add(ticket);

        Mockito.verify(ticketDao, Mockito.times(1)).add(ticket);
    }

    @Test
    public void searchTicket() {
        LOGGER.debug("Search Ticket");

        Ticket ticket = createFixture();

        Mockito.when(ticketDao.searchTicket(START_DATE, FINISH_DATE, CITY_FROM, CITY_TO))
                .thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.searchTicket(START_DATE, FINISH_DATE, CITY_FROM, CITY_TO);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() - 1, 0);

        Mockito.verify(ticketDao).searchTicket(START_DATE, FINISH_DATE, CITY_FROM, CITY_TO);
    }

    @Test
    public void findWithDirection() {
        LOGGER.debug("findWithDirection");

        Mockito.when(ticketDao.findWithDirection()).thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.findWithDirection();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() - 1, 0);

        Mockito.verify(ticketDao).findWithDirection();
    }


    private Ticket createFixture(){
        Ticket ticket = new Ticket();
        City city = new City();

        city.setCityId(CITY_FROM);
        ticket.setFromCity(city);

        city.setCityId(CITY_TO);
        ticket.setToCity(city);

        return ticket;
    }


}
