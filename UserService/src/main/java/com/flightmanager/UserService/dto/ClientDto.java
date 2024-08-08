package com.flightmanager.UserService.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.flightmanager.UserService.domain.Role;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDto extends UserDto{

    private Boolean isBanned = false;
}
