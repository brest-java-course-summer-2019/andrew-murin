package com.epam.brest2019.courses.dao.config;

import com.epam.brest2019.courses.model.converter.DateReadConverter;
import com.epam.brest2019.courses.model.converter.DateWriteConverter;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ComponentScan
//@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

    private final List<Converter<?, ?>> converters = new ArrayList<>();

    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getDatabaseName() {
        return "ticketTrain";
    }

    @Override
    public MongoCustomConversions customConversions() {
        converters.add(new DateReadConverter());
        converters.add(new DateWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
