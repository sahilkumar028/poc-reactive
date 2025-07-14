package com.example.flight.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InFlightEntertainment {
    private boolean enabled;
    private List<String> moviesAvailable;
}