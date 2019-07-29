package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
/**
 *  Ticket DAO Interface implementation
 */
public class TicketDaoJdbcImpl implements TicketDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select t.ticket_id, t.ticket_direction from ticket t order by 2";

    private static final String FIND_BY_ID =
            "select ticket_id, ticket_direction from ticket where ticket_id = :ticketId";

    private final static String ADD_DIRECTION =
            "insert into ticket (ticket_direction) values (:ticketDirection)";

    private final static String UPDATE =
            "update ticket set ticket_direction = :ticketDirection where ticket_id = :ticketId";

    private final static String DELETE =
            "delete from ticket where ticket_id = :ticketId";

    private final static String TICKET_ID = "ticketId";

    public TicketDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Ticket add(Ticket ticket){
        MapSqlParameterSource parametrs = new MapSqlParameterSource();
        parametrs.addValue("ticketDirection", ticket.getTicketDirection());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_DIRECTION, parametrs, generatedKeyHolder);
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
