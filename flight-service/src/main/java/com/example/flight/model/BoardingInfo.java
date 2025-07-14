package com.example.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardingInfo {
    private LocalDateTime boardingStart;
    private LocalDateTime boardingEnd;
    private int totalBoarded;
}