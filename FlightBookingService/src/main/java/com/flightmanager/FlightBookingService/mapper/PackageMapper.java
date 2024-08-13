package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Package;
import com.flightmanager.FlightBookingService.dto.PackageCreateDto;
import com.flightmanager.FlightBookingService.dto.PackageDto;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper {

    public PackageDto packageToPackageDto(Package _package)
    {
        PackageDto packageDto = new PackageDto();
        packageDto.setId(_package.getId());
        packageDto.setName(_package.getName());
        return packageDto;
    }

    public Package packageCreateDtoToPackage(PackageCreateDto packageCreateDto)
    {
        Package _package = new Package();
        _package.setName(packageCreateDto.getName());
        return _package;
    }
}
