package com.epam.brest2019.courses.consumer.config;

import com.epam.brest2019.courses.consumer.PaymentSoapConsumer;
import com.epam.brest2019.courses.consumer.TicketSoapConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@ComponentScan(basePackages = "com.epam.brest2019.courses.model.soap.*")
public class ConsumerSoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setProcessExternalEntities(true);

        marshaller.setContextPaths("com.epam.brest2019.courses.model.soap.model.city",
                                    "com.epam.brest2019.courses.model.soap.model.ticket",
                                    "com.epam.brest2019.courses.model.soap.model.payment");
        return marshaller;
    }

    @Bean
    public TicketSoapConsumer ticketSoapConsumer(Jaxb2Marshaller marshaller) {
        TicketSoapConsumer consumer = new TicketSoapConsumer();

        consumer.setDefaultUri("http://localhost:8088/ws/");
        consumer.setMarshaller(marshaller);
        consumer.setUnmarshaller(marshaller);

        return consumer;
    }

    @Bean
    public PaymentSoapConsumer paymentSoapConsumer(Jaxb2Marshaller marshaller) {
        PaymentSoapConsumer consumer = new PaymentSoapConsumer();

        consumer.setDefaultUri("http://localhost:8088/ws/");
        consumer.setMarshaller(marshaller);
        consumer.setUnmarshaller(marshaller);

        return consumer;
    }

}
