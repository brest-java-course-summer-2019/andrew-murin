package com.epam.brest2019.courses.model.converter;


import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

public class BigDecimalToDouble implements Converter<BigDecimal, Double> {

    @Override
    public Double convert(BigDecimal bigDecimal) {
        return bigDecimal.doubleValue();
    }
}
