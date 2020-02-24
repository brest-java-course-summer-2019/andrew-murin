package com.epam.brest2019.courses.web_app.config;

import com.epam.brest2019.courses.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.jms.JmsSendingMessageHandler;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class SenderConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenderConfig.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    private String destinationChannel = "queue";

    @Bean
    public MessageChannel producingChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "producingChannel")
    public MessageHandler jmsMessageHandler(JmsTemplate jmsTemplate) {

        JmsSendingMessageHandler handler =
                new JmsSendingMessageHandler(jmsTemplate);
        handler.setDestinationName(destinationChannel);
        handler.setExtractPayload(true);

        return handler;
    }

    @MessagingGateway
    public interface Sender{
        @Gateway(requestChannel = "producingChannel")
        Payment send(Payment message);
    }


}
