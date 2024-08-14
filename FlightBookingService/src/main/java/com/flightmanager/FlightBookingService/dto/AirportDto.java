package com.flightmanager.FlightBookingService.dto;

import com.flightmanager.FlightBookingService.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportDto {
    private Long id;
    private String name;
    private LocationDto location;
}
