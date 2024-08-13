package com.flightmanager.FlightBookingService.mapper;
import com.flightmanager.FlightBookingService.domain.Location;
import com.flightmanager.FlightBookingService.dto.LocationDto;
import com.flightmanager.FlightBookingService.dto.LocationCreateDto;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {


    public LocationDto locationToLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setCountry(location.getCountry());
        locationDto.setCity(location.getCity());
        locationDto.setShortName(location.getShortName());
        locationDto.setImagePath(location.getImagePath());
        return locationDto;
    }

    public Location locationCreateDtoToLocation(LocationCreateDto locationCreateDto) {
        Location location = new Location();
        location.setCountry(locationCreateDto.getCountry());
        location.setCity(locationCreateDto.getCity());
        location.setShortName(locationCreateDto.getShortName());
        location.setImagePath(locationCreateDto.getImagePath());
        return location;
    }
}
