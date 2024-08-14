package com.flightmanager.FlightBookingService.service;

import com.flightmanager.FlightBookingService.domain.Coupon;
import com.flightmanager.FlightBookingService.dto.CouponChangeActivityDto;
import com.flightmanager.FlightBookingService.dto.CouponCreateDto;
import com.flightmanager.FlightBookingService.dto.CouponDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICouponService {

    Page<CouponDto> getAllCoupons(Pageable pageable);
    CouponDto getCouponByCouponCode(String couponCode);
    CouponDto createCoupon(CouponCreateDto couponCreateDto);
    CouponDto updateCouponActivity(CouponDto oldCoupon, CouponChangeActivityDto couponChangeActivityDto);
}
