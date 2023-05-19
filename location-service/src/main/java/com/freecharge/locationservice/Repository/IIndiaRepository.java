package com.freecharge.locationservice.Repository;

import com.freecharge.locationservice.Entity.India;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIndiaRepository extends JpaRepository<India,Integer> {

    @Query(name = "Select * from india where postal_code = :postalCode",nativeQuery = true)
    List<India> findIndiasByPostalCode(@Param("postalCode") String postalCode);
}
