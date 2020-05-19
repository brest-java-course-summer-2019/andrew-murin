package com.epam.brest2019.courses.dao.config;

import com.epam.brest2019.courses.dao.TicketDaoImpl;
import com.epam.brest2019.courses.model.converter.DateReadConverter;
import com.epam.brest2019.courses.model.converter.DateWriteConverter;
import com.epam.brest2019.courses.model.initDB.InitEmbeddedDataBase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@Import({TicketDaoImpl.class, InitEmbeddedDataBase.class})
public class MongoConfigTest {

    private final List<Converter<?, ?>> converters = new ArrayList<>();


    @Bean
    public MongoCustomConversions customConversions() {
        converters.add(new DateReadConverter());
        converters.add(new DateWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
