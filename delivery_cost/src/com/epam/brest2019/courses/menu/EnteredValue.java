package com.epam.brest2019.courses.menu;

public interface EnteredValue {
    enum Types {EXIT, INCORRECT, VALUE}
    Types getType();
}
