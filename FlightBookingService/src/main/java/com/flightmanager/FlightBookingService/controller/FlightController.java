package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Plane;
import com.flightmanager.FlightBookingService.dto.FlightDto;
import com.flightmanager.FlightBookingService.service.IFlightService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
public class FlightController {

    private IFlightService iFlightService;

    @GetMapping
    public Page<FlightDto> getFlights(
            @RequestParam(required = false) Plane plane,
            @RequestParam(required = false) Airport origin,
            @RequestParam(required = false) Airport destination,
            @RequestParam(required = false) String gate,
            @RequestParam(required = false) LocalDateTime departureStart,
            @RequestParam(required = false) LocalDateTime departureEnd,
            @RequestParam(required = false) LocalDateTime arrivalStart,
            @RequestParam(required = false) LocalDateTime arrivalEnd,
            @RequestParam(required = false) Double fromPrice,
            @RequestParam(required = false) Double toPrice,
            Pageable pageable) {
        return iFlightService.getFlights(plane, origin, destination, gate, departureStart, departureEnd, arrivalStart, arrivalEnd, fromPrice, toPrice,pageable);
    }

}
