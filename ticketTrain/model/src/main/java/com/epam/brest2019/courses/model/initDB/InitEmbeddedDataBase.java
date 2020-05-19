package com.epam.brest2019.courses.model.initDB;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@Component
public class InitEmbeddedDataBase {

    private final static Logger LOGGER = LoggerFactory.getLogger(InitEmbeddedDataBase.class);


    public String initEmbeddedMongo() {
        String data = "";

        try ( InputStream stream = this.getClass().getClassLoader().getResourceAsStream("mongo-init.txt") ) {
            byte[] bdata = FileCopyUtils.copyToByteArray(stream);
            data = new String(bdata, StandardCharsets.UTF_8);

        } catch (Exception ex){
            LOGGER.debug("Error initialization test MongoDataBase: {}", ex.getMessage());
        }


        return data;
    }

}
