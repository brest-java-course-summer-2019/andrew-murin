//package com.epam.brest2019.courses;
//
//import com.epam.brest2019.courses.files.CSVFileReader;
//import com.epam.brest2019.courses.files.FileReader;
//
//import java.io.FileNotFoundException;
//import java.math.BigDecimal;
//import java.util.Map;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        FileReader fileReader = new CSVFileReader();
//        Map<Integer, BigDecimal> kgs = fileReader.readDate("resources/price_per_km.csv");
//        if(kgs == null || kgs.isEmpty()){
//            throw new FileNotFoundException();
//        }
//
//
//
//        BigDecimal weight;
//        BigDecimal distance;
//        BigDecimal pricePerKg = new BigDecimal("30");
//        BigDecimal pricePerKm = new BigDecimal("50");
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Input weight(kg) or 'q' for exit");
//        String inputStrng = scanner.nextLine();
//
//        if(!inputStrng.toLowerCase().equals("q")){
//            weight = new BigDecimal(inputStrng);
//        }else{
//            System.out.println("\nBye!");
//            return;
//        }
//        inputStrng = scanner.nextLine();
//
//
//        if(!inputStrng.toLowerCase().equals("q")){
//            distance = new BigDecimal(inputStrng);
//            BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
//            System.out.println(price);
//        }else{
//            System.out.println("\nBye!");
//            return;
//        }
//
//    }
//}
//
