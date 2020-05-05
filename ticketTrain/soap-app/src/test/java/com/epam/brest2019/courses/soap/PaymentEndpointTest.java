package com.epam.brest2019.courses.soap;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.soap.converter.PaymentConverter;
import com.epam.brest2019.courses.model.soap.model.payment.GetPaymentByIdResponse;
import com.epam.brest2019.courses.service.PaymentService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ws.test.server.MockWebServiceClient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.epam.brest2019.courses.model.soap.converter.Converter.UPDATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.soapEnvelope;


@SpringBootTest(classes = SoapApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentEndpointTest implements ResourceLoaderAware {


    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentEndpointTest.class);

    @Autowired
    private ApplicationContext context;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentConverter paymentConverter;

    private ResourceLoader resourceLoader;
    private MockWebServiceClient mockClient;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @BeforeEach
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(context);
    }


    @Test
    @Order(1)
    void getAllPayment() throws IOException {
        Resource request = resourceLoader.getResource("classpath:/requests/payment/getAllPaymentRequest.xml");
        Resource response = resourceLoader.getResource("classpath:/responses/payment/getAllPaymentResponse.xml");

        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
    }

    @Test
    @Order(2)
    void getAllPaymentWithDirections() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getAllPaymentWithDirectionRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/payment/getAllPaymentWithDirectionResponse.xml");

        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
    }

    @Test
    @Order(3)
    void getSearchByDate() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getSearchByDateRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/payment/getSearchByDateResponse.xml");

        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
    }

    @Test
    @Order(4)
    void getPaymentById() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getPaymentByIdRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/payment/getPaymentByIdResponse.xml");

        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
    }

    @Test
    @Order(5)
    void getFindByTicketId() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getFindByTicketIdRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/payment/getFindByTicketIdResponse.xml");

        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
    }

    @Test
    void getAddPayment() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getAddPaymentRequest.xml");

        int sizeBefore = paymentService.findAll().size();

        mockClient.sendRequest(withSoapEnvelope(request));

        int sizeAfter = paymentService.findAll().size();

        assertEquals(sizeBefore + 1, sizeAfter, "Sizes are different");
    }

    @Test
    void getUpdatePayment() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getUpdatePaymentRequest.xml");

        Payment paymentBefore = paymentService.findById(1);

        mockClient.sendRequest(withSoapEnvelope(request));

        Payment paymentAfter = paymentService.findById(1);

        assertNotEquals(paymentBefore, paymentAfter, "Payments are the same");
    }

    @Test
    void getDeletePayment() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/payment/getDeletePaymentRequest.xml");

        int sizeBefore = paymentService.findAll().size();

        mockClient.sendRequest(withSoapEnvelope(request));

        int sizeAfter = paymentService.findAll().size();

        assertEquals(sizeBefore, sizeAfter + 1, "Sizes are different");
    }
}
