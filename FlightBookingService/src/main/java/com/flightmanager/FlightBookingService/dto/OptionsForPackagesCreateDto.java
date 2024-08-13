package com.flightmanager.FlightBookingService.dto;

import com.flightmanager.FlightBookingService.domain.Option;
import com.flightmanager.FlightBookingService.domain.Package;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsForPackagesCreateDto {
    @NotBlank(message = "Option cannot be empty")
    private Option option;
    @NotBlank(message = "Package cannot be empty")
    private Package _package;
}
