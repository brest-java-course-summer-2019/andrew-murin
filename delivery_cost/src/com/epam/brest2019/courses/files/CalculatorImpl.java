package com.epam.brest2019.courses.files;

import java.math.BigDecimal;
import java.util.Map;

public class CalculatorImpl implements Calculator{
    private Map<Integer, BigDecimal> mapKm = null;
    private Map<Integer, BigDecimal> mapKg = null;
    private BigDecimal distance;
    private BigDecimal weight;
    private BigDecimal costKm;
    private BigDecimal costKg;
    private BigDecimal cost;

    public CalculatorImpl(Map<Integer, BigDecimal> mapKm,
                          Map<Integer, BigDecimal> mapKg,
                          BigDecimal distance,
                          BigDecimal weight) {
        this.mapKm = mapKm;
        this.mapKg = mapKg;
        this.distance = distance;
        this.weight = weight;
        this.cost = calculateCost();
    }

    public BigDecimal calculateCost() {
        for(Integer key : mapKm.keySet()){
            if(distance.intValue() <= key || key == mapKm.size()){
                costKm = (mapKm.get(key).multiply(distance));
                break;
            }
        }

        for(Integer key : mapKg.keySet()){
            if(weight.intValue() <= key || key == mapKg.size()){
                costKg = (mapKg.get(key).multiply(weight));
                break;
            }
        }
        return costKm.add(costKg);
    }

    public BigDecimal getCost() {
        return cost;
    }
}
