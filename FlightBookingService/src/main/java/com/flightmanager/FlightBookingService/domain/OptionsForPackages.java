package com.flightmanager.FlightBookingService.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "options_for_packages", uniqueConstraints = @UniqueConstraint(columnNames = {"option_id", "package_id"}))
@Data
public class OptionsForPackages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Option option;
    @ManyToOne(optional = false)
    private Package _package;



}
