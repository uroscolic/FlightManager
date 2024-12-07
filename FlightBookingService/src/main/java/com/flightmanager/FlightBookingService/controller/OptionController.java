package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.OptionCreateDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.service.IOptionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/option")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OptionController {

    private IOptionService optionService;

    @GetMapping
    public ResponseEntity<?> getOptions(
            @RequestParam(required = false) String name,
            Pageable pageable) {
        if(name != null) {
            return new ResponseEntity<>(optionService.getOptionByName(name), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(optionService.getAllOptions(pageable), HttpStatus.OK);
        }
    }
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity<?> createOption(@RequestHeader("Authorization") String authorization, @RequestBody OptionCreateDto optionCreateDto) {
        return new ResponseEntity<>(optionService.createOption(optionCreateDto), HttpStatus.CREATED);
    }
}
