package com.flightmanager.FlightBookingService.specification;

import com.flightmanager.FlightBookingService.domain.Airport;
import com.flightmanager.FlightBookingService.domain.Flight;
import com.flightmanager.FlightBookingService.domain.Plane;
import org.springframework.data.jpa.domain.Specification;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

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

    public static Specification<Flight> withAvailableEconomySeats(int from) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("availableEconomySeats"), from);
    }

    public static Specification<Flight> withAvailableBusinessSeats(int from) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("availableBusinessSeats"), from);
    }

    public static Specification<Flight> withAvailableFirstClassSeats(int from) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("availableFirstClassSeats"), from);
    }

    public static Specification<Flight> withDepartureStartAt(LocalDateTime start) {
        LocalDate startDate = start.toLocalDate();
        LocalDateTime startDateTime = startDate.plusDays(1).atStartOfDay(); // 10/12/2024 it sends as 10/11/2024 22:00 gmt+0200
        LocalDateTime endDateTime = startDate.plusDays(2).atStartOfDay();
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("departureTime"), startDateTime, endDateTime);
    }

    public static Specification<Flight> withArrivalEndAt(LocalDateTime end) {
        LocalDate endDate = end.plusHours(2).toLocalDate();
        LocalDateTime startDateTime = endDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("arrivalTime"), startDateTime, endDateTime);

    }

}
