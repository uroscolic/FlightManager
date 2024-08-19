package com.flightmanager.FlightBookingService.userService.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDto extends UserDto {
    private int numberOfBookings;
}
