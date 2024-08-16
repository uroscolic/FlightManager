package com.flightmanager.FlightBookingService.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsForPackagesCreateDto {
    @NotBlank(message = "Option cannot be empty")
    private OptionDto option;
    @NotBlank(message = "Package cannot be empty")
    private PackageDto _package;
}
