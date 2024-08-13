package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.LocationDto;
import com.flightmanager.FlightBookingService.service.ILocationService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
@AllArgsConstructor
public class LocationController {

    private ILocationService iLocationService;

    @GetMapping
    public ResponseEntity<Page<LocationDto>> getAllLocations(Pageable pageable) {
        return new ResponseEntity<>(iLocationService.getAllLocations(pageable), HttpStatus.OK);
    }
    @GetMapping("/{shortName}")
    public ResponseEntity<LocationDto> getLocationByShortName(@PathVariable("shortName") String shortName) {
        return new ResponseEntity<>(iLocationService.getLocationByShortName(shortName), HttpStatus.OK);
    }
    @GetMapping("/{city}")
    public ResponseEntity<Page<LocationDto>> getLocationsByCity(@PathVariable("city") String city,Pageable pageable) {
        return new ResponseEntity<>(iLocationService.getLocationsByCity(city,pageable), HttpStatus.OK);
    }
    @GetMapping("/{country}")
    public ResponseEntity<Page<LocationDto>> getLocationsByCountry(@PathVariable("country") String country,Pageable pageable) {
        return new ResponseEntity<>(iLocationService.getLocationsByCountry(country,pageable), HttpStatus.OK);
    }
    @GetMapping("/{city}/{country}")
    public ResponseEntity<Page<LocationDto>> getLocationsByCityAndCountry(@PathVariable("city") String city, @PathVariable("country") String country,Pageable pageable) {
        return new ResponseEntity<>(iLocationService.getLocationsByCityAndCountry(city,country,pageable), HttpStatus.OK);
    }


}
