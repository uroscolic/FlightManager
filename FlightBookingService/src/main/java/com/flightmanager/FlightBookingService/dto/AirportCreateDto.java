package com.flightmanager.FlightBookingService.dto;

import com.flightmanager.FlightBookingService.domain.Location;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportCreateDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Location cannot be empty")
    private Location location;
}
