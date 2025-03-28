package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.*;

import com.flightmanager.FlightBookingService.domain.Package;
import com.flightmanager.FlightBookingService.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TicketMapper {


    private PassengerMapper passengerMapper;
    private FlightMapper flightMapper;

    public TicketDto ticketToTicketDto(Ticket ticket)
    {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setOwner(passengerMapper.passengerToPassengerDto(ticket.getOwner()));
        ticketDto.setPassenger(passengerMapper.passengerToPassengerDto(ticket.getPassenger()));
        ticketDto.setSeatNumber(ticket.getSeatNumber());
        ticketDto.setReturnSeatNumber(ticket.getReturnSeatNumber());
        ticketDto.setTicketClass(ticket.getTicketClass());
        ticketDto.set_return(ticket.is_return());

        PackageDto _package = new PackageDto();
        _package.setId(ticket.get_package().getId());
        _package.setName(ticket.get_package().getName());
        _package.setPrice(ticket.get_package().getPrice());

        ticketDto.set_package(_package);
        ticketDto.setFlight(flightMapper.flightToFlightDto(ticket.getFlight()));

        if(ticket.getReturnFlight() != null)
            ticketDto.setReturnFlight(flightMapper.flightToFlightDto(ticket.getReturnFlight()));
        ticketDto.setTotalPrice(ticket.getTotalPrice());
        return ticketDto;
    }



    public Ticket ticketCreateDtoToTicket(TicketCreateDto ticketCreateDto) {
        Ticket ticket = new Ticket();
        ticket.setOwner(passengerMapper.getPassengerFromDto(ticketCreateDto.getOwner()));
        ticket.setPassenger(passengerMapper.getPassengerFromDto(ticketCreateDto.getPassenger()));
        ticket.setSeatNumber(ticketCreateDto.getSeatNumber());
        ticket.setReturnSeatNumber(ticketCreateDto.getReturnSeatNumber());
        ticket.setTicketClass(ticketCreateDto.getTicketClass());
        ticket.set_return(ticketCreateDto.is_return());

        Package _package = new Package();
        _package.setId(ticketCreateDto.get_package().getId());
        _package.setName(ticketCreateDto.get_package().getName());
        _package.setPrice(ticketCreateDto.get_package().getPrice());

        ticket.set_package(_package);
        ticket.setFlight(flightMapper.getFlightFromDto(ticketCreateDto.getFlight()));

        if(ticketCreateDto.getReturnFlight() != null)
            ticket.setReturnFlight(flightMapper.getFlightFromDto(ticketCreateDto.getReturnFlight()));
        ticket.setTotalPrice(ticketCreateDto.getTotalPrice());
        return ticket;
    }



}
