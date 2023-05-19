package com.freecharge.locationservice.Entity;

import lombok.Data;

@Data
public class Feature {
    public String type;
    public Properties properties;
    public Geometery geometry;
}
