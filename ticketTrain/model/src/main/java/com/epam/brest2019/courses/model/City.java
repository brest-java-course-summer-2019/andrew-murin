package com.epam.brest2019.courses.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * POJO City for model
 */
@Document
public class City{

    /**
     * cityId
     */
    @Id
    private String id;

    /**
     * cityName
     */
    private String cityName;

    /**
     * Constructor without parameters
     */
    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
