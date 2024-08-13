package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Flight;
import com.flightmanager.FlightBookingService.domain.Plane;
import com.flightmanager.FlightBookingService.dto.FlightDto;
import com.flightmanager.FlightBookingService.mapper.FlightMapper;
import com.flightmanager.FlightBookingService.repository.FlightRepository;
import com.flightmanager.FlightBookingService.service.IFlightService;
import com.flightmanager.FlightBookingService.specification.FlightSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@Service
@Transactional
@AllArgsConstructor
public class FlightServiceImpl implements IFlightService {

    private FlightRepository flightRepository;
    private FlightMapper flightMapper;

    @Override
    public Page<FlightDto> getFlights(Plane plane, Airport origin, Airport destination, String gate, LocalDateTime departureStart, LocalDateTime departureEnd, LocalDateTime arrivalStart, LocalDateTime arrivalEnd,
                                      Double fromPrice, Double toPrice, Pageable pageable) {
        Specification<Flight> spec = Specification.where(plane != null ? FlightSpecification.onPlane(plane) : null)
                .and(origin != null ? FlightSpecification.fromOrigin(origin) : null)
                .and(destination != null ? FlightSpecification.toDestination(destination) : null)
                .and(gate != null ? FlightSpecification.withGate(gate) : null)
                .and((departureStart != null && departureEnd != null) ?
                        FlightSpecification.withDepartureTimeBetween(departureStart, departureEnd) : null)
                .and((arrivalStart != null && arrivalEnd != null) ?
                        FlightSpecification.withArrivalTimeBetween(arrivalStart, arrivalEnd) : null)
                .and((fromPrice != null && toPrice != null) ?
                        FlightSpecification.withPriceBetween(fromPrice, toPrice) : null);

        return flightRepository.findAll(spec, pageable).map(flightMapper::flightToFlightDto);
    }
}
