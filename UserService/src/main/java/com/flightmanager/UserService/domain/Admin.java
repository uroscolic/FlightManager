package com.flightmanager.UserService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("Admin")
@AllArgsConstructor
@Getter
@Setter
public class Admin extends User{

}
