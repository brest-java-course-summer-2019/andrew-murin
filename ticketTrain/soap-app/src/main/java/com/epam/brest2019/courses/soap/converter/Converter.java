package com.epam.brest2019.courses.soap.converter;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;

public class Converter {

    static LocalDate dateConverter(XMLGregorianCalendar XMLDate) {
        return DatatypeConverter
                .parseDate(XMLDate.toString())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    static XMLGregorianCalendar dateToXML(LocalDate localDate) {
        XMLGregorianCalendar calendar = null;

        try {

            calendar = DatatypeFactory
                    .newInstance()
                    .newXMLGregorianCalendar(localDate.toString());

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return calendar;
    }
}
