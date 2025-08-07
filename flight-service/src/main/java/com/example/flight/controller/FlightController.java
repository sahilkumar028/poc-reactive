package com.example.flight.controller;

import com.example.flight.model.Flight;
import com.example.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/upload")
    public Mono<ResponseEntity<String>> uploadFlights(@RequestBody Flux<Flight> flightFlux){
        return flightFlux
                .onBackpressureBuffer(1000)
                .flatMap(f-> flightService.saveFlight(f))
                .then(Mono.just(ResponseEntity.ok("Flight uploaded successfully")));
    }

    @GetMapping(value="/getFlight/date",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Flight>  getFlightsByDate(@RequestParam String date){
        return flightService.getByDate(LocalDate.parse(date));
    }

    @GetMapping("/getFlight/airlineCode")
    public Flux<Flight> getFlightsByAirlineCode(@RequestParam String airlineCode){
        return flightService.getByAirline(airlineCode);
    }

    @GetMapping(value="/getFlight",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Flight> getFlight(@RequestParam String date, @RequestParam String airlineCode){
        return flightService.getByDateAndAirline(LocalDate.parse(date),airlineCode);
    }

    @GetMapping(value="/getAll",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Flight> getFlight(){
        return flightService.getAll();
    }


    @GetMapping(value = "/updates",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Flight> LiveUpdate(){
        return flightService.getLiveUpdate();
    }
}
