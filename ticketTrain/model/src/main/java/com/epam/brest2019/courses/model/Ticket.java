package com.epam.brest2019.courses.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;


/**
 * POJO Ticket for model.
 */
@ApiModel(value = "Class Ticket")
@Document(collection = "ticket")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Ticket implements Serializable {

    /**
     * Ticket Id
     */
    @Id
    private String id;

    /**
     * Cost of ticket
     */
    @ApiModelProperty(value = "Cost of ticket", example = "30")
    private BigDecimal ticketCost;

    /**
     * date train
     */
    @ApiModelProperty(value = "Start date for train", example = "2020-05-19")
    private ZonedDateTime ticketDate;

    /**
     * Direction of train_from
     */
    @ApiModelProperty(value = "From city", example = "BREST")
    private City fromCity;

    /**
     * Direction of train_to
     */
    @ApiModelProperty(value = "To city", example = "MINSK")
    private City toCity;

    @ApiModelProperty(value = "Purchases date of ticket ", example = "2020-05-18")
    private ZonedDateTime paymentDate;

    @ApiModelProperty(value = "E-mail", example = "alze.andrey.1997@mail.ru")
    private String email;


    public Ticket(BigDecimal ticketCost,
                  ZonedDateTime ticketDate,
                  City fromCity,
                  City toCity,
                  ZonedDateTime paymentDate,
                  String email)
    {
        this.ticketCost = ticketCost;
        this.ticketDate = ticketDate;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.paymentDate = paymentDate;
        this.email = email;
    }
}
