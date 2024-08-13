package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.OptionDto;
import com.flightmanager.FlightBookingService.service.IOptionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/option")
@AllArgsConstructor
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
}
