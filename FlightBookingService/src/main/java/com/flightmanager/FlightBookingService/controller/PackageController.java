package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.PackageDto;
import com.flightmanager.FlightBookingService.service.IPackageService;
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
@RequestMapping("/package")
@AllArgsConstructor
public class PackageController {

    private IPackageService packageService;

    @GetMapping
    public ResponseEntity<?> getPackages(
            @RequestParam(required = false) String name,
            Pageable pageable) {
        if(name != null) {
            return new ResponseEntity<>(packageService.getPackageByName(name), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(packageService.getAllPackages(pageable), HttpStatus.OK);
        }
    }
}
