package com.flightmanager.FlightBookingService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsForPackagesDto {
    private Long id;
    private OptionDto option;
    private PackageDto _package;
}
