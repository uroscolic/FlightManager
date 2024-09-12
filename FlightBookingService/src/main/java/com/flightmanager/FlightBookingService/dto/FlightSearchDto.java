package com.flightmanager.FlightBookingService.dto;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Class;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchDto {
    @NotBlank(message = "Origin cannot be empty")
    private Airport origin;
    @NotBlank(message = "Destination cannot be empty")
    private Airport destination;
    @NotBlank(message = "Departure start cannot be empty")
    private LocalDateTime departureStart;
    private LocalDateTime arrivalEnd;
    @NotBlank(message = "flight class cannot be empty")
    private Class flightClass;
    @NotBlank(message = "Passengers cannot be empty")
    private int passengers;

}