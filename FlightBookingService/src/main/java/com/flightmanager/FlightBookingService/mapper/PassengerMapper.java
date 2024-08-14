package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Passenger;
import com.flightmanager.FlightBookingService.dto.PassengerCreateDto;
import com.flightmanager.FlightBookingService.dto.PassengerDto;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

    public PassengerDto passengerToPassengerDto(Passenger passenger)
    {
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setId(passenger.getId());
        passengerDto.setFirstName(passenger.getFirstName());
        passengerDto.setLastName(passenger.getLastName());
        passengerDto.setEmail(passenger.getEmail());
        return  passengerDto;
    }

    public Passenger passengerCreateDtoToPassenger(PassengerCreateDto passengerCreateDto)
    {
        Passenger passenger = new Passenger();
        passenger.setFirstName(passengerCreateDto.getFirstName());
        passenger.setLastName(passengerCreateDto.getLastName());
        passenger.setEmail(passengerCreateDto.getEmail());
        return passenger;
    }

    public Passenger getPassengerFromDto(PassengerDto passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setId(passengerDto.getId());
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setEmail(passengerDto.getEmail());
        return passenger;
    }
}
