package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l")
    Page<Location> findAllLocations(Pageable pageable);
    Optional<Location> findLocationByShortName(String shortName);
    Page<Location> findLocationsByCity(String city, Pageable pageable);
    Page<Location> findLocationsByCountry(String country, Pageable pageable);
    Page<Location> findLocationsByCityAndCountry(String city, String country, Pageable pageable);


}
