package com.flightmanager.FlightBookingService.specification;

import com.flightmanager.FlightBookingService.domain.Plane;
import org.springframework.data.jpa.domain.Specification;

public class PlaneSpecification {

    public static Specification<Plane> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Plane> hasBusinessSeatsGreaterThan(Integer numberOfSeats) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("businessSeats"), numberOfSeats);
    }

    public static Specification<Plane> hasFirstClassSeatsGreaterThan(Integer numberOfSeats) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("firstClassSeats"), numberOfSeats);
    }

    public static Specification<Plane> hasEconomySeatsGreaterThan(Integer numberOfSeats) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("economySeats"), numberOfSeats);
    }
}