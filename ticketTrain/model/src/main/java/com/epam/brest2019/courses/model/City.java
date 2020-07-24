package com.epam.brest2019.courses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * POJO City for model
 */
@Entity
@Table(name = "city")
public class City{

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

//    @NotNull
    @OneToMany
    @JoinColumn(name = "from_city", foreignKey = @ForeignKey(name = "fk_from_city"))
    @JsonIgnore
    private List<Ticket> cityFrom;
    /**
     * Direction of train_to
     */
//    @NotNull
    @OneToMany
    @JoinColumn(name = "to_city", foreignKey = @ForeignKey(name = "fk_to_city"))
    @JsonIgnore
    private List<Ticket> cityTo;


    /**
     * Constructor without parameters
     */
    public City() {
    }

    public List<Ticket> getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(List<Ticket> cityFrom) {
        this.cityFrom = cityFrom;
    }

    public List<Ticket> getCityTo() {
        return cityTo;
    }

    public void setCityTo(List<Ticket> cityTo) {
        this.cityTo = cityTo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(cityId, city.cityId) &&
                Objects.equals(cityName, city.cityName) &&
                Objects.equals(cityFrom, city.cityFrom) &&
                Objects.equals(cityTo, city.cityTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName, cityFrom, cityTo);
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
