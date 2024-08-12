package com.flightmanager.FlightBookingService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "flight", uniqueConstraints = @UniqueConstraint(columnNames = {"gate", "origin_id", "departureTime"}))
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    private Plane plane;
    @OneToOne(optional = false)
    private Airport origin;
    @OneToOne(optional = false)
    private Airport destination;
    private String gate;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double price;
}
