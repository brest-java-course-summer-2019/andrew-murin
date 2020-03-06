//package com.epam.brest2019.courses.web_app.SI_test;
//
//import com.epam.brest2019.courses.consumer.PaymentRestConsumer;
//import com.epam.brest2019.courses.model.Payment;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.integration.test.context.SpringIntegrationTest;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.support.JmsHeaders;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.GenericMessage;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Collections;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringIntegrationTest
//@DirtiesContext
//public class PaymentTestSI {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentTestSI.class);
//
////    @Autowired
////    private JmsTemplate jmsTemplate;
//
//    @Autowired
//    private PaymentRestConsumer consumer;
//
//    @Autowired
//    private ApplicationContext context;
//
//
//
//    @Test
//    public void testIntegration() throws Exception {
//
//        MessageChannel sendToQueue = context.getBean("sendToQueue", MessageChannel.class);
//
//        Payment payment = consumer.findById(2);
//        int sizeBefore = consumer.findAll().size();
//
//        Map<String, Object> headers = Collections.singletonMap(JmsHeaders.DESTINATION, "sendToQueue");
//        GenericMessage message = new GenericMessage(payment, headers);
//        sendToQueue.send(message);
//
////       jmsTemplate.convertAndSend("sendToQueue", payment);
//
//        int sizeAfter = consumer.findAll().size();
//
//        assertEquals(sizeBefore + 1, sizeAfter);
//    }
//
//
//
//}
