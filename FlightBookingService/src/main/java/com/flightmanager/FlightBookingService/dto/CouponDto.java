package com.flightmanager.FlightBookingService.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {

    private String couponCode;
    private int discount;
    private boolean active;
}
