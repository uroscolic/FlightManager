package com.flightmanager.UserService.dto;

import com.flightmanager.UserService.domain.Role;
import com.flightmanager.UserService.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenResponseDto {

    private String token;
    private Long id;
    private String roleType;
    private String email;
    private String firstName;
    private String lastName;

}
