package com.epam.brest2019.courses.model;

import org.junit.Assert;
import org.junit.Test;

public class CityTest {

    private static final Integer CITY_ID = 1;
    private static final String CITY_NAME = "BREST";


    @Test
    public void getCityId() {

        City city = new City();

        city.setCityId(1);

        Assert.assertNotNull(city);
        Assert.assertEquals(city.getCityId(), CITY_ID);
    }

    @Test
    public void getCityName() {
        City city = new City();

        city.setCityId(CITY_ID);
        city.setCityName(CITY_NAME);

        Assert.assertNotNull(city);
        Assert.assertEquals(city.getCityId(), CITY_ID);
        Assert.assertEquals(city.getCityName(), CITY_NAME);
    }
}
