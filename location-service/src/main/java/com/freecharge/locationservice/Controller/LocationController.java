package com.freecharge.locationservice.Controller;

import com.freecharge.locationservice.Dto.Response.PincodeResponse;
import com.freecharge.locationservice.Entity.Root;
import com.freecharge.locationservice.Service.ILocationService;
import com.freecharge.locationservice.Service.ServiceImpl.LocationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/location/service")
@Slf4j
public class LocationController {

    @Autowired
    ILocationService locationService;
    @Autowired
    LocationServiceImpl locationServiceImpl;

    @PostMapping("/startOnboard")
    public List<Root> startOnboard(@RequestParam("stateName") String stateName){
        log.info("request State Name {}",stateName);
        List<Root> dataToDbState = locationService.setDataToDb(stateName);
       // log.info("DB Response {}",dataToDbState);
        return dataToDbState;
    }

    @GetMapping("/getPincode")
    public PincodeResponse getPincode(HttpServletRequest request){
        Double latitude = Double.parseDouble(request.getParameter("latitude"));
        Double longitude = Double.parseDouble(request.getParameter("longitude"));
        PincodeResponse response = locationService.findLatAndLong(longitude, latitude);
        log.info("{}",response);
        return response;
    }

    @GetMapping("/mapPolygon")
    public List<String> mapPolygon(HttpServletRequest request){
        log.info("Request of mapping");
            return  locationService.addToTable();
        // return locationService.addToTable();
//        log.info("{}",response);
//        return new ArrayList<>();
    }

    @PostMapping("/jsonParse")
    public List<String> parseJsonToDb(){
        return locationService.parseJsonToDb();
    }

    @GetMapping("/mapLatLong")
    public String mapLatLong(){
        return locationService.mapLatLong();
    }
    @GetMapping("/makeResult")
    public void makeResult(){
        locationServiceImpl.makeResult();
    }
}
