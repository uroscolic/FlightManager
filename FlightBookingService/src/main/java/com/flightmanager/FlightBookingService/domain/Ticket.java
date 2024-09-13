package com.flightmanager.FlightBookingService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Passenger owner;
    @ManyToOne(optional = false)
    private Passenger passenger;
    private int seatNumber;
    private int returnSeatNumber;
    private Class ticketClass;
    private boolean _return = false;
    @ManyToOne(optional = false)
    private Package _package;
    @ManyToOne(optional = false)
    private Flight flight;
    @ManyToOne
    private Flight returnFlight;
    private Double totalPrice;

}
