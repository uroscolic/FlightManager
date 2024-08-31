package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.domain.Location;
import com.flightmanager.FlightBookingService.dto.LocationCreateDto;
import com.flightmanager.FlightBookingService.dto.LocationDto;
import com.flightmanager.FlightBookingService.mapper.LocationMapper;
import com.flightmanager.FlightBookingService.repository.LocationRepository;
import com.flightmanager.FlightBookingService.service.ILocationService;
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
public class LocationServiceImpl implements ILocationService {

    private LocationRepository locationRepository;
    private LocationMapper locationMapper;

    @CrossOrigin(origins = "http://localhost:4200")
    @Override
    public Page<LocationDto> getAllLocations(Pageable pageable) {
        return locationRepository.findAllLocations(pageable).map(locationMapper::locationToLocationDto);
    }

    @Override
    public LocationDto getLocationByShortName(String shortName) {
        Location location = locationRepository.findLocationByShortName(shortName).orElseThrow(() -> new RuntimeException("Location not found"));
        return locationMapper.locationToLocationDto(location);
    }

    @Override
    public Page<LocationDto> getLocationsByCity(String city, Pageable pageable) {
        return locationRepository.findLocationsByCity(city,pageable).map(locationMapper::locationToLocationDto);
    }

    @Override
    public Page<LocationDto> getLocationsByCountry(String country, Pageable pageable) {
        return locationRepository.findLocationsByCountry(country,pageable).map(locationMapper::locationToLocationDto);
    }

    @Override
    public Page<LocationDto> getLocationsByCityAndCountry(String city, String country, Pageable pageable) {
        return locationRepository.findLocationsByCityAndCountry(city, country, pageable).map(locationMapper::locationToLocationDto);
    }

    @Override
    public LocationDto createLocation(LocationCreateDto locationCreateDto) {
        return locationMapper.locationToLocationDto(locationRepository.save(locationMapper.locationCreateDtoToLocation(locationCreateDto)));
    }
}
