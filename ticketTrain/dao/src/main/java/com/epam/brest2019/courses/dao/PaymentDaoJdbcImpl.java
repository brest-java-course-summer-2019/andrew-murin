package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@PropertySource("classpath:sql_query_payment.properties")
public class PaymentDaoJdbcImpl implements PaymentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${payment.findAll}")
    private String SELECT_ALL;

    @Value("${payment.findById}")
    private String FIND_BY_ID;

    @Value("${payment.findByTicketId}")
    private String FIND_BY_TICKET_ID;

    @Value("${payment.add}")
    private String ADD_PAYMENT;

    @Value("${payment.update}")
    private String UPDATE;

    @Value("${payment.delete}")
    private String DELETE;


    private static final String TICKET_ID = "ticketId";
    private static final String PAYMENT_ID = "paymentId";
    private static final String LOCAL_DATE = "localDate";


    public PaymentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments =
            namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Payment.class));
        return payments;
    }

    @Override
    public List<Payment> findByTicketId(Integer ticketId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(TICKET_ID, ticketId);
        List<Payment> results = namedParameterJdbcTemplate.query(FIND_BY_TICKET_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Payment.class));
        return results;
    }

    @Override
    public Optional<Payment> findById(Integer paymentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PAYMENT_ID, paymentId);
        List<Payment> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Payment.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Payment add(Payment payment) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(LOCAL_DATE, payment.getLocalDate());
        parameters.addValue(TICKET_ID, payment.getTicketId());

        KeyHolder generateKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_PAYMENT, parameters, generateKeyHolder);
        payment.setPaymentId(generateKeyHolder.getKey().intValue());
        return payment;
    }

    @Override
    public void update(Payment payment) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(payment)))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update payment in DB"));
    }

    @Override
    public void delete(Integer paymentId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(PAYMENT_ID, paymentId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete payment from DB"));
    }

    private boolean succesfullyUpdated(int numRowsUpdated){
        return numRowsUpdated > 0;
    }
}
