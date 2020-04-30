package com.epam.brest2019.courses.model.soap.converter;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.soap.model.city.CitySoap;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {

    public CitySoap cityConverterToSoap(City city) {
        CitySoap citySoap = new CitySoap();

        citySoap.setCityId(city.getCityId());
        citySoap.setCityName(city.getCityName());

        return citySoap;
    }


    public City cityConverter(CitySoap citySoap) {
        City city = new City();

        city.setCityId(citySoap.getCityId());
        city.setCityName(citySoap.getCityName());

        return city;
    }

}
