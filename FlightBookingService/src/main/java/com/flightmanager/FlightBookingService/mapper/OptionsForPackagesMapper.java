package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Option;
import com.flightmanager.FlightBookingService.domain.OptionsForPackages;
import com.flightmanager.FlightBookingService.domain.Package;
import com.flightmanager.FlightBookingService.dto.OptionDto;
import com.flightmanager.FlightBookingService.dto.OptionsForPackagesCreateDto;
import com.flightmanager.FlightBookingService.dto.OptionsForPackagesDto;
import com.flightmanager.FlightBookingService.dto.PackageDto;
import org.springframework.stereotype.Component;

@Component
public class OptionsForPackagesMapper {

    public OptionsForPackagesDto optionsForPackagesToOptionsForPackagesDto(OptionsForPackages optionsForPackages) {
        OptionsForPackagesDto optionsForPackagesDto = new OptionsForPackagesDto();
        optionsForPackagesDto.setId(optionsForPackages.getId());
        optionsForPackagesDto.set_package(new PackageDto(optionsForPackages.get_package().getId(), optionsForPackages.get_package().getName(), optionsForPackages.get_package().getPrice()));
        optionsForPackagesDto.setOption(new OptionDto(optionsForPackages.getOption().getId(), optionsForPackages.getOption().getName(), optionsForPackages.getOption().getPrice()));
        return optionsForPackagesDto;
    }

    public OptionsForPackages optionsForPackagesCreateDtoToOptionsForPackages(OptionsForPackagesCreateDto optionsForPackagesCreateDto) {
        OptionsForPackages optionsForPackages = new OptionsForPackages();
        optionsForPackages.set_package(new Package(optionsForPackagesCreateDto.get_package().getId(), optionsForPackagesCreateDto.get_package().getName(), optionsForPackagesCreateDto.get_package().getPrice()));
        optionsForPackages.setOption(new Option(optionsForPackagesCreateDto.getOption().getId(), optionsForPackagesCreateDto.getOption().getName(), optionsForPackagesCreateDto.getOption().getPrice()));
        return optionsForPackages;
    }
}
