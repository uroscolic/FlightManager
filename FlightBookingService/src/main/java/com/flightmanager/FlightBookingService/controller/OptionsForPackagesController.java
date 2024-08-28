package com.flightmanager.FlightBookingService.controller;


import com.flightmanager.FlightBookingService.dto.OptionsForPackagesCreateDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.service.IOptionsForPackagesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/options-for-packages")
@AllArgsConstructor
public class OptionsForPackagesController {

    private IOptionsForPackagesService optionsForPackagesService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<?> getOptionsForPackages(
            @RequestParam(required = false) String optionName,
            @RequestParam(required = false) String packageName,
            Pageable pageable){

        if(optionName != null && packageName != null){
            return ResponseEntity.ok(optionsForPackagesService.getOptionsForPackagesByPackageAndOption(packageName, optionName));
        }
        else if(packageName != null){
            return ResponseEntity.ok(optionsForPackagesService.getByPackageName(packageName, pageable));
        }
        return ResponseEntity.ok(optionsForPackagesService.getByOptionName(optionName, pageable));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> createOptionsForPackages(@RequestBody OptionsForPackagesCreateDto optionsForPackagesCreateDto,
                                                      @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(optionsForPackagesService.createOptionsForPackages(optionsForPackagesCreateDto), HttpStatus.CREATED);
    }
}
