package com.flightmanager.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "First name should not be blank")
    private String firstName;
    @NotBlank(message = "Last name should not be blank")
    private String lastName;
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;


}
