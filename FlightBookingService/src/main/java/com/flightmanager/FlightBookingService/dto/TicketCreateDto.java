package com.flightmanager.FlightBookingService.dto;

import com.flightmanager.FlightBookingService.domain.Class;
import com.flightmanager.FlightBookingService.domain.Flight;
import com.flightmanager.FlightBookingService.domain.Package;
import com.flightmanager.FlightBookingService.domain.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreateDto {
    private Passenger owner;
    private Passenger passenger;
    private int seatNumber;
    private Class ticketClass;
    private boolean isReturn;
    private Package _package;
    private Flight flight;
    private Flight returnFlight;
    private Double totalPrice;
}
