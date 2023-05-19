package com.freecharge.locationservice.Entity;

import lombok.Data;

import java.util.List;

@Data
public class Geometery {

    public String type;
    public List<List<List<List<Double>>>> coordinates;
}
