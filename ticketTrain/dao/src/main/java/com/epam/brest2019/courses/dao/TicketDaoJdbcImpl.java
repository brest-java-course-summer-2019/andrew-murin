package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.*;


/**
 *  Ticket DAO Interface implementation
 */
@Repository
@PropertySource("classpath:sql_query_ticket.properties")
public class TicketDaoJdbcImpl implements TicketDao {


    @Value("${ticket.findAll}")
    private String SELECT_ALL;

    @Value("${ticket.findById}")
    private String FIND_BY_ID;

    @Value("${ticket.add}")
    private String ADD_DIRECTION;

    @Value("${ticket.update}")
    private String UPDATE;

    @Value("${ticket.delete}")
    private String DELETE;

    @Value("${ticket.findAllWithDirection}")
    private String SELECT_ALL_WITH_DIRECTION;

    @Value("${ticket.searchByDate}")
    private String SEARCH_BY_DATE;


    private final SessionFactory sessionFactory;
    private Transact transact;

    @Autowired
    public TicketDaoJdbcImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        transact = new Transact(sessionFactory);
    }

//    @Override
//    public Ticket add(Ticket ticket){
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue(TICKET_DIRECTION_FROM, ticket.getTicketDirectionFrom());
//        parameters.addValue(TICKET_DIRECTION_TO, ticket.getTicketDirectionTo());
//        parameters.addValue(TICKET_COST, ticket.getTicketCost());
//        parameters.addValue(TICKET_DATE, ticket.getTicketDate());
//
//        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
//        namedParameterJdbcTemplate.update(ADD_DIRECTION, parameters, generatedKeyHolder);
//        ticket.setTicketId(generatedKeyHolder.getKey().intValue());
//        return ticket;
//    }
//


//    @Override
//    public void update(Ticket ticket) {
//        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(ticket)))
//                .filter(this::successfullyUpdated)
//                .orElseThrow(() -> new RuntimeException("Failed to update ticket in DB"));
//    }
//
//    private boolean successfullyUpdated(int numRowsUpdated) {
//        return numRowsUpdated > 0;
//    }
//
//    @Override
//    public void delete(Integer ticketId){
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue(TICKET_ID, ticketId);
//        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
//                .filter(this::successfullyUpdated)
//                .orElseThrow(() -> new RuntimeException("Failed to delete ticket from DB"));
//    }


    @Override
    public void add(Ticket ticket) {
        transact.dmlQueryFixture(ticket, "a");
    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public void delete(Ticket ticketId) {
        transact.dmlQueryFixture(ticketId, "d");
    }

    @Override
    public List<Ticket> findAll(){
        List<Ticket> tickets = transact.sessionFixture(SELECT_ALL);
        return tickets;
    }

    @Override
    public Ticket findById(Integer ticketId) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("ticketId", ticketId);
//
//        List<Ticket> tickets = transact.sessionParameter(FIND_BY_ID, map);
//        return tickets.get(0);
    return null;
    }

    @Override
    public List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate, Integer fromCity, Integer toCity) {
        return null;
    }

    @Override
    public List<Ticket> findAllWithDirection() {
        return null;
    }

//    @Override
//    public Optional<Ticket> findById(Integer ticketId) {
//        SqlParameterSource namedParameters = new MapSqlParameterSource(TICKET_ID, ticketId);
//        List<Ticket> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
//                BeanPropertyRowMapper.newInstance(Ticket.class));
//        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
//    }
//
//    @Override
//    public List<Ticket> findAllWithDirection() {
//        List<Ticket> tickets = transact.sessionFixture(SELECT_ALL_WITH_DIRECTION);
//        return tickets;
//    }

}
