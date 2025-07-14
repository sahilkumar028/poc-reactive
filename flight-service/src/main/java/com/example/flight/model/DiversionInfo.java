package com.example.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiversionInfo {
    private boolean diverted;
    private String diversionAirport;
    private String reason;
}
