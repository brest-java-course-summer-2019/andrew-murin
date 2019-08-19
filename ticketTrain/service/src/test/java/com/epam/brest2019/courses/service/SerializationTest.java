package com.epam.brest2019.courses.service;

import org.junit.jupiter.api.Test;


import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerializationTest {

    private static final String VALUE = "123456789";

    @Test
    void test() throws IOException, ClassNotFoundException {
        SerializableObject object = create();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(out);
        stream.close();
        stream.writeObject(object);

        byte[] bytes = out.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Object result = new ObjectInputStream(in).readObject();

        assertTrue(result instanceof SerializableObject);
        object = (SerializableObject) result;
        assertEquals(VALUE, object.getA());
    }

    private SerializableObject create(){
        SerializableObject object = new SerializableObject();
        object.setA(VALUE);
        return object;
    }

    public static class SerializableObject implements Serializable{
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

}
