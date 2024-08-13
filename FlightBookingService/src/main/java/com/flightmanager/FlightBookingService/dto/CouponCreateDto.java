package com.flightmanager.FlightBookingService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponCreateDto {
    @NotBlank(message = "Coupon code cannot be empty")
    private String couponCode;
    @Min(value = 1, message = "Discount must be greater than 0")
    private int discount;
}
