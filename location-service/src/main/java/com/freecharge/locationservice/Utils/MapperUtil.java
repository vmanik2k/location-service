package com.freecharge.locationservice.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class MapperUtil {


    private static final ObjectMapper MAPPER =
            new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T map(final String jsonStr, final Class<T> c) {
        T target = null;
        try {
            target = MAPPER.readValue(jsonStr, c);
        } catch (final JsonProcessingException e) {
            log.error("String to class convert exception is {}", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static <T> String map(final Class<T> source) {
        String jsonString ="";
        try {
            jsonString = MAPPER.writeValueAsString(source);
        } catch (final JsonProcessingException e) {
            log.error("String to class convert exception is {}", e);
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    public static <T> String map(final Object source) {
        String jsonString ="";
        ;
        try {
            jsonString = MAPPER.writeValueAsString(source);
        } catch (final JsonProcessingException e) {
            log.error("String to class convert exception is {}", e);
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    public static <S, T> T map(final Object source, final Class<T> destination) {
        T target = null;
        try {
            target = MAPPER.convertValue(source, destination);
        } catch (final IllegalArgumentException e) {
            log.error("String to class convert exception is {}", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    private MapperUtil() {
    }

}