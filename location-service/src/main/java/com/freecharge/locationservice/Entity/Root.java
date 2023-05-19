package com.freecharge.locationservice.Entity;

import lombok.Data;
import java.util.List;

@Data
public class Root {
    public String type;
    public String name;
    public Crs crs;
    public List<Feature> features;
}
