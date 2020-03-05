package com.epam.brest2019.courses.dao.config;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.TicketDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.brest2019.courses.*")
@EnableAutoConfiguration
public class DaoConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());

        SqlSessionFactory factory1 = factory.getObject();
        factory1.getConfiguration().setMapUnderscoreToCamelCase(true);

        return factory1;
    }

    @Bean
    public MapperFactoryBean paymentDao() throws Exception {
        MapperFactoryBean mapperFactoryBean = new MapperFactoryBean();
        mapperFactoryBean.setMapperInterface(PaymentDao.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory());
        return mapperFactoryBean;
    }

    @Bean
    public MapperFactoryBean ticketDao() throws Exception {
        MapperFactoryBean mapperFactoryBean = new MapperFactoryBean();
        mapperFactoryBean.setMapperInterface(TicketDao.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory());
        return mapperFactoryBean;
    }


}
