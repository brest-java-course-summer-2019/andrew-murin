package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;

import java.util.List;

public interface TicketDao {

    Ticket add(Ticket ticket);

    void update(Ticket ticket);

    void delete(Integer ticket);

    List<Ticket> findAll();
}
