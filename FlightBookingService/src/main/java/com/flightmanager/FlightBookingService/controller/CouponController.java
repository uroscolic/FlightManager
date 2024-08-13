package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.CouponDto;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.service.ICouponService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
@AllArgsConstructor
public class CouponController {

    private ICouponService iCouponService;

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Page<CouponDto>> getAllCoupons(@RequestHeader("Authorization") String authorization,
                                                         Pageable pageable) {
        return new ResponseEntity<>(iCouponService.getAllCoupons(pageable), HttpStatus.OK);
    }

    @GetMapping("/coupon-code")
    //@CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<CouponDto> getCouponByCouponCode(//@RequestHeader("Authorization") String authorization,
                                                           String couponCode) {
        return new ResponseEntity<>(iCouponService.getCouponByCouponCode(couponCode), HttpStatus.OK);
    }
}
