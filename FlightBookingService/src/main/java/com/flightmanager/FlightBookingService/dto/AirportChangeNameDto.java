package com.flightmanager.FlightBookingService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportChangeNameDto {
    private String oldName;
    @NotBlank(message = "New name cannot be empty")
    private String newName;
    private LocationDto location;
}
