package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.domain.OptionsForPackages;
import com.flightmanager.FlightBookingService.dto.OptionsForPackagesCreateDto;
import com.flightmanager.FlightBookingService.dto.OptionsForPackagesDto;
import com.flightmanager.FlightBookingService.mapper.OptionsForPackagesMapper;
import com.flightmanager.FlightBookingService.repository.OptionsForPackagesRepository;
import com.flightmanager.FlightBookingService.service.IOptionsForPackagesService;
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
public class OptionsForPackagesServiceImpl implements IOptionsForPackagesService {

    private OptionsForPackagesRepository optionsForPackagesRepository;
    private OptionsForPackagesMapper optionsForPackagesMapper;

    @Override
    public Page<OptionsForPackagesDto> getByOptionName(String optionName, Pageable pageable) {
        return optionsForPackagesRepository.findByOptionName(optionName, pageable).map(optionsForPackagesMapper::optionsForPackagesToOptionsForPackagesDto);
    }


    @Override
    public Page<OptionsForPackagesDto> getByPackageName(String packageName,Pageable pageable) {
        return optionsForPackagesRepository.findBy_packageName(packageName, pageable).map(optionsForPackagesMapper::optionsForPackagesToOptionsForPackagesDto);
    }

    @Override
    public OptionsForPackagesDto getOptionsForPackagesByPackageAndOption(String packageName, String optionName) {
        return optionsForPackagesMapper.optionsForPackagesToOptionsForPackagesDto(optionsForPackagesRepository.findOptionsForPackagesBy_packageNameAndOptionName(packageName, optionName).orElseThrow(() -> new RuntimeException("Options for package not found")));
    }

    @Override
    public OptionsForPackagesDto createOptionsForPackages(OptionsForPackagesCreateDto optionsForPackagesCreateDto) {
        OptionsForPackages optionsForPackages = optionsForPackagesMapper.optionsForPackagesCreateDtoToOptionsForPackages(optionsForPackagesCreateDto);
        optionsForPackagesRepository.save(optionsForPackages);
        return optionsForPackagesMapper.optionsForPackagesToOptionsForPackagesDto(optionsForPackages);
    }
}
