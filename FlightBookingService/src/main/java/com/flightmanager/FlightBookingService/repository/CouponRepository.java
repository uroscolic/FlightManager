package com.flightmanager.FlightBookingService.repository;

import com.flightmanager.FlightBookingService.domain.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

    @Query("SELECT c FROM Coupon c")
    Page<Coupon> findAllCoupons(Pageable pageable);
    Optional<Coupon> findCouponByCouponCode(String couponCode);
}
