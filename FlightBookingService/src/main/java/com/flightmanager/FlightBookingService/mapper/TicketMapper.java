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
        System.out.println("Ticket: " + ticket.toString());
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setOwner(passengerMapper.passengerToPassengerDto(ticket.getOwner()));
        ticketDto.setPassenger(passengerMapper.passengerToPassengerDto(ticket.getPassenger()));
        ticketDto.setSeatNumber(ticket.getSeatNumber());
        ticketDto.setTicketClass(ticket.getTicketClass());
        ticketDto.setReturn(ticket.isReturn());

        PackageDto _package = new PackageDto();
        _package.setId(ticket.get_package().getId());
        _package.setName(ticket.get_package().getName());

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
        ticket.setTicketClass(ticketCreateDto.getTicketClass());
        ticket.setReturn(ticketCreateDto.isReturn());

        Package _package = new Package();
        _package.setId(ticketCreateDto.get_package().getId());
        _package.setName(ticketCreateDto.get_package().getName());

        ticket.set_package(_package);
        ticket.setFlight(flightMapper.getFlightFromDto(ticketCreateDto.getFlight()));

        if(ticketCreateDto.getReturnFlight() != null)
            ticket.setReturnFlight(flightMapper.getFlightFromDto(ticketCreateDto.getReturnFlight()));
        ticket.setTotalPrice(ticketCreateDto.getTotalPrice());
        return ticket;
    }



}
