package com.flightmanager.FlightBookingService.service.impl;

import com.flightmanager.FlightBookingService.dto.CouponDto;
import com.flightmanager.FlightBookingService.mapper.CouponMapper;
import com.flightmanager.FlightBookingService.repository.CouponRepository;
import com.flightmanager.FlightBookingService.service.ICouponService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@Transactional
@AllArgsConstructor
public class CouponServiceImpl implements ICouponService {

    private CouponRepository couponRepository;
    private CouponMapper couponMapper;

    @Override
    public Page<CouponDto> getAllCoupons(Pageable pageable) {
        return couponRepository.findAllCoupons(pageable).map(couponMapper::couponToCouponDto);
    }

    @Override
    public CouponDto getCouponByCouponCode(String couponCode) {
        return couponMapper.couponToCouponDto(couponRepository.findCouponByCouponCode(couponCode).orElseThrow(() -> new RuntimeException("Coupon not found")));
    }
}
