package com.example.flight.sink;

import com.example.flight.model.Flight;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class FlightSink {
    private final Sinks.Many<Flight> sink=Sinks.many().multicast().onBackpressureBuffer();

    public Sinks.Many<Flight> getSink(){
        return sink;
    }
}
