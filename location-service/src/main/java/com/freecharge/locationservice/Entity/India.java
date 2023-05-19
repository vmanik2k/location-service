package com.freecharge.locationservice.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="india")
public class India {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public int id;
        public String country_code;
        @Column(name = "postal_code")
        public String postalCode;
        public String place;
        public String state;
        public String statecode;
        public String province_or_county;
        public String province_or_countycode;
        public String community;
        public String communitycode;
        public String latitude;
        public String longitude;
        public String accuracy;
        @Column(name="Country")
        public String country;
        @Column(name="Continent")
        public String continent;
}
