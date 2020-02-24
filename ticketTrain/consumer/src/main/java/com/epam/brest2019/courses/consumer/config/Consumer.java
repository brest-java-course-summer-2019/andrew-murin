package com.epam.brest2019.courses.consumer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.jms.ChannelPublishingJmsMessageListener;
import org.springframework.integration.jms.JmsMessageDrivenEndpoint;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.jms.ConnectionFactory;

@Configuration
public class Consumer {

    private String destinationChannel = "queue";

    @Bean
    private MessageChannel addChannel() {
        return new DirectChannel();
    }

    @Bean
    private MessageChannel errorChannel() {
        return new DirectChannel();
    }

    @Bean
    public JmsMessageDrivenEndpoint jmsMessageDrivenEndpoint(ConnectionFactory connectionFactory) {

        JmsMessageDrivenEndpoint endpoint = new JmsMessageDrivenEndpoint(
                simpleMessageListenerContainer(connectionFactory),
                channelPublishingJmsMessageListener());
        endpoint.setOutputChannel(addChannel());
        endpoint.setErrorChannel(errorChannel());

        return endpoint;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(
            ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(destinationChannel);
        return container;
    }

    @Bean
    public ChannelPublishingJmsMessageListener channelPublishingJmsMessageListener() {
        return new ChannelPublishingJmsMessageListener();
    }


    @Bean
    @ServiceActivator(inputChannel = "errorChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("*********************************ERROR******************************************");
            }
        };
    }
}
