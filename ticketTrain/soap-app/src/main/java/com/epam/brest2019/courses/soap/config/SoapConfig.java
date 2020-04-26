package com.epam.brest2019.courses.soap.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();

        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "ticketTrain")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchemaCollection schemaCollection) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ticketTrainPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://epam.com/brest2019/courses");
        wsdl11Definition.setSchemaCollection(schemaCollection);

        return wsdl11Definition;
    }

    @Bean
    public XsdSchemaCollection schemaCollection() {
        return new XsdSchemaCollection() {
            @Override
            public XsdSchema[] getXsdSchemas() {
                return getXsdSchemas();
            }

            @Override
            public XmlValidator createValidator() {
                return null;
            }
        };
    }


    public XsdSchema[] getXsdSchemas() throws IOException, SAXException, ParserConfigurationException {
        SimpleXsdSchema[] schemas = new SimpleXsdSchema[] {
                new SimpleXsdSchema(new ClassPathResource("xsd/city.xsd")),
                new SimpleXsdSchema(new ClassPathResource("xsd/ticket.xsd")),
                new SimpleXsdSchema(new ClassPathResource("xsd/payment.xsd"))
        };

        for (SimpleXsdSchema schema : schemas) {
            schema.afterPropertiesSet();
        }
        return  schemas;
    }

}
