package com.freecharge.locationservice.Repository;

import com.freecharge.locationservice.Entity.LatLongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILatLongPincodeRepository extends JpaRepository<LatLongEntity,String> {
}
