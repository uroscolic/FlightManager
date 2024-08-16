package com.flightmanager.FlightBookingService.specification;

import com.flightmanager.FlightBookingService.domain.Class;
import com.flightmanager.FlightBookingService.domain.Flight;
import com.flightmanager.FlightBookingService.domain.Passenger;
import com.flightmanager.FlightBookingService.domain.Plane;
import com.flightmanager.FlightBookingService.domain.Ticket;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TicketSpecification {

    public static Specification<Ticket> withOwner(String email) {

        System.out.println("Email: " + email);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("owner").get("email"), email);
    }

    public static Specification<Ticket> isReturn(Boolean isReturn) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isReturn"), isReturn);
    }

    public static Specification<Ticket> withPassenger(Passenger passenger) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("passenger"), passenger);
    }

    public static Specification<Ticket> withFlight(Flight flight) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("flight"), flight);
    }

    public static Specification<Ticket> withReturnFlight(Flight returnFlight) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("returnFlight"), returnFlight);
    }

    public static Specification<Ticket> withTicketClass(Class ticketClass) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("ticketClass"), ticketClass);
    }

    public static Specification<Ticket> withPackage(com.flightmanager.FlightBookingService.domain.Package _package) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("_package"), _package);
    }

    public static Specification<Ticket> withTotalPriceBetween(Double from, Double to) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("totalPrice"), from, to);
    }

    public static Specification<Ticket> onPlane(Plane plane) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("flight").get("plane"), plane);
    }

    public static Specification<Ticket> withFlightDepartureTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("flight").get("departureTime"), start, end);
    }

    public static Specification<Ticket> withFlightArrivalTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("flight").get("arrivalTime"), start, end);
    }

    public static Specification<Ticket> withReturnFlightDepartureTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("returnFlight").get("departureTime"), start, end);
    }

    public static Specification<Ticket> withReturnFlightArrivalTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("returnFlight").get("arrivalTime"), start, end);
    }
}
