package com.freecharge.locationservice.Repository;

import com.freecharge.locationservice.Entity.PincodePolygonDetails;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILocationRepository extends JpaRepository<PincodePolygonDetails,String> {

    @Query(value = "Select p from PincodePolygonDetails p WHERE  ST_CONTAINS(p.multi_polygon,ST_GeomFromText(:point,4326))=true")
    PincodePolygonDetails findPincodePolygonDetails(@Param("point") String point);

    @Query(value = "Select * from pincode_polygon_details Where latitude is not null and longitude is not null ",nativeQuery = true)
   List<PincodePolygonDetails> findlatLongNotNull();

}
