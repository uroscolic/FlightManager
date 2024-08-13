package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.domain.*;
import com.flightmanager.FlightBookingService.domain.Class;
import com.flightmanager.FlightBookingService.dto.TicketDto;
import com.flightmanager.FlightBookingService.mapper.TicketMapper;
import com.flightmanager.FlightBookingService.repository.TicketRepository;
import com.flightmanager.FlightBookingService.service.ITicketService;
import com.flightmanager.FlightBookingService.specification.TicketSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.flightmanager.FlightBookingService.domain.Package;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Getter
@Setter
@Service
@Transactional
@AllArgsConstructor
public class TicketServiceImpl implements ITicketService {

    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;

    @Override
    public Page<TicketDto> getTickets(String ownerEmail, Boolean isReturn, Passenger passenger,
                                      Flight flight, Flight returnFlight, Class ticketClass, Package _package,
                                      Double totalPrice, Plane plane, LocalDateTime flightDepartureStart,
                                      LocalDateTime flightDepartureEnd, LocalDateTime flightArrivalStart,
                                      LocalDateTime flightArrivalEnd, LocalDateTime returnFlightDepartureStart,
                                      LocalDateTime returnFlightDepartureEnd, LocalDateTime returnFlightArrivalStart,
                                      LocalDateTime returnFlightArrivalEnd, Pageable pageable) {
        Specification<Ticket> spec = Specification.where(TicketSpecification.withOwner(ownerEmail))
                .and(isReturn != null ? TicketSpecification.isReturn(isReturn) : null)
                .and(passenger != null ? TicketSpecification.withPassenger(passenger) : null)
                .and(flight != null ? TicketSpecification.withFlight(flight) : null)
                .and(returnFlight != null ? TicketSpecification.withReturnFlight(returnFlight) : null)
                .and(ticketClass != null ? TicketSpecification.withTicketClass(ticketClass) : null)
                .and(_package != null ? TicketSpecification.withPackage(_package) : null)
                .and(totalPrice != null ? TicketSpecification.withTotalPrice(totalPrice) : null)
                .and(plane != null ? TicketSpecification.onPlane(plane) : null)
                .and((flightDepartureStart != null && flightDepartureEnd != null) ?
                        TicketSpecification.withFlightDepartureTimeBetween(flightDepartureStart, flightDepartureEnd) : null)
                .and((flightArrivalStart != null && flightArrivalEnd != null) ?
                        TicketSpecification.withFlightArrivalTimeBetween(flightArrivalStart, flightArrivalEnd) : null)
                .and((returnFlightDepartureStart != null && returnFlightDepartureEnd != null) ?
                        TicketSpecification.withReturnFlightDepartureTimeBetween(returnFlightDepartureStart, returnFlightDepartureEnd) : null)
                .and((returnFlightArrivalStart != null && returnFlightArrivalEnd != null) ?
                        TicketSpecification.withReturnFlightArrivalTimeBetween(returnFlightArrivalStart, returnFlightArrivalEnd) : null);

        return ticketRepository.findAll(spec, pageable).map(ticketMapper :: ticketToTicketDto);
    }
}
