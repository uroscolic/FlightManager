package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Ticket;
import com.flightmanager.FlightBookingService.dto.TicketCreateDto;
import com.flightmanager.FlightBookingService.dto.TicketDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDto ticketToTicketDto(Ticket ticket)
    {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setOwner(ticket.getOwner());
        ticketDto.setPassenger(ticket.getPassenger());
        ticketDto.setSeatNumber(ticket.getSeatNumber());
        ticketDto.setTicketClass(ticket.getTicketClass());
        ticketDto.setReturn(ticket.isReturn());
        ticketDto.set_package(ticket.get_package());
        ticketDto.setFlight(ticket.getFlight());
        ticketDto.setReturnFlight(ticket.getReturnFlight());
        ticketDto.setTotalPrice(ticket.getTotalPrice());
        return ticketDto;
    }

    public Ticket ticketCreateDtoToTicket(TicketCreateDto ticketCreateDto) {
        Ticket ticket = new Ticket();
        ticket.setOwner(ticketCreateDto.getOwner());
        ticket.setPassenger(ticketCreateDto.getPassenger());
        ticket.setSeatNumber(ticketCreateDto.getSeatNumber());
        ticket.setTicketClass(ticketCreateDto.getTicketClass());
        ticket.setReturn(ticketCreateDto.isReturn());
        ticket.set_package(ticketCreateDto.get_package());
        ticket.setFlight(ticketCreateDto.getFlight());
        ticket.setReturnFlight(ticketCreateDto.getReturnFlight());
        ticket.setTotalPrice(ticketCreateDto.getTotalPrice());
        return ticket;
    }
}
