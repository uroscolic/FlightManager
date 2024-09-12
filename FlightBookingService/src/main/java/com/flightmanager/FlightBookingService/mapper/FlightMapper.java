package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.*;
import com.flightmanager.FlightBookingService.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FlightMapper {

    private AirportMapper airportMapper;
    private PlaneMapper planeMapper;

    public FlightDto flightToFlightDto(Flight flight)
    {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());

        flightDto.setPlane(planeMapper.planeToPlaneDto(flight.getPlane()));
        flightDto.setOrigin(airportMapper.airportToAirportDto(flight.getOrigin()));
        flightDto.setDestination(airportMapper.airportToAirportDto(flight.getDestination()));

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

        Plane plane = new Plane();
        plane.setId(flightCreateDto.getPlane().getId());
        plane.setName(flightCreateDto.getPlane().getName());
        plane.setBusinessSeats(flightCreateDto.getPlane().getBusinessSeats());
        plane.setEconomySeats(flightCreateDto.getPlane().getEconomySeats());
        plane.setFirstClassSeats(flightCreateDto.getPlane().getFirstClassSeats());

        flight.setPlane(plane);

        Airport origin = new Airport();
        origin.setId(flightCreateDto.getOrigin().getId());
        origin.setName(flightCreateDto.getOrigin().getName());

        Location originLocation = new Location();
        originLocation.setId(flightCreateDto.getOrigin().getLocation().getId());
        originLocation.setCity(flightCreateDto.getOrigin().getLocation().getCity());
        originLocation.setCountry(flightCreateDto.getOrigin().getLocation().getCountry());
        originLocation.setShortName(flightCreateDto.getOrigin().getLocation().getShortName());

        origin.setLocation(originLocation);

        flight.setOrigin(origin);

        Airport destination = new Airport();

        destination.setId(flightCreateDto.getDestination().getId());
        destination.setName(flightCreateDto.getDestination().getName());

        Location destinationLocation = new Location();
        destinationLocation.setId(flightCreateDto.getDestination().getLocation().getId());
        destinationLocation.setCity(flightCreateDto.getDestination().getLocation().getCity());
        destinationLocation.setCountry(flightCreateDto.getDestination().getLocation().getCountry());
        destinationLocation.setShortName(flightCreateDto.getDestination().getLocation().getShortName());

        destination.setLocation(destinationLocation);

        flight.setDestination(destination);


        flight.setGate(flightCreateDto.getGate());
        flight.setDepartureTime(flightCreateDto.getDepartureTime());
        flight.setArrivalTime(flightCreateDto.getArrivalTime());
        flight.setPrice(flightCreateDto.getPrice());
        return flight;
    }

    public Flight flightChangeDtoToFlight(FlightDto oldFlight, FlightChangeDto flightChangeDto)
    {
        Flight flight = new Flight();
        flight.setId(oldFlight.getId());

        Plane plane = new Plane();
        plane.setId(oldFlight.getPlane().getId());
        plane.setName(oldFlight.getPlane().getName());
        plane.setBusinessSeats(oldFlight.getPlane().getBusinessSeats());
        plane.setEconomySeats(oldFlight.getPlane().getEconomySeats());
        plane.setFirstClassSeats(oldFlight.getPlane().getFirstClassSeats());

        flight.setPlane(plane);

        Airport origin = new Airport();
        origin.setId(oldFlight.getOrigin().getId());
        origin.setName(oldFlight.getOrigin().getName());

        Location originLocation = new Location();
        originLocation.setId(oldFlight.getOrigin().getLocation().getId());
        originLocation.setCity(oldFlight.getOrigin().getLocation().getCity());
        originLocation.setCountry(oldFlight.getOrigin().getLocation().getCountry());
        originLocation.setShortName(oldFlight.getOrigin().getLocation().getShortName());

        origin.setLocation(originLocation);

        flight.setOrigin(origin);

        Airport destination = new Airport();

        destination.setId(oldFlight.getDestination().getId());
        destination.setName(oldFlight.getDestination().getName());

        Location destinationLocation = new Location();
        destinationLocation.setId(oldFlight.getDestination().getLocation().getId());
        destinationLocation.setCity(oldFlight.getDestination().getLocation().getCity());
        destinationLocation.setCountry(oldFlight.getDestination().getLocation().getCountry());
        destinationLocation.setShortName(oldFlight.getDestination().getLocation().getShortName());

        destination.setLocation(destinationLocation);

        flight.setDestination(destination);

        System.out.println("new gate"+flightChangeDto.getNewGate());
        System.out.println("new departure time"+flightChangeDto.getNewDepartureTime());
        System.out.println("new arrival time"+flightChangeDto.getNewArrivalTime());
        System.out.println("new price"+flightChangeDto.getNewPrice());

        flight.setGate(flightChangeDto.getNewGate());
        flight.setDepartureTime(flightChangeDto.getNewDepartureTime());
        flight.setArrivalTime(flightChangeDto.getNewArrivalTime());
        flight.setPrice(flightChangeDto.getNewPrice());
        flight.setAvailableEconomySeats(oldFlight.getAvailableEconomySeats());
        flight.setAvailableBusinessSeats(oldFlight.getAvailableBusinessSeats());
        flight.setAvailableFirstClassSeats(oldFlight.getAvailableFirstClassSeats());
        return flight;
    }

    public Flight getFlightFromDto(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setDestination(airportMapper.getAirportFromDto(flightDto.getDestination()));
        flight.setOrigin(airportMapper.getAirportFromDto(flightDto.getOrigin()));
        flight.setPrice(flightDto.getPrice());
        flight.setGate(flightDto.getGate());
        flight.setAvailableBusinessSeats(flightDto.getAvailableBusinessSeats());
        flight.setAvailableEconomySeats(flightDto.getAvailableEconomySeats());
        flight.setAvailableFirstClassSeats(flightDto.getAvailableFirstClassSeats());

        Plane plane = new Plane();
        plane.setId(flightDto.getPlane().getId());
        plane.setName(flightDto.getPlane().getName());
        plane.setBusinessSeats(flightDto.getPlane().getBusinessSeats());
        plane.setEconomySeats(flightDto.getPlane().getEconomySeats());
        plane.setFirstClassSeats(flightDto.getPlane().getFirstClassSeats());

        flight.setPlane(plane);
        return flight;
    }

}
