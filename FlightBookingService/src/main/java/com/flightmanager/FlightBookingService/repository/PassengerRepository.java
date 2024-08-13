package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query("SELECT p FROM Passenger p")
    Page<Passenger> findAllPassengers(Pageable pageable);
    Page<Passenger> findPassengersByFirstName(String firstName, Pageable pageable);
    Page<Passenger> findPassengersByLastName(String lastName, Pageable pageable);
    Page<Passenger> findPassengersByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);
    Optional<Passenger> findPassengerByEmail(String email);
}
