package com.flightmanager.FlightBookingService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    private String couponCode;
    private int discount;
    private boolean active;
}
