package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Location;
import com.flightmanager.FlightBookingService.dto.AirportChangeNameDto;
import com.flightmanager.FlightBookingService.dto.AirportCreateDto;
import com.flightmanager.FlightBookingService.dto.AirportDto;
import com.flightmanager.FlightBookingService.dto.LocationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AirportMapper {

    private LocationMapper locationMapper;

    public AirportDto airportToAirportDto(Airport airport) {
        AirportDto airportDto = new AirportDto();
        airportDto.setId(airport.getId());
        airportDto.setName(airport.getName());

        airportDto.setLocation(locationMapper.locationToLocationDto(airport.getLocation()));

        return airportDto;
    }

    public Airport airportCreateDtoToAirport(AirportCreateDto airportCreateDto)
    {
        Airport airport = new Airport();
        airport.setName(airportCreateDto.getName());

        Location location = new Location();
        location.setId(airportCreateDto.getLocation().getId());
        location.setImagePath(airportCreateDto.getLocation().getImagePath());
        location.setShortName(airportCreateDto.getLocation().getShortName());
        location.setCountry(airportCreateDto.getLocation().getCountry());
        location.setCity(airportCreateDto.getLocation().getCity());

        airport.setLocation(location);

        return airport;
    }

    public Airport getAirportFromDto(AirportDto airportDto) {
        Airport airport = new Airport();
        airport.setId(airportDto.getId());
        airport.setName(airportDto.getName());
        Location location = new Location();
        location.setId(airportDto.getLocation().getId());
        location.setCity(airportDto.getLocation().getCity());
        location.setCountry(airportDto.getLocation().getCountry());
        location.setShortName(airportDto.getLocation().getShortName());
        location.setImagePath(airportDto.getLocation().getImagePath());
        airport.setLocation(location);
        return airport;
    }
//    public Airport airportChangeNameDtoToAirport(AirportChangeNameDto airportChangeNameDto)
//    {
//        Airport airport = new Airport();
//
//        Location location = new Location();
//        location.setId(airportChangeNameDto.getLocation().getId());
//        location.setImagePath(airportChangeNameDto.getLocation().getImagePath());
//        location.setShortName(airportChangeNameDto.getLocation().getShortName());
//        location.setCountry(airportChangeNameDto.getLocation().getCountry());
//        location.setCity(airportChangeNameDto.getLocation().getCity());
//
//        airport.setLocation(location);
//
//        airport.setName(airportChangeNameDto.getNewName());
//        return airport;
//    }
}
