package com.flightmanager.FlightBookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionDto {
    private Long id;
    private String name;
    private Double price;
}
