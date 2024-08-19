package com.flightmanager.FlightBookingService.userService.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
