package com.flightmanager.FlightBookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDto {
    private Long id;
    private String country;
    private String city;
    private String shortName;
    private String imagePath;
}
