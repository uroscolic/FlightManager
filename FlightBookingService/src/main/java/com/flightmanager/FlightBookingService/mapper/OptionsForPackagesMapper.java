package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.OptionsForPackages;
import com.flightmanager.FlightBookingService.dto.OptionsForPackagesCreateDto;
import com.flightmanager.FlightBookingService.dto.OptionsForPackagesDto;
import org.springframework.stereotype.Component;

@Component
public class OptionsForPackagesMapper {

    public OptionsForPackagesDto optionsForPackagesToOptionsForPackagesDto(OptionsForPackages optionsForPackages) {
        OptionsForPackagesDto optionsForPackagesDto = new OptionsForPackagesDto();
        optionsForPackagesDto.setId(optionsForPackages.getId());
        optionsForPackagesDto.set_package(optionsForPackages.get_package());
        optionsForPackagesDto.setOption(optionsForPackages.getOption());
        return optionsForPackagesDto;
    }

    public OptionsForPackages optionsForPackagesCreateDtoToOptionsForPackages(OptionsForPackagesCreateDto optionsForPackagesCreateDto) {
        OptionsForPackages optionsForPackages = new OptionsForPackages();
        optionsForPackages.set_package(optionsForPackagesCreateDto.get_package());
        optionsForPackages.setOption(optionsForPackagesCreateDto.getOption());
        return optionsForPackages;
    }
}
