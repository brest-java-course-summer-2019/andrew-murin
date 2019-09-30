package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 *  Ticket DAO Interface implementation
 */
@Repository
@PropertySource("classpath:sql_query_ticket.properties")
public class TicketDaoJdbcImpl implements TicketDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    @Value("${ticket.countTicket}")
    private String TICKET_COUNT_AGGREGATE;

    @Value("${ticket.countTicketSum}")
    private String TICKET_SUM_AGGREGATE;



    //Fields of Models
    private final static String TICKET_ID = "ticketId";
    private final static String TICKET_DIRECTION_FROM = "ticketDirectionFrom";
    private final static String TICKET_DIRECTION_TO = "ticketDirectionTo";
    private final static String TICKET_COST = "ticketCost";
    private final static String TICKET_DATE = "ticketDate";
//    private final static String TICKET_COUNT = "countTicket";
//    private final static String TICKET_SUM = "ticketSum";

    //Fields for search
    private final static String TICKET_SEARCH_START_DATE = "startDate";
    private final static String TICKET_SEARCH_FINISH_DATE = "finishDate";
    private final static String TICKET_SEARCH_FROM = "directionFrom";
    private final static String TICKET_SEARCH_TO = "directionTo";

    //Fields of Ticket-entity in DB
    private final static String DB_TICKET_ID = "ticket_id";
    private final static String DB_TICKET_DIRECTION_FROM = "ticket_direction_from";
    private final static String DB_TICKET_DIRECTION_TO = "ticket_direction_to";
    private final static String DB_TICKET_COST = "ticket_cost";
    private final static String DB_TICKET_DATE = "ticket_date";


    public TicketDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Ticket add(Ticket ticket){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(TICKET_DIRECTION_FROM, ticket.getTicketDirectionFrom());
        parameters.addValue(TICKET_DIRECTION_TO, ticket.getTicketDirectionTo());
        parameters.addValue(TICKET_COST, ticket.getTicketCost());
        parameters.addValue(TICKET_DATE, ticket.getTicketDate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_DIRECTION, parameters, generatedKeyHolder);
        ticket.setTicketId(generatedKeyHolder.getKey().intValue());
        return ticket;
    }

    @Override
    public List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate,
                                     Integer directionFrom, Integer directionTo) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(TICKET_SEARCH_START_DATE, startDate);
        parameters.addValue(TICKET_SEARCH_FINISH_DATE, finishDate);
        parameters.addValue(TICKET_SEARCH_FROM, directionFrom);
        parameters.addValue(TICKET_SEARCH_TO, directionTo);

        List<Ticket> tickets = namedParameterJdbcTemplate.query(SEARCH_BY_DATE, parameters,
                BeanPropertyRowMapper.newInstance(Ticket.class));
        return tickets;
    }


    @Override
    public void update(Ticket ticket) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(ticket)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update ticket in DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer ticketId){
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TICKET_ID, ticketId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete ticket from DB"));
    }


    @Override
    public List<Ticket> findAll(){
        List<Ticket> tickets =
                namedParameterJdbcTemplate.query(SELECT_ALL, new TicketRowMapper());
        return tickets;
    }

    @Override
    public Optional<Ticket> findById(Integer ticketId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(TICKET_ID, ticketId);
        List<Ticket> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Ticket.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<Ticket> findAllWithDirection() {
        List<Ticket> tickets =  namedParameterJdbcTemplate.query(SELECT_ALL_WITH_DIRECTION,
                BeanPropertyRowMapper.newInstance(Ticket.class));
        return tickets;
    }

    @Override
    public List<Ticket> countTicket() {
        List<Ticket> tickets = namedParameterJdbcTemplate.query(TICKET_COUNT_AGGREGATE,
                BeanPropertyRowMapper.newInstance(Ticket.class));
        return tickets;
    }

    @Override
    public List<Ticket> ticketSum() {
        List<Ticket> tickets = namedParameterJdbcTemplate.query(TICKET_SUM_AGGREGATE,
                BeanPropertyRowMapper.newInstance(Ticket.class));
        return tickets;
    }

    private class TicketRowMapper implements RowMapper<Ticket>{
        @Override
        public Ticket mapRow(ResultSet resultSet, int i) throws SQLException{
            Ticket ticket = new Ticket();
            ticket.setTicketId(resultSet.getInt(DB_TICKET_ID));
            ticket.setTicketDirectionFrom(resultSet.getInt(DB_TICKET_DIRECTION_FROM));
            ticket.setTicketDirectionTo(resultSet.getInt(DB_TICKET_DIRECTION_TO));
            ticket.setTicketCost(resultSet.getBigDecimal(DB_TICKET_COST));
            ticket.setTicketDate(resultSet.getDate(DB_TICKET_DATE).toLocalDate());
            return ticket;
        }
    }


}
