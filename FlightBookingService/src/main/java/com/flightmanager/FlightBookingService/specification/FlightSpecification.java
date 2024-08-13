package com.flightmanager.FlightBookingService.specification;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Flight;
import com.flightmanager.FlightBookingService.domain.Plane;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class FlightSpecification {

    public static Specification<Flight> onPlane(Plane plane) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("plane"), plane);
    }

    public static Specification<Flight> fromOrigin(Airport origin) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("origin"), origin);
    }

    public static Specification<Flight> toDestination(Airport destination) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("destination"), destination);
    }

    public static Specification<Flight> withGate(String gate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("gate"), gate);
    }

    public static Specification<Flight> withDepartureTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("departureTime"), start, end);
    }

    public static Specification<Flight> withArrivalTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("arrivalTime"), start, end);
    }

    public static Specification<Flight> withPriceBetween(Double from, Double to) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), from, to);
    }

}
