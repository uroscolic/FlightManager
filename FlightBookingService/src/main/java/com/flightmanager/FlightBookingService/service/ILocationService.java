package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.dto.LocationCreateDto;
import com.flightmanager.FlightBookingService.dto.LocationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILocationService {

    Page<LocationDto> getAllLocations(Pageable pageable);
    LocationDto getLocationByShortName(String shortName);
    Page<LocationDto> getLocationsByCity(String city, Pageable pageable);
    Page<LocationDto> getLocationsByCountry(String country, Pageable pageable);
    Page<LocationDto> getLocationsByCityAndCountry(String city, String country, Pageable pageable);
    LocationDto createLocation(LocationCreateDto locationCreateDto);
}
