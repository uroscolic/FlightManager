package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Flight;
import com.flightmanager.FlightBookingService.domain.Ticket;
import com.flightmanager.FlightBookingService.dto.*;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto flightToFlightDto(Flight flight)
    {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setPlane(flight.getPlane());
        flightDto.setOrigin(flight.getOrigin());
        flightDto.setDestination(flight.getDestination());
        flightDto.setGate(flight.getGate());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setArrivalTime(flight.getArrivalTime());
        flightDto.setPrice(flight.getPrice());
        flightDto.setAvailableEconomySeats(flight.getAvailableEconomySeats());
        flightDto.setAvailableBusinessSeats(flight.getAvailableBusinessSeats());
        flightDto.setAvailableFirstClassSeats(flight.getAvailableFirstClassSeats());
        return flightDto;
    }

    public Flight flightCreateDtoToFlight(FlightCreateDto flightCreateDto)
    {
        Flight flight = new Flight();
        flight.setPlane(flightCreateDto.getPlane());
        flight.setOrigin(flightCreateDto.getOrigin());
        flight.setDestination(flightCreateDto.getDestination());
        flight.setGate(flightCreateDto.getGate());
        flight.setDepartureTime(flightCreateDto.getDepartureTime());
        flight.setArrivalTime(flightCreateDto.getArrivalTime());
        flight.setPrice(flightCreateDto.getPrice());
        flight.setAvailableEconomySeats(flightCreateDto.getAvailableEconomySeats());
        flight.setAvailableBusinessSeats(flightCreateDto.getAvailableBusinessSeats());
        flight.setAvailableFirstClassSeats(flightCreateDto.getAvailableFirstClassSeats());
        return flight;
    }

    public Flight flightChangeDtoToFlight(Flight oldFlight, FlightChangeDto flightChangeDto)
    {
        Flight flight = new Flight();
        flight.setId(oldFlight.getId());
        flight.setPlane(oldFlight.getPlane());
        flight.setOrigin(oldFlight.getOrigin());
        flight.setDestination(oldFlight.getDestination());
        flight.setGate(flightChangeDto.getNewGate());
        flight.setDepartureTime(flightChangeDto.getNewDepartureTime());
        flight.setArrivalTime(flightChangeDto.getNewArrivalTime());
        flight.setPrice(flightChangeDto.getNewPrice());
        flight.setAvailableEconomySeats(oldFlight.getAvailableEconomySeats());
        flight.setAvailableBusinessSeats(oldFlight.getAvailableBusinessSeats());
        flight.setAvailableFirstClassSeats(oldFlight.getAvailableFirstClassSeats());
        return flight;
    }


}
