package com.flightmanager.UserService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Manager")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Manager extends User{

    private Boolean isBanned = false;


}
