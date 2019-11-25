package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


/**
 *  Ticket DAO Interface implementation
 */
@Repository
@PropertySource("classpath:/sql_query_ticket.properties")
public class TicketDaoJdbcImpl implements TicketDao {

    @Value("${ticket.findAll}")
    private String SELECT_ALL;

    @Value("${ticket.findById}")
    private String FIND_BY_ID;

    @Value("${ticket.findAllWithDirection}")
    private String SELECT_ALL_WITH_DIRECTION;

    @Value("${ticket.searchByDate}")
    private String SEARCH_BY_DATE;


    private static final String ADD = "a";
    private static final String UPDATE = "u";
    private static final String DELETE = "d";

    private static final String START_DATE = "startDate";
    private static final String FINISH_DATE = "finishDate";
    private static final String FROM_CITY = "fromCity";
    private static final String TO_CITY = "toCity";


    private final SessionFactory sessionFactory;

    private Transact transact;

    @Autowired
    public TicketDaoJdbcImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        transact = new Transact(sessionFactory);
    }


    @Override
    public void add(Ticket ticket) {
        transact.dmlQueryFixture(ticket, ADD);
    }

    @Override
    public void update(Ticket ticket) {
        transact.dmlQueryFixture(ticket, UPDATE);
    }

    @Override
    public void delete(Ticket ticketId) {
        transact.dmlQueryFixture(ticketId, DELETE);
    }

    @Override
    public List<Ticket> findAll(){
        List<Ticket> tickets = transact.sessionFixture(SELECT_ALL);
        return tickets;
    }

    @Override
    public Ticket findById(Integer ticketId) {
        Transaction transaction = null;
        Ticket ticket = null;

        try ( Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();

            ticket = session.find(Ticket.class, ticketId);

            transaction.commit();

        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
                ex.printStackTrace();
            }
        }

        return ticket;
    }

    @Override
    public List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate, Integer fromCity, Integer toCity) {
        Map map = new HashMap<>();
        map.put(START_DATE, startDate);
        map.put(FINISH_DATE, finishDate);
        map.put(FROM_CITY, fromCity);
        map.put(TO_CITY, toCity);

        List<Ticket> tickets = transact.sessionParameter(SEARCH_BY_DATE, map);
        return tickets;
    }

    @Override
    public List<Ticket> findAllWithDirection() {
//        List<Ticket> tickets = transact.sessionFixture(SELECT_ALL_WITH_DIRECTION);
//        return tickets;


        Transaction transaction = null;
        List<Ticket> tickets = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            String sql = "SELECT t.id, c.city_name AS cityFrom, s.city_name AS cityTo, t.ticket_cost, t.ticket_date FROM ticket t" +
                    " INNER JOIN city c ON t.from_city = c.id INNER JOIN city s ON t.to_city = s.id";


//            String jpql = "SELECT t" +
//                    " FROM Ticket t";
            String jpql = "SELECT t" +
                    " FROM Ticket t" +
                    " JOIN t.fromCity c";
//                    " WHERE t.fk_from_city = c.id";




//            NativeQuery query = session.createNativeQuery(SELECT_ALL_WITH_DIRECTION, "ticketSqlMapping");
            tickets = session.createQuery(jpql, Ticket.class).getResultList();


            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            ex.printStackTrace();

        }
        return tickets;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() throws IOException {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertySourcesPlaceholderConfigurer;
    }

}
