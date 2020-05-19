package com.epam.brest2019.courses.service.mockTest;

import com.epam.brest2019.courses.dao.TicketDaoImpl;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static com.epam.brest2019.courses.model.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class TicketServiceMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceMockTest.class);


    @Captor
    private ArgumentCaptor<Ticket> ticketCaptor;

    @Mock
    private TicketDaoImpl ticketDao;
    @InjectMocks
    private TicketServiceImpl ticketService;



    @Test
    public void findAll(){
        LOGGER.debug("findAll");

        Mockito.when(ticketDao.findAll()).thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(ticketDao).findAll();
    }


    @Test
    public void findById(){
        LOGGER.debug("findById");

        String id = "12345";
        Mockito.when(ticketDao.findById(id)).thenReturn(createFixture());

        Ticket ticket = ticketService.findById(id);

        Mockito.verify(ticketDao).findById(id);
    }


    @Test
    public void update(){
        LOGGER.debug("update");

        ticketService.update(createFixture());

        Mockito.verify(ticketDao).update(ticketCaptor.capture());

        Ticket ticket = ticketCaptor.getValue();
        assertNotNull(ticket);
        assertEquals(ticket.getToCity(), City.MINSK);
    }


    @Test
    public void delete(){
        LOGGER.debug("Delete");

        ticketService.delete("12345");

        Mockito.verify(ticketDao).delete("12345");
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

        Mockito.when(ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, MINSK))
                .thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.searchTicket(START_DATE, FINISH_DATE, BREST, MINSK);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() - 1, 0);

        Mockito.verify(ticketDao).searchTicket(START_DATE, FINISH_DATE, BREST, MINSK);
    }



    private Ticket createFixture() {
        Ticket ticket = new Ticket();

        ticket.setTicketDate(ZonedDateTime.now());
        ticket.setFromCity(City.BREST);
        ticket.setToCity(City.MINSK);

        ticket.setTicketCost(new BigDecimal(25));

        return ticket;
    }

}
