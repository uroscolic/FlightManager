package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.domain.Plane;
import com.flightmanager.FlightBookingService.dto.PlaneDto;
import com.flightmanager.FlightBookingService.mapper.PlaneMapper;
import com.flightmanager.FlightBookingService.repository.PlaneRepository;
import com.flightmanager.FlightBookingService.service.IPlaneService;
import com.flightmanager.FlightBookingService.specification.PlaneSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@Transactional
@AllArgsConstructor
public class PlaneServiceImpl implements IPlaneService {

    private PlaneRepository planeRepository;
    private PlaneMapper planeMapper;

    @Override
    public Page<PlaneDto> getPlanes(String name, Integer businessSeats,
                                    Integer firstClassSeats, Integer economySeats, Pageable pageable) {
        Specification<Plane> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and(PlaneSpecification.hasName(name));
        }
        if (businessSeats != null) {
            spec = spec.and(PlaneSpecification.hasBusinessSeatsGreaterThan(businessSeats));
        }
        if (firstClassSeats != null) {
            spec = spec.and(PlaneSpecification.hasFirstClassSeatsGreaterThan(firstClassSeats));
        }
        if (economySeats != null) {
            spec = spec.and(PlaneSpecification.hasEconomySeatsGreaterThan(economySeats));
        }

        return planeRepository.findAll(spec, pageable).map(planeMapper::planeToPlaneDto);
    }
}
