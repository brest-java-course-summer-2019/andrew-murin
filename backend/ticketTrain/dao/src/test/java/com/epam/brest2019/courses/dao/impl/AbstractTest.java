package com.epam.brest2019.courses.dao.impl;

import com.epam.brest2019.courses.dao.config.MongoConfigTest;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;

@DataMongoTest
@ContextConfiguration(classes = MongoConfigTest.class)
public abstract class AbstractTest {
}
