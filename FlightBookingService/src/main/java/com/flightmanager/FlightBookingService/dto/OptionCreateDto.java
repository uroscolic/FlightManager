package com.flightmanager.FlightBookingService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionCreateDto {
    @NotBlank(message = "Option name cannot be empty")
    private String name;
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;
}
