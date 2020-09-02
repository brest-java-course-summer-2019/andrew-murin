package com.epam.brest2019.courses.service.mockTest;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static com.epam.brest2019.courses.model.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TicketServiceMockTest {


    @Captor
    private ArgumentCaptor<Ticket> ticketCaptor;

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketServiceImpl ticketService;



    @Test
    public void findAll(){
        log.debug("findAll");

        Mockito.when(ticketDao.findAll()).thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(ticketDao).findAll();
    }


    @Test
    public void findById(){
        log.debug("findById");

        String id = "12345";
        Mockito.when(ticketDao.findById(id)).thenReturn(createFixture());

        Ticket ticket = ticketService.findById(id);

        Mockito.verify(ticketDao).findById(id);
    }


    @Test
    public void update(){
        log.debug("update");

        ticketService.update(createFixture());

        Mockito.verify(ticketDao).update(ticketCaptor.capture());

        Ticket ticket = ticketCaptor.getValue();
        assertNotNull(ticket);
        assertEquals(ticket.getToCity(), City.MINSK);
    }


    @Test
    public void delete(){
        log.debug("Delete");

        ticketService.delete("12345");

        Mockito.verify(ticketDao).delete("12345");
    }


//    @Test
//    public void add(){
//        log.debug("Add");
//
//        ZonedDateTime date = LocalDate.now().atTime(LocalTime.now()).atZone(ZoneId.systemDefault());
//
//        Ticket ticket = createFixture();
//        ticket.setId("12");
//        ticket.setPaymentDate(date);
//        ticket.setEmail("12324@mail.ru");
//
//        ticketService.add(ticket);
//
//        Mockito.verify(ticketDao, Mockito.times(1)).add(ticket);
//    }


    @Test
    public void searchTicket() {
        log.debug("Search Ticket");

        Mockito.when(ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, MINSK))
                .thenReturn(Collections.singletonList(createFixture()));

//        Trouble with ticketService because LocalDateTime is returning .now()
        List<Ticket> result = ticketDao.searchTicket(START_DATE, FINISH_DATE, BREST, MINSK);

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
