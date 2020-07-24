package com.epam.brest2019.courses;

import com.epam.brest2019.courses.files.CSVFileReader;
import com.epam.brest2019.courses.files.CalculatorImpl;
import com.epam.brest2019.courses.files.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;


public class Main{
    private static final String PATH_PER_KM = "price_per_km.csv";
    public static void main(String[] args) throws IOException {

        Map<Integer, BigDecimal> mapKm;
        Map<Integer, BigDecimal> mapKg;
        BigDecimal distance;
        BigDecimal weight;
        BigDecimal priceForKg;

        FileReader fileReader = new CSVFileReader();
        Console console = new Console();



        mapKm = fileReader.readData(PATH_PER_KM);
        mapKg = fileReader.readData(PATH_PER_KM);
        if (mapKm == null && mapKg == null || mapKm.isEmpty() && mapKg.isEmpty()) {
            throw new FileNotFoundException("File with prices per_km or per_kg not found.");
        }


        distance = console.getValue("Enter distance in km or q for quit");
        weight = console.getValue("Enter weight in kg or q for quit");


//        for(Integer key : map.keySet()){
//            if(distance.intValue() < key){
//                System.out.println(map.get(key).multiply(weight).multiply(priceForKg));
//            }1
//        }

        CalculatorImpl paymentPrice = new CalculatorImpl(mapKm, mapKg,distance, weight);
        System.out.println(paymentPrice.getCost());
    }
}