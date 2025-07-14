package com.example.flight.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeicingInfo {
    private boolean deicingRequired;
    private String deicingType;
}

