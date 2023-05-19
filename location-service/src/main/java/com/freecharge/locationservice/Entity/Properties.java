package com.freecharge.locationservice.Entity;

import lombok.Data;

@Data
public class Properties {

    public String pincode;
    public String state;
    public String district;
    public String officename;
    public String officetype;
    public int orig_ogc_fid;
}
