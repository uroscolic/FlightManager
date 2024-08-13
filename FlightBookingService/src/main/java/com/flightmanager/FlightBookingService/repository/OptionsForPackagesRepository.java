package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Option;
import com.flightmanager.FlightBookingService.domain.OptionsForPackages;
import com.flightmanager.FlightBookingService.domain.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionsForPackagesRepository extends JpaRepository<OptionsForPackages, Long> {

    @Query("SELECT o FROM OptionsForPackages o WHERE o.option.name = ?1")
    Page<OptionsForPackages> findByOptionName(String optionName, Pageable pageable);
    @Query("SELECT o FROM OptionsForPackages o WHERE o._package.name = ?1")
    Page<OptionsForPackages> findBy_packageName(String packageName, Pageable pageable);
    @Query("SELECT o FROM OptionsForPackages o WHERE o._package.name = ?1 AND o.option.name = ?2")
    Optional<OptionsForPackages> findOptionsForPackagesBy_packageNameAndOptionName(String packageName, String optionName);
}
