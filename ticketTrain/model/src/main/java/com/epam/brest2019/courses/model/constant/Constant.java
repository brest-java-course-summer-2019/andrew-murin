package com.epam.brest2019.courses.model.constant;

import com.epam.brest2019.courses.model.City;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@UtilityClass
public class Constant {

//    @UtilityClass makes all constants static
    public final LocalDateTime START_DATE =  LocalDate.of(2019,01,01).atTime(LocalTime.now());
    public final LocalDateTime FINISH_DATE = LocalDate.of(2019, 12,12).atTime(LocalTime.now());

    public final String BREST = City.BREST.name();
    public final String MINSK = City.MINSK.name();
    public final String GRODNO = City.GRODNO.name();
    public final String GOMEL = City.GOMEL.name();
    public final String MOGILEV = City.MOGILEV.name();
    public final String VITEBSK = City.VITEBSK.name();
}
