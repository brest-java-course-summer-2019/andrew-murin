package com.epam.brest2019.courses.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
public class CityTest {

    private static final Integer CITY_ID = 1;
    private static final String CITY_NAME = "BREST";


    @Test
    public void getCityId() {

        City city = new City();

        city.setCityId(1);

        assertNotNull(city);
        assertEquals(city.getCityId(), CITY_ID);
    }

    @Test
    public void getCityName() {
        City city = new City();

        city.setCityId(CITY_ID);
        city.setCityName(CITY_NAME);

        assertNotNull(city);
        assertEquals(city.getCityId(), CITY_ID);
        assertEquals(city.getCityName(), CITY_NAME);
    }
}
