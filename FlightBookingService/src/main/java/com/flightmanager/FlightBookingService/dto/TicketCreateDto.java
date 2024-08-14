package com.flightmanager.FlightBookingService.dto;

import com.flightmanager.FlightBookingService.domain.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreateDto {
    private PassengerDto owner;
    private PassengerDto passenger;
    private int seatNumber;
    private Class ticketClass;
    private boolean isReturn;
    private PackageDto _package;
    private FlightDto flight;
    private FlightDto returnFlight;
    private Double totalPrice;
}
