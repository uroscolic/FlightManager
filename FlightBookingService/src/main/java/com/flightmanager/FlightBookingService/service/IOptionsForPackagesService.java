package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.domain.Option;
import com.flightmanager.FlightBookingService.dto.OptionDto;
import com.flightmanager.FlightBookingService.dto.OptionsForPackagesDto;
import com.flightmanager.FlightBookingService.dto.PackageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOptionsForPackagesService {

    Page<OptionsForPackagesDto> getByOptionName(String optionName, Pageable pageable);
    Page<OptionsForPackagesDto> getByPackageName(String packageName, Pageable pageable);
    OptionsForPackagesDto getOptionsForPackagesByPackageAndOption(String packageName, String optionName);
}
