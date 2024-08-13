package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.dto.PackageDto;
import com.flightmanager.FlightBookingService.mapper.PackageMapper;
import com.flightmanager.FlightBookingService.repository.PackageRepository;
import com.flightmanager.FlightBookingService.service.IPackageService;
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
public class PackageServiceImpl implements IPackageService {

    private PackageRepository packageRepository;
    private PackageMapper packageMapper;

    @Override
    public Page<PackageDto> getAllPackages(Pageable pageable) {
        return packageRepository.findAllPackages(pageable).map(packageMapper::packageToPackageDto);
    }

    @Override
    public PackageDto getPackageByName(String name) {
        return packageMapper.packageToPackageDto(packageRepository.findPackageByName(name).orElseThrow(() -> new RuntimeException("Package not found")));
    }
}
