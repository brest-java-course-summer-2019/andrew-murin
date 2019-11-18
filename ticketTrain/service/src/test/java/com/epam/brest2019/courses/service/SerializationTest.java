package com.epam.brest2019.courses.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerializationTest {

    private static final String VALUE = "123456789";

    @Test
    public void test() throws IOException, ClassNotFoundException {
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

    @Test
    public void testXML(){
        SerializableObject object = create();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        encoder.writeObject(object);
        encoder.close();

        byte[] bytes = out.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Object result = new XMLDecoder(in).readObject();

        assertTrue(result instanceof SerializableObject);
        object = (SerializableObject) result;
        assertEquals(VALUE, object.getA());
    }

    @Test
    public void testJson() throws IOException {
        SerializableObject object = create();
        ObjectMapper objectMapper = new ObjectMapper();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        objectMapper.writeValue(out, object);

        byte[] bytes = out.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        object = objectMapper.readValue(in, SerializableObject.class);

        assertEquals(VALUE, object.getA());
    }


    private SerializableObject create(){
        SerializableObject object = new ExternalizableObject();
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


    public static class ExternalizableObject extends SerializableObject implements Externalizable{
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(String.format("object=%s", getA()));
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            setA(((String) in.readObject()).split("=")[1]);
        }
    }
}
