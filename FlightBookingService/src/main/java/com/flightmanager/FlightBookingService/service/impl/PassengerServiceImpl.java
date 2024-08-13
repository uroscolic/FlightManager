package com.flightmanager.FlightBookingService.service.impl;

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

@Getter
@Setter
@Service
@Transactional
@AllArgsConstructor
public class PassengerServiceImpl implements IPassengerService {

    private PassengerRepository passengerRepository;
    private PassengerMapper passengerMapper;

    @Override
    public Page<PassengerDto> getAll(Pageable pageable) {
        return passengerRepository.findAll(pageable).map(passengerMapper::passengerToPassengerDto);
    }

    @Override
    public PassengerDto getPassengerByEmail(String email) {
        return passengerMapper.passengerToPassengerDto(passengerRepository.findPassengerByEmail(email).orElseThrow(() -> new RuntimeException("Passenger not found")));
    }
}
