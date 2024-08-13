package com.flightmanager.FlightBookingService.controller;


import com.flightmanager.FlightBookingService.service.IOptionsForPackagesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/options-for-packages")
@AllArgsConstructor
public class OptionsForPackagesController {

    private IOptionsForPackagesService optionsForPackagesService;

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
}
