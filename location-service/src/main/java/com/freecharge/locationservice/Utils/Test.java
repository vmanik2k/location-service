package com.freecharge.locationservice.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freecharge.locationservice.Entity.LatLongEntity;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        try {
            List<String> responseList = new ArrayList<String>();
            String str  = new String(Files.readAllBytes(Paths.get("C:\\Project\\indiaPincodeLocator\\data.json")));
            System.out.println(str);
            ObjectMapper mapper = new ObjectMapper();
            Map<String , String> map = mapper.readValue(str, Map.class);
            System.out.println(map);
            System.out.println(mapper.writeValueAsString(map.get("504218")));
            map.entrySet().stream().forEach(data->{
                try {
                    LatLongEntity latLongEntity = mapper.readValue(mapper.writeValueAsString(data.getValue()), LatLongEntity.class);
                    System.out.println(latLongEntity);
                    //System.out.println();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
            //log.info(str);

        } catch (IOException e) {
           // log.info("Exception {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
