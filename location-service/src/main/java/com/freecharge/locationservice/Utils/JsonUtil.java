package com.freecharge.locationservice.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;


@Slf4j
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();


    static {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    }

    private JsonUtil() {

    }

    public static <T> T convertStringIntoObject(String jsonInString, TypeReference<T> reference) {
        T response = null;
        try {
            response = mapper.readValue(jsonInString, reference);
        } catch (IOException e) {
            log.error("String to object exception occurred {}", e);
            //throw new EMSAPIException(EMSErrorCodes.INTERNAL_SERVER_ERROR.getErrCode(), EMSErrorCodes.INTERNAL_SERVER_ERROR.getErrMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public static String writeValueAsString(Object object) {
        String result = null;
        if (object == null) {
            return null;
        }
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Object to String exception occurred {}", e);
            //throw new EMSAPIException(EMSErrorCodes.INTERNAL_SERVER_ERROR.getErrCode(), EMSErrorCodes.INTERNAL_SERVER_ERROR.getErrMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    public static Object getExecutorResponseKeyValue(Object serviceResponse, String path) {

        return getExecutorResponseKeyValue(serviceResponse, path, true);
    }

    public static Object getExecutorResponseKeyValue(Object serviceResponse, String path, boolean mandatory) {
        try {
            Object obj = JsonPath.read(serviceResponse.toString(), path);
            return obj;
            //return JsonPath.parse(JsonUtil.writeValueAsString(serviceResponse).toString()).read(productAttributeModelEntry.getValue().getAttributeName());
        } catch (PathNotFoundException e) {
            log.error("Unable to parse executor response. Key is : {}", path);
        }
        return null;
    }
}