package com.flightmanager.UserService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client extends User{

    private boolean isActive = false;
    private boolean isBanned = false;


}
