package com.epam.brest2019.courses.model;

/**
 * POJO City for model
 */
public class City {

    /**
     * cityId
     */
    private Integer cityId;

    /**
     * cityName
     */
    private String cityName;

    /**
     * Constructor without parameters
     */
    public City() {
    }

    /**
     * Get cityId
     *
     * @return cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * Set cityId
     *
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * Get cityName
     *
     * @return
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *Set cityName
     *
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
