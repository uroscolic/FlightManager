package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.*;
import com.flightmanager.FlightBookingService.dto.*;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto flightToFlightDto(Flight flight)
    {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());

        PlaneDto planeDto = new PlaneDto();
        planeDto.setId(flight.getPlane().getId());
        planeDto.setName(flight.getPlane().getName());
        planeDto.setBusinessSeats(flight.getPlane().getBusinessSeats());
        planeDto.setEconomySeats(flight.getPlane().getEconomySeats());
        planeDto.setFirstClassSeats(flight.getPlane().getFirstClassSeats());
        flightDto.setPlane(planeDto);

        AirportDto originDto = new AirportDto();
        originDto.setId(flight.getOrigin().getId());
        originDto.setName(flight.getOrigin().getName());

        LocationDto originLocationDto = new LocationDto();
        originLocationDto.setId(flight.getOrigin().getLocation().getId());
        originLocationDto.setCity(flight.getOrigin().getLocation().getCity());
        originLocationDto.setCountry(flight.getOrigin().getLocation().getCountry());
        originLocationDto.setImagePath(flight.getOrigin().getLocation().getImagePath());
        originLocationDto.setShortName(flight.getOrigin().getLocation().getShortName());

        originDto.setLocation(originLocationDto);

        flightDto.setOrigin(originDto);

        AirportDto destinationDto = new AirportDto();
        destinationDto.setId(flight.getDestination().getId());
        destinationDto.setName(flight.getDestination().getName());

        LocationDto destinationLocationDto = new LocationDto();
        destinationLocationDto.setId(flight.getDestination().getLocation().getId());
        destinationLocationDto.setCity(flight.getDestination().getLocation().getCity());
        destinationLocationDto.setCountry(flight.getDestination().getLocation().getCountry());
        destinationLocationDto.setImagePath(flight.getDestination().getLocation().getImagePath());
        destinationLocationDto.setShortName(flight.getDestination().getLocation().getShortName());

        destinationDto.setLocation(destinationLocationDto);

        flightDto.setDestination(destinationDto);



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
        originLocation.setImagePath(flightCreateDto.getOrigin().getLocation().getImagePath());
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
        destinationLocation.setImagePath(flightCreateDto.getDestination().getLocation().getImagePath());
        destinationLocation.setShortName(flightCreateDto.getDestination().getLocation().getShortName());

        destination.setLocation(destinationLocation);

        flight.setDestination(destination);


        flight.setGate(flightCreateDto.getGate());
        flight.setDepartureTime(flightCreateDto.getDepartureTime());
        flight.setArrivalTime(flightCreateDto.getArrivalTime());
        flight.setPrice(flightCreateDto.getPrice());
        flight.setAvailableEconomySeats(flightCreateDto.getAvailableEconomySeats());
        flight.setAvailableBusinessSeats(flightCreateDto.getAvailableBusinessSeats());
        flight.setAvailableFirstClassSeats(flightCreateDto.getAvailableFirstClassSeats());
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
        originLocation.setImagePath(oldFlight.getOrigin().getLocation().getImagePath());
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
        destinationLocation.setImagePath(oldFlight.getDestination().getLocation().getImagePath());
        destinationLocation.setShortName(oldFlight.getDestination().getLocation().getShortName());

        destination.setLocation(destinationLocation);

        flight.setDestination(destination);


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
