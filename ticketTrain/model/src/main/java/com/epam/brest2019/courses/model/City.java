package com.epam.brest2019.courses.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Column(name = "id")
    private Integer cityId;

    /**
     * cityName
     */
    @Column(name = "city_name")
    private String cityName;

    @NotNull
    @OneToMany
    @JoinColumn(name = "from_city", foreignKey = @ForeignKey(name = "fk_from_city"))
    private List<Ticket> city_from;
    /**
     * Direction of train_to
     */
    @NotNull
    @OneToMany
    @JoinColumn(name = "to_city", foreignKey = @ForeignKey(name = "fk_to_city"))
    private List<Ticket> city_to;


    /**
     * Constructor without parameters
     */
    public City() {
    }

    public List<Ticket> getCity_from() {
        return city_from;
    }

    public void setCity_from(List<Ticket> city_from) {
        this.city_from = city_from;
    }

    public List<Ticket> getCity_to() {
        return city_to;
    }

    public void setCity_to(List<Ticket> city_to) {
        this.city_to = city_to;
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
