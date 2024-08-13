package com.flightmanager.FlightBookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassengerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
