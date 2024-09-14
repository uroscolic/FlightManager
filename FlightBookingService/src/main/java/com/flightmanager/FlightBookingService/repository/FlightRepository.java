package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Flight;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Flight> findById(Long id);
}
