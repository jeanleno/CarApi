package com.jlheidemann.carapi.repository;

import com.jlheidemann.carapi.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean.heidemann
 */
@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    
}
