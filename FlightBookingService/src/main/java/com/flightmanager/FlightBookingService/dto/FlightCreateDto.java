package com.flightmanager.FlightBookingService.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightCreateDto {
    @NotBlank(message = "Gate cannot be empty")
    private PlaneDto plane;
    @NotBlank(message = "Origin cannot be empty")
    private AirportDto origin;
    @NotBlank(message = "Destination cannot be empty")
    private AirportDto destination;
    @NotBlank(message = "Gate cannot be empty")
    private String gate;
    @NotBlank(message = "Departure time cannot be empty")
    private LocalDateTime departureTime;
    @NotBlank(message = "Arrival time cannot be empty")
    private LocalDateTime arrivalTime;
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;
    @Min(value = 0, message = "Number of economy seats cannot be a negative number")
    private int availableEconomySeats;
    @Min(value = 0, message = "Number of business seats cannot be a negative number")
    private int availableBusinessSeats;
    @Min(value = 0, message = "Number of first class seats cannot be a negative number")
    private int availableFirstClassSeats;
}
