package com.example.flight.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrewInfo {
    private String pilot;
    private String coPilot;
    private List<String> cabinCrew;
}
