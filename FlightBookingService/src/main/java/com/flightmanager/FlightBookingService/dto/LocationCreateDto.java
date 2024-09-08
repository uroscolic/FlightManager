package com.flightmanager.FlightBookingService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationCreateDto {
    @NotBlank(message = "Country name cannot be empty")
    private String country;
    @NotBlank(message = "City name cannot be empty")
    private String city;
    @NotBlank(message = "Short name cannot be empty")
    private String shortName;
}
