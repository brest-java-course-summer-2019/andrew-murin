package com.epam.brest2019.courses.web_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.messaging.MessageChannel;

import javax.jms.ConnectionFactory;

@Configuration
@EnableIntegration
public class SenderConfig {


    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public MessageChannel sendToQueue() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow jmsInboundFlow() {
        return IntegrationFlows
                .from(Jms.inboundAdapter(connectionFactory)
                        .destination("sendToQueue"), e ->
                            e.poller(Pollers
                                    .fixedDelay(500)
                                    .maxMessagesPerPoll(2)))
                .handle(Jms.outboundAdapter(connectionFactory)
                        .destination("consumingChannel")
                )
                .get();

    }

}
