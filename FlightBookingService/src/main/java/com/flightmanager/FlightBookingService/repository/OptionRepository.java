package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    @Query("SELECT o FROM Option o")
    Page<Option> findAllOptions(Pageable pageable);
    Optional<Option> findOptionByName(String name);
}
