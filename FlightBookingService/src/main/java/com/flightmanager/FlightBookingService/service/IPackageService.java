package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.dto.PackageCreateDto;
import com.flightmanager.FlightBookingService.dto.PackageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPackageService {

    Page<PackageDto> getAllPackages(Pageable pageable);
    PackageDto getPackageByName(String name);
    PackageDto createPackage(PackageCreateDto packageCreateDto);
}
