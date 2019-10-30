package com.epam.brest2019.courses.model;

import javax.persistence.*;

/**
 * POJO City for model
 */
@Entity
@Table(name = "city")
public class City {

    /**
     * cityId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    @OneToMany(mappedBy = "ticket_direction_from")

    private Integer cityId;

    /**
     * cityName
     */
    @Column(name = "city_name")
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
