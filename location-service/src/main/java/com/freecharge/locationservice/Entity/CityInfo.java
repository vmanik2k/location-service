package com.freecharge.locationservice.Entity;

import lombok.Data;
import org.locationtech.jts.geom.MultiPolygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name= "cityinfo")
public class  CityInfo {
    @Id
    @Column(name = "CID")
    int cid;

    @Column(name = "Pincode")
    int pincode;

    @Column(name="name")
    String cityName;

    @Column(name = "Latitude")
    Double latitude;

    @Column(name = "Longitude")
    Double longitude;

    @Column(name = "multi_polygon",columnDefinition ="Multipolygon" )
    MultiPolygon multiPolygon;

    @Column(name="District")
    String district;

    @Column(name = "State")
    String state;
}
