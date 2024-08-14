package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.domain.*;
import com.flightmanager.FlightBookingService.domain.Class;
import com.flightmanager.FlightBookingService.dto.TicketCreateDto;
import com.flightmanager.FlightBookingService.dto.TicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.flightmanager.FlightBookingService.domain.Package;
import java.time.LocalDateTime;

public interface ITicketService {

     Page<TicketDto> getTickets(String ownerEmail, Boolean isReturn, Passenger passenger, Flight flight, Flight returnFlight,
                                Class ticketClass, Package _package, Double totalPrice, Plane plane,
                                LocalDateTime flightDepartureStart, LocalDateTime flightDepartureEnd,
                                LocalDateTime flightArrivalStart, LocalDateTime flightArrivalEnd,
                                LocalDateTime returnFlightDepartureStart, LocalDateTime returnFlightDepartureEnd,
                                LocalDateTime returnFlightArrivalStart, LocalDateTime returnFlightArrivalEnd,
                                Pageable pageable);

     TicketDto createTicket(TicketCreateDto ticketCreateDto);

}
