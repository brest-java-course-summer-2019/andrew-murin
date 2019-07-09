package com.epam.brest2019.courses;

import com.epam.brest2019.courses.menu.CorrectValue;
import com.epam.brest2019.courses.menu.EnteredValue;
import com.epam.brest2019.courses.menu.ExitValue;
import com.epam.brest2019.courses.menu.IncorrectValue;

import java.math.BigDecimal;
import java.util.Scanner;

class Console {
    private Scanner scanner;
    private static final String QUIT_SYMBOL = "q";

    Console(){
        this.scanner = new Scanner(System.in);
    }

    public BigDecimal getValue(String message){
        EnteredValue value = receiveValueFromConsole(message);
        CorrectValue newValue;
        if(value.getType() != EnteredValue.Types.EXIT){
            newValue = (CorrectValue)value;
            System.out.println("Value: " + newValue.getValue());
            return newValue.getValue();
        }
        System.out.println("End.");
        System.exit(1000);

        return null;
    }

    private EnteredValue receiveValueFromConsole(String message){
        EnteredValue enteredValue = new IncorrectValue();
        while(enteredValue.getType() == EnteredValue.Types.INCORRECT){
            System.out.println(message);
            enteredValue = parseInputValue(scanner.nextLine());
        }
        return enteredValue;
    }

    private EnteredValue parseInputValue(String inputValue){
        EnteredValue result = new ExitValue();
        if(!inputValue.trim().toLowerCase().equals(QUIT_SYMBOL)){
            try{
                BigDecimal value = new BigDecimal(inputValue);
                if(value.compareTo(BigDecimal.ZERO) > 0){
                    result = new CorrectValue(new BigDecimal(inputValue));
                } else {
                    throw new IllegalArgumentException();
                }
            }catch (IllegalArgumentException e){
                System.out.format("Incorrect value: %s%n", inputValue);
                result = new IncorrectValue();
            }
        }
        return result;
    }
}
