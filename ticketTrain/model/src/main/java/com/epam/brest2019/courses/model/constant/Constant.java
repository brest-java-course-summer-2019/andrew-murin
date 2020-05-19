package com.epam.brest2019.courses.model.constant;

import com.epam.brest2019.courses.model.City;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Constant {

    public final static LocalDateTime START_DATE =  LocalDate.of(2019,01,01).atTime(LocalTime.now());
    public final static LocalDateTime FINISH_DATE = LocalDate.of(2019, 12,12).atTime(LocalTime.now());

    public final static String BREST = City.BREST.getCity();
    public final static String MINSK = City.MINSK.getCity();
    public final static String GRODNO = City.GRODNO.getCity();
    public final static String GOMEL = City.GOMEL.getCity();
    public final static String MOGILEV = City.MOGILEV.getCity();
    public final static String VITEBSK = City.VITEBSK.getCity();
}
