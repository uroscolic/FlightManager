package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.PackageCreateDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.service.IPackageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package")
@AllArgsConstructor
public class PackageController {

    private IPackageService packageService;

    @CrossOrigin(origins = "http://localhost:4200")
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity<?> createPackage(@RequestHeader("Authorization") String authorization, @RequestBody PackageCreateDto packageCreateDto){
        return new ResponseEntity<>(packageService.createPackage(packageCreateDto), HttpStatus.CREATED);
    }
}
