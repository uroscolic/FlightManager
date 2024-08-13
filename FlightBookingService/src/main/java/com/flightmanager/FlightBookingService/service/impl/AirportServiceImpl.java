package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.dto.AirportDto;
import com.flightmanager.FlightBookingService.mapper.AirportMapper;
import com.flightmanager.FlightBookingService.repository.AirportRepository;
import com.flightmanager.FlightBookingService.service.IAirportService;
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
public class AirportServiceImpl implements IAirportService {

    private AirportRepository airportRepository;
    private AirportMapper airportMapper;

    @Override
    public Page<AirportDto> getAllAirports(Pageable pageable) {
        return airportRepository.findAllAirports(pageable).map(airportMapper::airportToAirportDto);
    }

    @Override
    public Page<AirportDto> getAirportsByName(String name, Pageable pageable) {
        return airportRepository.findAirportsByName(name, pageable).map(airportMapper::airportToAirportDto);
    }

    @Override
    public Page<AirportDto> getAirportsByLocationShortName(String shortName, Pageable pageable) {
        return airportRepository.findAirportsByLocationShortName(shortName, pageable).map(airportMapper::airportToAirportDto);
    }

    @Override
    public AirportDto getAirportByLocationShortNameAndName(String shortName, String name) {
        return airportMapper.airportToAirportDto(airportRepository.findAirportByLocationShortNameAndName(shortName,name).orElseThrow(() -> new RuntimeException("Airport not found")));
    }
}
