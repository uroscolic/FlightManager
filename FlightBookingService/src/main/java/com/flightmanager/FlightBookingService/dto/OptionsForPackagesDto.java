package com.flightmanager.FlightBookingService.dto;

import com.flightmanager.FlightBookingService.domain.Option;
import com.flightmanager.FlightBookingService.domain.Package;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsForPackagesDto {
    private Long id;
    private Option option;
    private Package _package;
}
