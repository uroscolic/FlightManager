package com.flightmanager.FlightBookingService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaneCreateDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Min(value = 0, message = "Number of economy seats cannot be a negative number")
    private int economySeats;
    @Min(value = 0, message = "Number of business seats cannot be a negative number")
    private int businessSeats;
    @Min(value = 0, message = "Number of first class seats cannot be a negative number")
    private int firstClassSeats;
}
