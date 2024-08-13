package com.flightmanager.FlightBookingService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponChangeActivityDto {
    @NotBlank(message = "Coupon code cannot be empty")
    private String couponCode;
    private Boolean isActive;
}
