package com.crystal.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonSerializer {
    private JsonSerializer(){

    }
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        //We need those to make sure that we are using same datatype when serializing and deserializing
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        //Adding support for serialization of LocalDate
        mapper.registerModule(new JavaTimeModule());
    }


    public static <T> String serialize(T t) {
        return serialize(t, null);
    }

    public static <T> String serialize(T t, boolean intentOutput) {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return serialize(t);
    }

    public static <T> String serialize(T t, ObjectMapper mapper) {
        if (mapper != null)
            JsonSerializer.mapper = mapper;
        try {
            return JsonSerializer.mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T deserialize(String jsonText, Class<T> modelClass) {
        try {
            return mapper.readValue(jsonText, modelClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
