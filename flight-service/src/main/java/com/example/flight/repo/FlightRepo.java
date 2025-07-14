package com.example.flight.repo;

import com.example.flight.model.Flight;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface FlightRepo extends ReactiveMongoRepository<Flight,String> {
    Flux<Flight> findByFlightDate(LocalDate date);
    Flux<Flight> findByAirlineCode(String airlineCode);
    Flux<Flight> findByFlightDateAndAirlineCode(LocalDate date,String airlineCode);
}
