package com.flightmanager.FlightBookingService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageCreateDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    private Double price;
}
