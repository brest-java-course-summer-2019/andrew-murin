package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Ticket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.epam.brest2019.courses.*"})
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }


    //    @Bean
//    public RedisCacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
//        return RedisCacheManager
//                .create(jedisConnectionFactory);
//    }

//    @Bean
//    public RedisCacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory);
//
//        return new RedisCacheManager(cacheWriter, cacheConfiguration);
//    }
//
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.getPoolConfig().setMaxIdle(30);
//        factory.getPoolConfig().setMaxIdle(10);
//        factory.setHostName("127.0.0.1");
//        factory.setPort(6379);
//
//        return factory;
//    }
//
//
//    @Bean
//    public RedisTemplate<String, Ticket> redisTemplate() {
//        RedisTemplate<String, Ticket> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        redisTemplate.setExposeConnection(true);
////        redisTemplate.setEnableTransactionSupport(true);
//
//        return redisTemplate;
//    }
}
