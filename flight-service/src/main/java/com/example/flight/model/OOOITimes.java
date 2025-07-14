package com.example.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OOOITimes {
    private LocalDateTime out;
    private LocalDateTime off;
    private LocalDateTime on;
    private LocalDateTime in;
}
