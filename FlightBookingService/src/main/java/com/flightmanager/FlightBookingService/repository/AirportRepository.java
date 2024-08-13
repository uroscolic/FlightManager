package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("SELECT a FROM Airport a")
    Page<Airport> findAllAirports(Pageable pageable);
    Page<Airport> findAirportsByName(String name, Pageable pageable);
    @Query("SELECT a FROM Airport a WHERE a.location.shortName = ?1")
    Page<Airport> findAirportsByLocationShortName(String shortName, Pageable pageable);
    @Query("SELECT a FROM Airport a WHERE a.location.shortName = ?1 AND a.name = ?2")
    Optional<Airport> findAirportByLocationShortNameAndName(String shortName, String name);


}
