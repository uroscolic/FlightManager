package com.flightmanager.FlightBookingService.mapper;

import com.flightmanager.FlightBookingService.domain.Coupon;
import com.flightmanager.FlightBookingService.dto.CouponChangeActivityDto;
import com.flightmanager.FlightBookingService.dto.CouponCreateDto;
import com.flightmanager.FlightBookingService.dto.CouponDto;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {

        public CouponDto couponToCouponDto(Coupon coupon) {
            CouponDto couponDto = new CouponDto();
            couponDto.setCouponCode(coupon.getCouponCode());
            couponDto.setDiscount(coupon.getDiscount());
            couponDto.setActive(coupon.isActive());
            return couponDto;
        }

        public Coupon couponCreateDtoToCoupon(CouponCreateDto couponCreateDto)
        {
            Coupon coupon = new Coupon();
            coupon.setCouponCode(couponCreateDto.getCouponCode());
            coupon.setDiscount(couponCreateDto.getDiscount());
            coupon.setActive(true);
            return coupon;
        }

        public Coupon couponChangeActivityDtoToCoupon(CouponDto oldCoupon, CouponChangeActivityDto couponChangeActivityDto)
        {
            Coupon coupon = new Coupon();
            coupon.setDiscount(oldCoupon.getDiscount());
            coupon.setCouponCode(couponChangeActivityDto.getCouponCode());
            coupon.setActive(couponChangeActivityDto.getIsActive());
            return coupon;
        }


}
