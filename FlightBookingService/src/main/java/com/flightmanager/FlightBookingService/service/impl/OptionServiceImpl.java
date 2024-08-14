package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.dto.OptionCreateDto;
import com.flightmanager.FlightBookingService.dto.OptionDto;
import com.flightmanager.FlightBookingService.mapper.OptionMapper;
import com.flightmanager.FlightBookingService.repository.OptionRepository;
import com.flightmanager.FlightBookingService.service.IOptionService;
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
public class OptionServiceImpl implements IOptionService {

    private OptionRepository optionRepository;
    private OptionMapper optionMapper;

    @Override
    public Page<OptionDto> getAllOptions(Pageable pageable) {
        return optionRepository.findAllOptions(pageable).map(optionMapper::optionToOptionDto);
    }

    @Override
    public OptionDto getOptionByName(String name) {
        return optionMapper.optionToOptionDto(optionRepository.findOptionByName(name).orElseThrow(() -> new RuntimeException("Option not found")));
    }

    @Override
    public OptionDto createOption(OptionCreateDto optionCreateDto) {
        return optionMapper.optionToOptionDto(optionRepository.save(optionMapper.optionCreateDtoToOption(optionCreateDto)));
    }

}
