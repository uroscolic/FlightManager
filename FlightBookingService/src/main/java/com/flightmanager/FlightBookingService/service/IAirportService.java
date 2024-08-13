package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.dto.AirportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAirportService {

    Page<AirportDto> getAllAirports(Pageable pageable);
    Page<AirportDto> getAirportsByName(String name, Pageable pageable);
    Page<AirportDto> getAirportsByLocationShortName(String shortName, Pageable pageable);
    AirportDto getAirportByLocationShortNameAndName(String shortName, String name);

}
