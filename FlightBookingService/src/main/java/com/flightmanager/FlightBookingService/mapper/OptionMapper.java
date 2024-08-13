package com.flightmanager.FlightBookingService.mapper;
import com.flightmanager.FlightBookingService.domain.Option;
import com.flightmanager.FlightBookingService.dto.OptionCreateDto;
import com.flightmanager.FlightBookingService.dto.OptionDto;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {

    public OptionDto optionToOptionDto(Option option)
    {
        OptionDto optionDto = new OptionDto();
        optionDto.setId(option.getId());
        optionDto.setName(option.getName());
        optionDto.setPrice(option.getPrice());
        return optionDto;
    }

    public Option optionCreateDtoToOption(OptionCreateDto optionCreateDto)
    {
        Option option = new Option();
        option.setName(optionCreateDto.getName());
        option.setPrice(optionCreateDto.getPrice());
        return option;
    }
}
