package com.epam.brest2019.courses.model;

public enum City {

    BREST, MINSK, GOMEL, GRODNO, MOGILEV, VITEBSK;

    public String getCity() {
        return this.name();
    }
}
