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
     * @param fileJSON - path to file
     * @param <T>
     * @return new List of objects
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
     * @return new Object
     * @throws IOException
     */
    public static  <T> T convertMapperObject(String fileJSON, Class<?> clazz) throws IOException {

        return (T) new ObjectMapper().readValue(fileJSON, clazz);
    }

    /**
     * Read the file and write to String value
     * @param path to file
     * @return new string
     * @throws IOException
     */
    public static String getJSON(String path) throws IOException {

        return  FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
    }
}
