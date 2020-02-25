package com.epam.brest2019.courses.web_app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.messaging.MessageChannel;

import javax.jms.ConnectionFactory;

@Configuration
@EnableIntegration
public class SenderConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenderConfig.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private MessageConverter messageConverter;

    @Bean
    public MessageChannel sendToQueue() {
        return new DirectChannel();
    }

    private String destinationChannel = "queue";

//    @Bean
//    @ServiceActivator(inputChannel = "producingChannel")
//    public MessageHandler jmsMessageHandler(JmsTemplate jmsTemplate) {
//
//        JmsSendingMessageHandler handler =
//                new JmsSendingMessageHandler(jmsTemplate);
//
//        handler.setDestinationName(destinationChannel);
//
//        return handler;

    @Bean
    public IntegrationFlow jmsInboundFlow() {
        return IntegrationFlows
                .from(Jms.inboundAdapter(connectionFactory)
                        .destination("sendToQueue"), e ->
                            e.poller(Pollers
                                    .fixedDelay(5000)
                                    .maxMessagesPerPoll(2)))
                .handle(Jms.outboundAdapter(connectionFactory)
                        .destination("logging"))
                .get();
    }

    @JmsListener(destination = "logging")
    public void listen(Object in) {
        LOGGER.info(in.toString());
    }

//    @Bean
//    public IntegrationFlow jmsOutboundFlow() {
//        return IntegrationFlows
//                .from("outboundChannel")
//                .handle(Jms.outboundAdapter(connectionFactory)
//                        .destination("queue"))
//                .log()
//                .get();
//
//    }

}
