package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Plane;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long>, JpaSpecificationExecutor<Plane> {

//    @Query("SELECT p FROM Plane p")
//    Page<Plane> findAllPlanes();
//    Page<Plane> findPlanesByName(String name);
//    Page<Plane> findPlanesByBusinessSeatsIsAfter(Integer numberOfSeats);
//    Page<Plane> findPlanesByFirstClassSeatsIsAfter(Integer numberOfSeats);
//    Page<Plane> findPlanesByEconomySeatsIsAfter(Integer numberOfSeats);


}
