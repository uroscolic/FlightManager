package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.PassengerCreateDto;
import com.flightmanager.FlightBookingService.dto.PassengerDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.security.service.TokenService;
import com.flightmanager.FlightBookingService.service.IPassengerService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PassengerController {

    private IPassengerService passengerService;
    private TokenService tokenService;

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_CLIENT"})
    public ResponseEntity<?> getAllPassengers(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false) String email,
            Pageable pageable) {
        if(email != null) {
            return new ResponseEntity<>(passengerService.getPassengerByEmail(email), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(passengerService.getAll(pageable), HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerCreateDto passengerCreateDto) {
        return new ResponseEntity<>(passengerService.createPassenger(passengerCreateDto), HttpStatus.CREATED);
    }
}
