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

    private final static String TICKET_ID = "ticketId";
    private final static String TICKET_DIRECTION = "ticketDirection";
    private final static String TICKET_COST = "cost";
    private final static String TICKET_DATE = "localDate";

    public TicketDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Ticket add(Ticket ticket){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(TICKET_DIRECTION, ticket.getTicketDirection());
        parameters.addValue(TICKET_COST, ticket.getCost());
        parameters.addValue(TICKET_DATE, ticket.getLocalDate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_DIRECTION, parameters, generatedKeyHolder);
        ticket.setTicketId(generatedKeyHolder.getKey().intValue());
        return ticket;
    }

    @Override
    public void update(Ticket ticket) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(ticket)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update department in DB"));
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


    public Optional<Ticket> findById(Integer ticketId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(TICKET_ID, ticketId);
        List<Ticket> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Ticket.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class TicketRowMapper implements RowMapper<Ticket>{
        @Override
        public Ticket mapRow(ResultSet resultSet, int i) throws SQLException{
            Ticket ticket = new Ticket();
            ticket.setTicketId(resultSet.getInt("ticket_id"));
            ticket.setTicketDirection(resultSet.getString("ticket_direction"));
            return ticket;
        }
    }



}
