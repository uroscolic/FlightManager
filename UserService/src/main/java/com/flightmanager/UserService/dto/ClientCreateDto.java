package com.flightmanager.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientCreateDto extends UserCreateDto{
    public ClientCreateDto() {
        super();
    }
    public ClientCreateDto(String firstName, String lastName,  String password, String email) {
        super(firstName, lastName, password, email);
    }

}
