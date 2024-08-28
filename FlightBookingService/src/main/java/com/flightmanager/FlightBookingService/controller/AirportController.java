package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.AirportChangeNameDto;
import com.flightmanager.FlightBookingService.dto.AirportCreateDto;
import com.flightmanager.FlightBookingService.dto.AirportDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.service.IAirportService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airport")
@AllArgsConstructor
public class AirportController {

    private IAirportService airportService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<?> getAirports(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String shortName,
            Pageable pageable) {
        if(name != null && shortName != null) {
            return new ResponseEntity<>(airportService.getAirportByLocationShortNameAndName(shortName, name), HttpStatus.OK);
        } else if(name != null) {
            return new ResponseEntity<>(airportService.getAirportsByName(name, pageable), HttpStatus.OK);
        } else if(shortName != null) {
            return new ResponseEntity<>(airportService.getAirportsByLocationShortName(shortName, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(airportService.getAllAirports(pageable), HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<AirportDto> createAirport(
            @RequestHeader("Authorization") String authorization,
            @RequestBody AirportCreateDto airportCreateDto) {
        return new ResponseEntity<>(airportService.createAirport(airportCreateDto), HttpStatus.CREATED);
    }

    @PutMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<AirportDto> updateAirport(
            @RequestHeader("Authorization") String authorization,
            @RequestBody AirportChangeNameDto airportChangeNameDto) {
        return new ResponseEntity<>(airportService.updateAirportName(airportChangeNameDto), HttpStatus.OK);
    }
}
