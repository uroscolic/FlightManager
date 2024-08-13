package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.AirportDto;
import com.flightmanager.FlightBookingService.service.IAirportService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airport")
@AllArgsConstructor
public class AirportController {

    private IAirportService airportService;

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
}
