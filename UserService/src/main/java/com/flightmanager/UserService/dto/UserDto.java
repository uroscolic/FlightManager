package com.flightmanager.UserService.dto;

import com.flightmanager.UserService.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
