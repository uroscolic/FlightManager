package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Plane;
import com.flightmanager.FlightBookingService.dto.PlaneCreateDto;
import com.flightmanager.FlightBookingService.dto.PlaneDto;
import org.springframework.stereotype.Component;

@Component
public class PlaneMapper {


    public PlaneDto planeToPlaneDto(Plane plane)
    {
        PlaneDto planeDto = new PlaneDto();
        planeDto.setId(plane.getId());
        planeDto.setName(plane.getName());
        planeDto.setEconomySeats(plane.getEconomySeats());
        planeDto.setBusinessSeats(plane.getBusinessSeats());
        planeDto.setFirstClassSeats(plane.getFirstClassSeats());
        return planeDto;
    }

    public Plane planeCreateDtoToPlane(PlaneCreateDto planeCreateDto)
    {
        Plane plane = new Plane();
        plane.setName(planeCreateDto.getName());
        plane.setBusinessSeats(planeCreateDto.getBusinessSeats());
        plane.setEconomySeats(planeCreateDto.getEconomySeats());
        plane.setFirstClassSeats(planeCreateDto.getFirstClassSeats());
        return plane;
    }
}
