package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {

    @Query("SELECT p FROM Package p")
    Page<Package> findAllPackages(Pageable pageable);
    Optional<Package> findPackageByName(String name);
}