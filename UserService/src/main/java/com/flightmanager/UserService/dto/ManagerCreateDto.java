package com.flightmanager.UserService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ManagerCreateDto extends UserCreateDto{


    public ManagerCreateDto() {
        super();
    }
    public ManagerCreateDto(String firstName, String lastName, String password, String email) {
        super(firstName, lastName, password, email);

    }
}
