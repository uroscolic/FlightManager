package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.domain.*;
import com.flightmanager.FlightBookingService.domain.Class;
import com.flightmanager.FlightBookingService.domain.Package;
import com.flightmanager.FlightBookingService.dto.TicketCreateDto;
import com.flightmanager.FlightBookingService.dto.TicketDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.security.service.TokenService;
import com.flightmanager.FlightBookingService.service.ITicketService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private ITicketService iTicketService;
    private TokenService tokenService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @CheckSecurity(roles = {"ROLE_CLIENT", "ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<Page<TicketDto>> getTickets(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false) String ownerEmail,
            @RequestParam(required = false) Boolean isReturn,
            @RequestParam(required = false) Passenger passenger,
            @RequestParam(required = false) Flight flight,
            @RequestParam(required = false) Flight returnFlight,
            @RequestParam(required = false) Class ticketClass,
            @RequestParam(required = false) Package _package,
            @RequestParam(required = false) Double from,
            @RequestParam(required = false) Double to,
            @RequestParam(required = false) Plane plane,
            @RequestParam(required = false) LocalDateTime flightDepartureStart,
            @RequestParam(required = false) LocalDateTime flightDepartureEnd,
            @RequestParam(required = false) LocalDateTime flightArrivalStart,
            @RequestParam(required = false) LocalDateTime flightArrivalEnd,
            @RequestParam(required = false) LocalDateTime returnFlightDepartureStart,
            @RequestParam(required = false) LocalDateTime returnFlightDepartureEnd,
            @RequestParam(required = false) LocalDateTime returnFlightArrivalStart,
            @RequestParam(required = false) LocalDateTime returnFlightArrivalEnd,
            Pageable pageable){

        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        if(claims.get("role").toString().equals("ROLE_CLIENT") && !ownerEmail.equals(claims.get("email").toString()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(iTicketService.getTickets(ownerEmail, isReturn, passenger, flight, returnFlight,
                ticketClass, _package, from, to, plane, flightDepartureStart, flightDepartureEnd,
                flightArrivalStart, flightArrivalEnd, returnFlightDepartureStart, returnFlightDepartureEnd,
                returnFlightArrivalStart, returnFlightArrivalEnd, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketCreateDto ticketCreateDto){
        return new ResponseEntity<>(iTicketService.createTicket(ticketCreateDto), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> cancelTicket(@PathVariable("id") Long id){
        return new ResponseEntity<>(iTicketService.cancelTicket(id), HttpStatus.OK);
    }
}
