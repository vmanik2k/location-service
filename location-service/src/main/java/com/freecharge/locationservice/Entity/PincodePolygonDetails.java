package com.freecharge.locationservice.Entity;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.MultiPolygon;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@Table(name="pincode_polygon_details")
public class PincodePolygonDetails {

    @Id
    private String pincode;

    private String name;

    private String state;

    private String district;

    @Column(columnDefinition = "Multipolygon",nullable = false)
    private MultiPolygon multi_polygon;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", insertable = true, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated;

    private String latitude;

    private String longitude;

}
