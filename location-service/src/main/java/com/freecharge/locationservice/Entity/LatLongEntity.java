package com.freecharge.locationservice.Entity;

import lombok.Data;
import org.locationtech.jts.geom.MultiPolygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="lat_long_data")
public class LatLongEntity {


        public String country_code;

        @Id
        public String postal_code;

        public String place_name;

        public String state_name;

        public String latitude;

        public String longitude;

        public String accuracy;
        @Column(columnDefinition = "Multipolygon",nullable = true)
        private MultiPolygon multi_polygon;
}
