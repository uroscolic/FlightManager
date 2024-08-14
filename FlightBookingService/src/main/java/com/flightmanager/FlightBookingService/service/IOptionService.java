package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.dto.OptionCreateDto;
import com.flightmanager.FlightBookingService.dto.OptionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOptionService {

    Page<OptionDto> getAllOptions(Pageable pageable);
    OptionDto getOptionByName(String name);
    OptionDto createOption(OptionCreateDto optionCreateDto);
}
