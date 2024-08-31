package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.domain.Passenger;
import com.flightmanager.FlightBookingService.dto.PassengerCreateDto;
import com.flightmanager.FlightBookingService.dto.PassengerDto;
import com.flightmanager.FlightBookingService.mapper.PassengerMapper;
import com.flightmanager.FlightBookingService.repository.PassengerRepository;
import com.flightmanager.FlightBookingService.service.IPassengerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@Service
@Transactional
@AllArgsConstructor
public class PassengerServiceImpl implements IPassengerService {

    private PassengerRepository passengerRepository;
    private PassengerMapper passengerMapper;

    @CrossOrigin(origins = "http://localhost:4200")
    @Override
    public Page<PassengerDto> getAll(Pageable pageable) {
        return passengerRepository.findAll(pageable).map(passengerMapper::passengerToPassengerDto);
    }

    @Override
    public PassengerDto getPassengerByEmail(String email) {
        return passengerMapper.passengerToPassengerDto(passengerRepository.findPassengerByEmail(email).orElseThrow(() -> new RuntimeException("Passenger not found")));
    }

    @Override
    public PassengerDto createPassenger(PassengerCreateDto passengerCreateDto) {
        Passenger passenger = passengerMapper.passengerCreateDtoToPassenger(passengerCreateDto);
        passengerRepository.save(passenger);
        return passengerMapper.passengerToPassengerDto(passenger);
    }
}
