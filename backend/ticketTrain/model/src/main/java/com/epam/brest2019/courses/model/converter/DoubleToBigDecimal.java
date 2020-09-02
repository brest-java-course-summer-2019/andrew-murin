package com.epam.brest2019.courses.model.converter;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

public class DoubleToBigDecimal implements Converter<Double, BigDecimal> {

    @Override
    public BigDecimal convert(Double aDouble) {
        return BigDecimal.valueOf(aDouble);
    }
}
