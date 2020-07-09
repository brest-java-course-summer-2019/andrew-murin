package com.epam.brest2019.courses.dao.config;

import com.epam.brest2019.courses.model.converter.BigDecimalToDouble;
import com.epam.brest2019.courses.model.converter.DateReadConverter;
import com.epam.brest2019.courses.model.converter.DateWriteConverter;
import com.epam.brest2019.courses.model.converter.DoubleToBigDecimal;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ComponentScan("com.epam.brest2019.courses.*")
public class MongoConfig extends AbstractMongoConfiguration {

    private final List<Converter<?, ?>> converters = new ArrayList<>();

//    @Bean
//    public MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getDatabaseName() {
        return "ticketTrain1";
    }

    @Override
    public MongoCustomConversions customConversions() {
        converters.add(new DateReadConverter());
        converters.add(new DateWriteConverter());
//        converters.add(new BigDecimal128Converter());
//        converters.add(new Decimal128BigDecimalConverter());
        converters.add(new DoubleToBigDecimal());
        converters.add(new BigDecimalToDouble());

        return new MongoCustomConversions(converters);
    }
}
