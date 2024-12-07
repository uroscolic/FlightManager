package com.flightmanager.FlightBookingService.controller;


import com.flightmanager.FlightBookingService.dto.PlaneCreateDto;
import com.flightmanager.FlightBookingService.dto.PlaneDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.service.IPlaneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plane")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PlaneController {

    //TODO zauzeti avion da ne moze da leti dok se ne zavrsi let

    private IPlaneService iPlaneService;

    @GetMapping
    public ResponseEntity<Page<PlaneDto>> getPlanes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer businessSeats,
            @RequestParam(required = false) Integer firstClassSeats,
            @RequestParam(required = false) Integer economySeats,
            Pageable pageable) {
        return new ResponseEntity<>(iPlaneService.getPlanes(name, businessSeats, firstClassSeats, economySeats, pageable), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<PlaneDto> createPlane(@RequestHeader("Authorization") String authorization, @RequestBody PlaneCreateDto planeCreateDto) {
        return new ResponseEntity<>(iPlaneService.createPlane(planeCreateDto), HttpStatus.CREATED) ;
    }
}

