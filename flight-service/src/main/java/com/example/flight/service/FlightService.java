package com.example.flight.service;

import com.example.flight.model.Flight;
import com.example.flight.repo.FlightRepo;
import com.example.flight.sink.FlightSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class FlightService {
    @Autowired
    private FlightRepo flightRepo;
    @Autowired
    private FlightSink flightSink;
    public Mono<Flight> saveFlight(Flight flight){
        return flightRepo.save(flight)
                .doOnNext(f->flightSink.getSink().tryEmitNext(f));
    }
    public Flux<Flight> getByDate(LocalDate date){
        return flightRepo.findByFlightDate(date);
    }
    public Flux<Flight> getByDateAndAirline(LocalDate date,String airlineCode){
        return flightRepo.findByFlightDateAndAirlineCode(date,airlineCode);
    }
    public Flux<Flight> getByAirline(String airlineCode){
        return flightRepo.findByAirlineCode(airlineCode);
    }
    public Flux<Flight> getAll() {
        return flightRepo.findAll()
                .delayElements(Duration.ofMillis(2));
    }
    public Flux<Flight> getLiveUpdate(){
        return flightSink.getSink().asFlux();
    }
}
