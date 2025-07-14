package com.example.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceInfo {
    private boolean maintenanceRequired;
    private String maintenanceNotes;
}
