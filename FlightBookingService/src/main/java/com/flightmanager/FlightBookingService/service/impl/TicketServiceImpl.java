package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.domain.*;
import com.flightmanager.FlightBookingService.domain.Class;
import com.flightmanager.FlightBookingService.dto.TicketCreateDto;
import com.flightmanager.FlightBookingService.dto.TicketDto;
import com.flightmanager.FlightBookingService.mapper.TicketMapper;
import com.flightmanager.FlightBookingService.repository.FlightRepository;
import com.flightmanager.FlightBookingService.repository.PlaneRepository;
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

import java.time.Duration;
import java.time.LocalDateTime;
@Getter
@Setter
@Service
@Transactional
@AllArgsConstructor
public class TicketServiceImpl implements ITicketService {

    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;
    private FlightRepository flightRepository;

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

    @Override
    public TicketDto createTicket(TicketCreateDto ticketCreateDto) {
        Ticket ticket = ticketMapper.ticketCreateDtoToTicket(ticketCreateDto);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureTime = ticket.getFlight().getDepartureTime();

        if (Duration.between(now, departureTime).toHours() < 12) {
            throw new RuntimeException("You can't buy a ticket less than 12 hours before the flight");
        }

        Flight flight = ticket.getFlight();
        Class ticketClass = ticket.getTicketClass();
        helperCreate(flight, ticketClass);

        if(ticket.isReturn()) {
            flight = ticket.getReturnFlight();
            helperCreate(flight, ticketClass);
        }

        return ticketMapper.ticketToTicketDto(ticketRepository.save(ticket));
    }



    @Override
    public Long cancelTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null) {
            return -1L;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureTime = ticket.getFlight().getDepartureTime();

        if (Duration.between(now, departureTime).toHours() < 48) {
            throw new RuntimeException("You can't cancel a ticket less than 48 hours before the flight");
        }


        Class ticketClass = ticket.getTicketClass();
        Flight flight = ticket.getFlight();
        helper(ticketClass, flight);

        if(ticket.isReturn()) {
            flight = ticket.getReturnFlight();
            helper(ticketClass, flight);
        }

        ticketRepository.deleteById(id);
        return id;
    }

    private void helper(Class ticketClass, Flight flight) {
        if(ticketClass == Class.BUSINESS) {
            flight.setAvailableBusinessSeats(flight.getAvailableBusinessSeats() + 1);
        } else if(ticketClass == Class.ECONOMY) {
            flight.setAvailableEconomySeats(flight.getAvailableEconomySeats() + 1);
        } else {
            flight.setAvailableFirstClassSeats(flight.getAvailableFirstClassSeats() + 1);
        }
        flightRepository.save(flight);
    }

    private void helperCreate(Flight flight, Class ticketClass) {

        if(ticketClass == Class.BUSINESS) {
            if(flight.getAvailableBusinessSeats() == 0) {
                throw new RuntimeException("No more business seats available");
            }
            flight.setAvailableBusinessSeats(flight.getAvailableBusinessSeats() - 1);
        } else if(ticketClass == Class.ECONOMY) {
            if(flight.getAvailableEconomySeats() == 0) {
                throw new RuntimeException("No more economy seats available");
            }
            flight.setAvailableEconomySeats(flight.getAvailableEconomySeats() - 1);
        } else {
            if(flight.getAvailableFirstClassSeats() == 0) {
                throw new RuntimeException("No more first class seats available");
            }
            flight.setAvailableFirstClassSeats(flight.getAvailableFirstClassSeats() - 1);
        }
        flightRepository.save(flight);
    }
}
