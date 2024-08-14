package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.dto.PlaneCreateDto;
import com.flightmanager.FlightBookingService.dto.PlaneDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPlaneService {

    Page<PlaneDto> getPlanes(String name, Integer businessSeats, Integer firstClassSeats, Integer economySeats, Pageable pageable);
    PlaneDto createPlane(PlaneCreateDto planeCreateDto);
}
