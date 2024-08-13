package com.flightmanager.FlightBookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaneDto {
    private Long id;
    private String name;
    private int economySeats;
    private int businessSeats;
    private int firstClassSeats;
}
