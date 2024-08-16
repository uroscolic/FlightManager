package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Plane;
import com.flightmanager.FlightBookingService.dto.FlightChangeDto;
import com.flightmanager.FlightBookingService.dto.FlightCreateDto;
import com.flightmanager.FlightBookingService.dto.FlightDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IFlightService {

     Page<FlightDto> getFlights(Plane plane, Airport origin, Airport destination, String gate,
                                LocalDateTime departureStart, LocalDateTime departureEnd,
                                LocalDateTime arrivalStart, LocalDateTime arrivalEnd, Double fromPrice, Double toPrice,
                                Pageable pageable);
     FlightDto createFlight(FlightCreateDto flightCreateDto);
     FlightDto updateFlight(FlightDto oldFlight, FlightChangeDto flightChangeDto);
     FlightDto getFlightById(Long id);
}
