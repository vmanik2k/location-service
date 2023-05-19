package com.freecharge.locationservice.Entity;

import lombok.Data;
import org.locationtech.jts.geom.MultiPolygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name= "all_lat_long_india")
public class IndiaLatLong {

    @Id
    @Column(name = "CID")
    int cid;

    @Column(name = "pincode")
    int pincode;

    @Column(name="name")
    String cityName;

    @Column(name = "Latitude")
    Double latitude;

    @Column(name = "Longitude")
    Double longitude;

    @Column(name = "multi_polygon")
    MultiPolygon multiPolygon;

    @Column(name="district")
    String district;

    @Column(name = "state")
    String state;
}
