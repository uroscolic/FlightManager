package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.dto.PassengerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPassengerService {
    Page<PassengerDto> getAll(Pageable pageable);
    PassengerDto getPassengerByEmail(String email);
}
