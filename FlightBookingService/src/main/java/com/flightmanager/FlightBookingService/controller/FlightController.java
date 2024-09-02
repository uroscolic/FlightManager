package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Plane;
import com.flightmanager.FlightBookingService.dto.FlightChangeDto;
import com.flightmanager.FlightBookingService.dto.FlightCreateDto;
import com.flightmanager.FlightBookingService.dto.FlightDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.service.IFlightService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
public class FlightController {

    private IFlightService flightService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<Page<FlightDto>> getFlights(
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
        return new ResponseEntity<>(flightService.getFlights(plane, origin, destination, gate, departureStart, departureEnd, arrivalStart, arrivalEnd, fromPrice, toPrice, pageable), org.springframework.http.HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<FlightDto> createFlight(
            @RequestHeader("Authorization") String authorization,
            @RequestBody FlightCreateDto flightCreateDto) {
        return new ResponseEntity<>(flightService.createFlight(flightCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<FlightDto> updateFlight(
            @RequestHeader("Authorization") String authorization,
            @PathVariable("id") Long id, @RequestBody FlightChangeDto flightChangeDto){

        FlightDto oldFlight = flightService.getFlightById(id);
        return new ResponseEntity<>(flightService.updateFlight(oldFlight, flightChangeDto), HttpStatus.OK);
    }

}
