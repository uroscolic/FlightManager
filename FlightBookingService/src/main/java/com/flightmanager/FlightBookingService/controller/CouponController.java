package com.flightmanager.FlightBookingService.controller;

import com.flightmanager.FlightBookingService.dto.CouponChangeActivityDto;
import com.flightmanager.FlightBookingService.dto.CouponCreateDto;
import com.flightmanager.FlightBookingService.dto.CouponDto;
import com.flightmanager.FlightBookingService.mapper.CouponMapper;
import com.flightmanager.FlightBookingService.security.CheckSecurity;
import com.flightmanager.FlightBookingService.service.ICouponService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@AllArgsConstructor
public class CouponController {

    private ICouponService iCouponService;
    private CouponMapper couponMapper;

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Page<CouponDto>> getAllCoupons(@RequestHeader("Authorization") String authorization,
                                                         Pageable pageable) {
        return new ResponseEntity<>(iCouponService.getAllCoupons(pageable), HttpStatus.OK);
    }

    @GetMapping("/{coupon-code}")
    //@CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<CouponDto> getCouponByCouponCode(//@RequestHeader("Authorization") String authorization,
                                                           @PathVariable("coupon-code") String couponCode) {
        return new ResponseEntity<>(iCouponService.getCouponByCouponCode(couponCode), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponCreateDto couponCreateDto,
                                                  @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(iCouponService.createCoupon(couponCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{couponCode}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<CouponDto> updateCoupon(@RequestHeader("Authorization") String authorization, @RequestBody CouponChangeActivityDto couponChangeActivityDto) {
        CouponDto couponDto = iCouponService.getCouponByCouponCode(couponChangeActivityDto.getCouponCode());
        return new ResponseEntity<>(iCouponService.updateCouponActivity(couponDto, couponChangeActivityDto), HttpStatus.OK);
    }
}
