package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.dto.AirportChangeNameDto;
import com.flightmanager.FlightBookingService.dto.AirportCreateDto;
import com.flightmanager.FlightBookingService.dto.AirportDto;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {

        public AirportDto airportToAirportDto(Airport airport) {
            AirportDto airportDto = new AirportDto();
            airportDto.setId(airport.getId());
            airportDto.setName(airport.getName());
            airportDto.setLocation(airport.getLocation());
            return airportDto;
        }

        public Airport airportCreateDtoToAirport(AirportCreateDto airportCreateDto)
        {
            Airport airport = new Airport();
            airport.setName(airportCreateDto.getName());
            airport.setLocation(airportCreateDto.getLocation());
            return airport;
        }

        public Airport airportChangeNameDtoToAirport(AirportChangeNameDto airportChangeNameDto)
        {
            Airport airport = new Airport();
            airport.setLocation(airportChangeNameDto.getLocation());
            airport.setName(airportChangeNameDto.getNewName());
            return airport;
        }
}
