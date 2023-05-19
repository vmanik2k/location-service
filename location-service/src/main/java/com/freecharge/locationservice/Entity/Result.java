package com.freecharge.locationservice.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pincode_v1")
    private  String pincodeFromOld; //pincode from old table

    @Column(name = "pincode_v2")
    private String pincodeFromNew; //pincode from new table

    private String result;
}
