package com.flightmanager.FlightBookingService.dto;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Plane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Long id;
    private PlaneDto plane;
    private AirportDto origin;
    private AirportDto destination;
    private String gate;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double price;
    private int availableEconomySeats;
    private int availableBusinessSeats;
    private int availableFirstClassSeats;
}
