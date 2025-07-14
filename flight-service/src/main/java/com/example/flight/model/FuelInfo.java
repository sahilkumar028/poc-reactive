package com.example.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelInfo {
    private double estimatedFuel;
    private double actualFuel;
    private String fuelType;
}
