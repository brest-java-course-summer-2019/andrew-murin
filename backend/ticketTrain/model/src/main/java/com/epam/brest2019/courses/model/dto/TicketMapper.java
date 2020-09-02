package com.epam.brest2019.courses.model.dto;

import com.epam.brest2019.courses.model.Ticket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket toEntity(TicketDto ticketDto);
    TicketDto toDto(Ticket ticket);

    List<Ticket> toEntity(List<TicketDto> ticketDto);
    List<TicketDto> toDto(List<Ticket> ticket);
}
