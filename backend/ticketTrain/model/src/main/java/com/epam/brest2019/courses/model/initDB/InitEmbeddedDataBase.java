package com.epam.brest2019.courses.model.initDB;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@Component
@Slf4j
public class InitEmbeddedDataBase {

    public String initEmbeddedMongo() {
        String data = "";

        try ( InputStream stream = this.getClass().getClassLoader().getResourceAsStream("mongo-init.txt") ) {
            byte[] bdata = FileCopyUtils.copyToByteArray(stream);
            data = new String(bdata, StandardCharsets.UTF_8);

        } catch (Exception ex){
            log.debug("Error initialization test MongoDataBase: {}", ex.getMessage());
        }


        return data;
    }

}
