package com.freecharge.locationservice.Repository;

import com.freecharge.locationservice.Entity.IndiaLatLong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ILatLongRepository extends JpaRepository<IndiaLatLong,Integer> {


}
