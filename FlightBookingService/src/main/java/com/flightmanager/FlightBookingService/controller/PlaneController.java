package com.flightmanager.FlightBookingService.controller;


import com.flightmanager.FlightBookingService.dto.PlaneDto;
import com.flightmanager.FlightBookingService.service.IPlaneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plane")
@AllArgsConstructor
public class PlaneController {

    private IPlaneService iPlaneService;

    @GetMapping
    public Page<PlaneDto> getPlanes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer businessSeats,
            @RequestParam(required = false) Integer firstClassSeats,
            @RequestParam(required = false) Integer economySeats,
            Pageable pageable) {
        return iPlaneService.getPlanes(name, businessSeats, firstClassSeats, economySeats, pageable);
    }
}

