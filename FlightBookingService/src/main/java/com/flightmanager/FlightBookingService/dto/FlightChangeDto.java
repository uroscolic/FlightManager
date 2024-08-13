package com.flightmanager.FlightBookingService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightChangeDto {
    @Min(value = 0, message = "Price cannot be negative")
    private Double newPrice;
    @NotBlank(message = "Departure time cannot be empty")
    private LocalDateTime newDepartureTime;
    @NotBlank(message = "Arrival time cannot be empty")
    private LocalDateTime newArrivalTime;
    @NotBlank(message = "Gate cannot be empty")
    private String newGate;
}
