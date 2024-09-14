package com.flightmanager.FlightBookingService.runner;

import com.flightmanager.FlightBookingService.domain.*;
import com.flightmanager.FlightBookingService.domain.Class;
import com.flightmanager.FlightBookingService.domain.Package;
import com.flightmanager.FlightBookingService.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Profile({"default"})
@Component
@AllArgsConstructor
public class TestDataRunner implements CommandLineRunner {

    private PassengerRepository passengerRepository;
    private PlaneRepository planeRepository;
    private FlightRepository flightRepository;
    private AirportRepository airportRepository;
    private PackageRepository packageRepository;
    private TicketRepository ticketRepository;
    private CouponRepository couponRepository;
    private OptionsForPackagesRepository optionsForPackagesRepository;
    private OptionRepository optionRepository;
    private LocationRepository locationRepository;




    @Override
    public void run(String... args) throws Exception {

        Passenger passenger = new Passenger();
        passenger.setFirstName("Client");
        passenger.setLastName("Client");
        passenger.setEmail("client@gmail.com");

        passengerRepository.save(passenger);

        Package _package = new Package();
        _package.setName("Package 1");
        _package.setPrice(10.0);

        packageRepository.save(_package);

        Plane plane = new Plane();
        plane.setName("Plane 1");
        plane.setBusinessSeats(11);
        plane.setEconomySeats(11);
        plane.setFirstClassSeats(11);

        planeRepository.save(plane);

        Airport airport = new Airport();
        airport.setName("Airport 1");


        Location location = new Location();
        location.setCountry("Serbia");
        location.setCity("Belgrade");
        location.setShortName("SRB-BG");

        Location location1 = new Location();
        location1.setCountry("Serbia");
        location1.setCity("Novi Sad");
        location1.setShortName("SRB-NS");

        locationRepository.save(location);
        locationRepository.save(location1);
        airport.setLocation(location);

        airportRepository.save(airport);

        Airport airport1 = new Airport();
        airport1.setName("Airport 2");
        airport1.setLocation(location1);

        airportRepository.save(airport1);



        Flight flight = new Flight();
        flight.setPlane(plane);
        flight.setOrigin(airport);
        flight.setDestination(airport1);
        flight.setGate("A1");
        flight.setDepartureTime(LocalDateTime.now().plusMonths(1));
        flight.setArrivalTime(LocalDateTime.now().plusMonths(1).plusHours(1));
        flight.setAvailableEconomySeats(11);
        flight.setAvailableBusinessSeats(11);
        flight.setAvailableFirstClassSeats(11);
        flight.setPrice(100.0);


        Flight returnFlight = new Flight();
        returnFlight.setPlane(plane);
        returnFlight.setOrigin(airport1);
        returnFlight.setDestination(airport);
        returnFlight.setGate("A4");
        returnFlight.setDepartureTime(LocalDateTime.now().plusMonths(2));
        returnFlight.setArrivalTime(LocalDateTime.now().plusMonths(2).plusHours(1));
        returnFlight.setAvailableEconomySeats(11);
        returnFlight.setAvailableBusinessSeats(11);
        returnFlight.setAvailableFirstClassSeats(11);
        returnFlight.setPrice(100.0);


        flightRepository.save(flight);
        flightRepository.save(returnFlight);


        Coupon coupon = new Coupon();
        coupon.setCouponCode("123");
        coupon.setDiscount(10);
        coupon.setActive(true);

        couponRepository.save(coupon);

        Option option = new Option();
        option.setName("Option 1");
        option.setPrice(10.0);

        optionRepository.save(option);

        OptionsForPackages optionsForPackages = new OptionsForPackages();
        optionsForPackages.setOption(option);
        optionsForPackages.set_package(_package);

        optionsForPackagesRepository.save(optionsForPackages);





    }
}
