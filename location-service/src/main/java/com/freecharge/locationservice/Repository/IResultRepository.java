package com.freecharge.locationservice.Repository;

import com.freecharge.locationservice.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResultRepository extends JpaRepository<Result,Integer> {
}
