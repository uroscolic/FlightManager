package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.domain.*;
import com.flightmanager.FlightBookingService.domain.Class;
import com.flightmanager.FlightBookingService.dto.TicketCreateDto;
import com.flightmanager.FlightBookingService.dto.TicketDto;
import com.flightmanager.FlightBookingService.mapper.TicketMapper;
import com.flightmanager.FlightBookingService.repository.FlightRepository;
import com.flightmanager.FlightBookingService.repository.TicketRepository;
import com.flightmanager.FlightBookingService.service.ITicketService;
import com.flightmanager.FlightBookingService.specification.TicketSpecification;
import com.flightmanager.FlightBookingService.userService.dto.ClientDto;
import com.flightmanager.FlightBookingService.userService.dto.DecrementBookCountDto;
import com.flightmanager.FlightBookingService.userService.dto.IncrementBookCountDto;
import io.github.resilience4j.retry.Retry;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.flightmanager.FlightBookingService.listener.helper.MessageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.flightmanager.FlightBookingService.domain.Package;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
@Getter
@Setter
@Service
@Transactional
public class TicketServiceImpl implements ITicketService {

    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;
    private FlightRepository flightRepository;
    private RestTemplate userServiceRestTemplate;
    private JmsTemplate jmsTemplate;
    private String incrementBookCountDestination;
    private String decrementReservationCountDestination;
    private MessageHelper messageHelper;
    private Retry userServiceRetry;


    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper,
                             FlightRepository flightRepository, RestTemplate userServiceRestTemplate,
                             JmsTemplate jmsTemplate,
                             @Value("${destination.incrementBookCount}") String incrementBookCountDestination,
                             @Value("${destination.decrementBookCount}") String decrementReservationCountDestination,
                             MessageHelper messageHelper, Retry userServiceRetry) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.flightRepository = flightRepository;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.incrementBookCountDestination = incrementBookCountDestination;
        this.decrementReservationCountDestination = decrementReservationCountDestination;
        this.messageHelper = messageHelper;
        this.userServiceRetry = userServiceRetry;
    }
    @Override
    public Page<TicketDto> getTickets(String ownerEmail, Boolean isReturn, Passenger passenger,
                                      Flight flight, Flight returnFlight, Class ticketClass, Package _package,
                                      Double from, Double to, Plane plane, LocalDateTime flightDepartureStart,
                                      LocalDateTime flightDepartureEnd, LocalDateTime flightArrivalStart,
                                      LocalDateTime flightArrivalEnd, LocalDateTime returnFlightDepartureStart,
                                      LocalDateTime returnFlightDepartureEnd, LocalDateTime returnFlightArrivalStart,
                                      LocalDateTime returnFlightArrivalEnd, Pageable pageable) {
        Specification<Ticket> spec = Specification.where(TicketSpecification.withOwner(ownerEmail))
                .and(isReturn != null ? TicketSpecification.isReturn(isReturn) : null)
                .and(passenger != null ? TicketSpecification.withPassenger(passenger) : null)
                .and(flight != null ? TicketSpecification.withFlight(flight) : null)
                .and(returnFlight != null ? TicketSpecification.withReturnFlight(returnFlight) : null)
                .and(ticketClass != null ? TicketSpecification.withTicketClass(ticketClass) : null)
                .and(_package != null ? TicketSpecification.withPackage(_package) : null)
                .and(from != null && to != null ? TicketSpecification.withTotalPriceBetween(from, to) : null)
                .and(plane != null ? TicketSpecification.onPlane(plane) : null)
                .and((flightDepartureStart != null && flightDepartureEnd != null) ?
                        TicketSpecification.withFlightDepartureTimeBetween(flightDepartureStart, flightDepartureEnd) : null)
                .and((flightArrivalStart != null && flightArrivalEnd != null) ?
                        TicketSpecification.withFlightArrivalTimeBetween(flightArrivalStart, flightArrivalEnd) : null)
                .and((returnFlightDepartureStart != null && returnFlightDepartureEnd != null) ?
                        TicketSpecification.withReturnFlightDepartureTimeBetween(returnFlightDepartureStart, returnFlightDepartureEnd) : null)
                .and((returnFlightArrivalStart != null && returnFlightArrivalEnd != null) ?
                        TicketSpecification.withReturnFlightArrivalTimeBetween(returnFlightArrivalStart, returnFlightArrivalEnd) : null);

        if(ticketMapper == null)
            System.out.println("ticketMapper is null");
        if(ticketRepository == null)
            System.out.println("ticketRepository is null");
        if(flightRepository == null)
            System.out.println("flightRepository is null");


        return ticketRepository.findAll(spec, pageable).map(ticketMapper :: ticketToTicketDto);
    }

    @Override
    public TicketDto createTicket(TicketCreateDto ticketCreateDto) {
        Ticket ticket = ticketMapper.ticketCreateDtoToTicket(ticketCreateDto);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureTime = ticket.getFlight().getDepartureTime();

        if (Duration.between(now, departureTime).toHours() < 12) {
            throw new RuntimeException("You can't buy a ticket less than 12 hours before the flight");
        }
        if(ticket.getFlight().getDepartureTime().isAfter(ticket.getFlight().getArrivalTime())) {
            throw new RuntimeException("Departure time can't be after arrival time");
        }

        if(ticket.isReturn() && ticket.getReturnFlight().getDepartureTime().isAfter(ticket.getReturnFlight().getArrivalTime())) {
            throw new RuntimeException("Departure time can't be after arrival time");
        }

        if(ticket.isReturn() && ticket.getFlight().getArrivalTime().isAfter(ticket.getReturnFlight().getDepartureTime())) {
            throw new RuntimeException("Return flight can't be before the arrival of the first flight");
        }



        Flight flight = ticket.getFlight();
        Class ticketClass = ticket.getTicketClass();
        helperCreate(flight, ticketClass);

        if(ticket.isReturn()) {
            flight = ticket.getReturnFlight();
            helperCreate(flight, ticketClass);
        }
        ClientDto clientDto = Retry.decorateSupplier(userServiceRetry, () -> getClient(ticketCreateDto.getOwner().getEmail())).get();
        if(clientDto == null) {
            throw new RuntimeException("Client does not exist");
        }


        TicketDto ticketDto = ticketMapper.ticketToTicketDto(ticketRepository.save(ticket));
        jmsTemplate.convertAndSend(incrementBookCountDestination, messageHelper.createTextMessage(new IncrementBookCountDto(clientDto.getId())));

        return ticketDto;
    }



    @Override
    public Long cancelTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null) {
            return -1L;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureTime = ticket.getFlight().getDepartureTime();

        if (Duration.between(now, departureTime).toHours() < 48) {
            throw new RuntimeException("You can't cancel a ticket less than 48 hours before the flight");
        }


        Class ticketClass = ticket.getTicketClass();
        Flight flight = ticket.getFlight();
        helper(ticketClass, flight);

        if(ticket.isReturn()) {
            flight = ticket.getReturnFlight();
            helper(ticketClass, flight);
        }
        ClientDto clientDto = Retry.decorateSupplier(userServiceRetry, () -> getClient(ticket.getOwner().getEmail())).get();
        if(clientDto == null) {
            throw new RuntimeException("Client does not exist");
        }
        ticketRepository.deleteById(id);
        jmsTemplate.convertAndSend(decrementReservationCountDestination, messageHelper.createTextMessage(new DecrementBookCountDto(clientDto.getId())));

        return id;
    }

    private ClientDto getClient(String email)
    {
        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        try {
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/email/" +
                    email, HttpMethod.GET, null, ClientDto.class);
            return clientDtoResponseEntity.getBody();
        }

        catch (HttpClientErrorException e)
        {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new RuntimeException("Client does not exist");
        }
        catch (Exception e)
        {
            throw new RuntimeException("User service is unavailable");
        }
        return null;
    }

    private void helper(Class ticketClass, Flight flight) {
        if(ticketClass == Class.BUSINESS) {
            flight.setAvailableBusinessSeats(flight.getAvailableBusinessSeats() + 1);
        } else if(ticketClass == Class.ECONOMY) {
            flight.setAvailableEconomySeats(flight.getAvailableEconomySeats() + 1);
        } else {
            flight.setAvailableFirstClassSeats(flight.getAvailableFirstClassSeats() + 1);
        }
        flightRepository.save(flight);
    }

    private void helperCreate(Flight flight, Class ticketClass) {

        if(ticketClass == Class.BUSINESS) {
            if(flight.getAvailableBusinessSeats() == 0) {
                throw new RuntimeException("No more business seats available");
            }
            flight.setAvailableBusinessSeats(flight.getAvailableBusinessSeats() - 1);
        } else if(ticketClass == Class.ECONOMY) {
            if(flight.getAvailableEconomySeats() == 0) {
                throw new RuntimeException("No more economy seats available");
            }
            flight.setAvailableEconomySeats(flight.getAvailableEconomySeats() - 1);
        } else {
            if(flight.getAvailableFirstClassSeats() == 0) {
                throw new RuntimeException("No more first class seats available");
            }
            flight.setAvailableFirstClassSeats(flight.getAvailableFirstClassSeats() - 1);
        }
        flightRepository.save(flight);
    }

}
