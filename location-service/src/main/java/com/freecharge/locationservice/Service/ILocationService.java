package com.freecharge.locationservice.Service;


import com.freecharge.locationservice.Dto.Response.PincodeResponse;
import com.freecharge.locationservice.Entity.Root;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ILocationService {

    List<Root> setDataToDb(String stateName);

    PincodeResponse findLatAndLong(Double longitude, Double latitude);

    List<String> mapPincode();

    List<String> addToTable();

    void findLatLong();

    List<String> parseJsonToDb();

    String mapLatLong();
}
