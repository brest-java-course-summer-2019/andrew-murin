//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.28 at 11:43:19 AM MSK 
//


package com.epam.brest2019.courses.soap.model.ticket;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listOfTicket" type="{http://epam.com/brest2019/courses/soap/model/ticket}ticketSoap" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listOfTicket"
})
@XmlRootElement(name = "getAllTicketWithDirectionResponse")
public class GetAllTicketWithDirectionResponse {

    @XmlElement(required = true)
    protected List<TicketSoap> listOfTicket;

    /**
     * Gets the value of the listOfTicket property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listOfTicket property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfTicket().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TicketSoap }
     * 
     * 
     */
    public List<TicketSoap> getListOfTicket() {
        if (listOfTicket == null) {
            listOfTicket = new ArrayList<TicketSoap>();
        }
        return this.listOfTicket;
    }

}
