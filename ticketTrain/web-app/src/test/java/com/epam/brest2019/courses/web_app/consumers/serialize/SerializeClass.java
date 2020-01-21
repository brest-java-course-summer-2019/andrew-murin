package com.epam.brest2019.courses.web_app.consumers.serialize;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.TestUtils;
import wiremock.org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * Used methods for serialized objects
 */
public class SerializeClass {

    /**
     * Serialize String value to List<T>
     * @param fileJSON
     * @param <T>
     * @return
     * @throws IOException
     */
    public static  <T> T convertMapperList(String fileJSON) throws IOException {

        return new ObjectMapper().readValue(fileJSON,
                new TypeReference<List<T>>(){}
        );
    }

    /**
     * Serialize String to object <T>
     * @param fileJSON
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static  <T> T convertMapperObject(String fileJSON, Class<?> clazz) throws IOException {

        return (T) new ObjectMapper().readValue(fileJSON, clazz);
    }

    /**
     * Read the file and write to String value
     * @param path
     * @return
     * @throws IOException
     */
    public static String getJSON(String path) throws IOException {
        String file = FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
        return file;
    }
}
