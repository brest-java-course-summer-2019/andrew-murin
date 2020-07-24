package com.epam.brest2019.courses.consumer;

import com.epam.brest2019.courses.consumer.config.ConsumerSoapConfig;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.soap.converter.PaymentConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ws.test.client.MockWebServiceServer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.epam.brest2019.courses.model.soap.converter.Converter.ADD;
import static com.epam.brest2019.courses.model.soap.converter.Converter.UPDATE;
import static org.springframework.ws.test.client.RequestMatchers.soapEnvelope;
import static org.springframework.ws.test.client.ResponseCreators.withSoapEnvelope;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConsumerSoapConfig.class)
public class PaymentConsumerTest implements ResourceLoaderAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumerTest.class);

    @Autowired
    private PaymentSoapConsumer paymentSoapConsumer;
    @Autowired
    private PaymentConverter paymentConverter;
    private MockWebServiceServer mockServer;
    private ResourceLoader resourceLoader;


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @BeforeEach
    public void createSoapConsumer() {
        mockServer = MockWebServiceServer.createServer(paymentSoapConsumer);
    }

    @Test
    @Order(1)
    void getAllPayment() throws IOException {
        Resource request = resourceLoader.getResource("classpath:/requests/payment/getAllPaymentRequest.xml");
        Resource response = resourceLoader.getResource("classpath:/responses/payment/getAllPaymentResponse.xml");

        mockServer.expect(soapEnvelope(request))
                .andRespond(withSoapEnvelope(response));

        paymentSoapConsumer.findAll();

        mockServer.verify();
    }

    @Test
    @Order(2)
    void getAllPaymentWithDirections() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getAllPaymentWithDirectionRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/payment/getAllPaymentWithDirectionResponse.xml");

        mockServer.expect(soapEnvelope(request))
                .andRespond(withSoapEnvelope(response));

        paymentSoapConsumer.findAllPaymentWithDirection();

        mockServer.verify();
    }

    @Test
    @Order(3)
    void getSearchByDate() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getSearchByDateRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/payment/getSearchByDateResponse.xml");

        mockServer.expect(soapEnvelope(request))
                .andRespond(withSoapEnvelope(response));

        LocalDate startDate = LocalDate.of(2019, 9, 10);
        LocalDate finishDate = LocalDate.of(2019, 9, 20);

        paymentSoapConsumer.searchPaymentByDate(startDate, finishDate);

        mockServer.verify();

    }

    @Test
    @Order(4)
    void getPaymentById() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getPaymentByIdRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/payment/getPaymentByIdResponse.xml");

        mockServer.expect(soapEnvelope(request))
                .andRespond(withSoapEnvelope(response));

        paymentSoapConsumer.findPaymentById(1);

        mockServer.verify();
    }


    @Test
    void getAddPayment() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getAddPaymentRequest.xml");


        Payment payment = createPaymentFixture(ADD);

        mockServer.expect(soapEnvelope(request));

        paymentSoapConsumer.addPayment(payment);

        mockServer.verify();
    }

    @Test
    void getUpdatePayment() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getUpdatePaymentRequest.xml");

        mockServer.expect(soapEnvelope(request));

        Payment payment = createPaymentFixture(UPDATE);

        paymentSoapConsumer.updatePayment(createPaymentFixture(UPDATE));

        mockServer.verify();
    }

    @Test
    void getDeletePayment() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getDeletePaymentRequest.xml");

        mockServer.expect(soapEnvelope(request));

        paymentSoapConsumer.deletePayment(2);

        mockServer.verify();
    }

    private Payment createPaymentFixture(String add) {
        Payment payment = new Payment();
        Ticket ticket = new Ticket();
        City fromCity = new City();
        City toCity = new City();

        fromCity.setCityId(1);
        fromCity.setCityName("BREST");
        toCity.setCityId(3);
        toCity.setCityName("VITEBSK");

        ticket.setTicketId(2);
        ticket.setTicketDate(LocalDate.of(2019, 9, 25));
        ticket.setTicketCost(new BigDecimal("10.65"));
        ticket.setFromCity(fromCity);
        ticket.setToCity(toCity);


        payment.setPaymentId(1);
        payment.setPaymentDate(LocalDate.of(2019, 9, 7));
        payment.setEmail("KlimukDmitry@gmail.com");
        payment.setTicketId(ticket);
        payment.setTicketCount(5L);
        payment.setTicketCost(new BigDecimal("2"));

        return payment;
    }

}
