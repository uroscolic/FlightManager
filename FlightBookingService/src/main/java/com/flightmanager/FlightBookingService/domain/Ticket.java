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
    @OneToOne(optional = false)
    private Passenger owner;
    @OneToOne(optional = false)
    private Passenger passenger;
    private int seatNumber;
    private Class ticketClass;
    private boolean isReturn = false;
    @OneToOne(optional = false)
    private Package _package;
    @OneToOne(optional = false)
    private Flight flight;
    @OneToOne
    private Flight returnFlight;
    private Double totalPrice;

}
