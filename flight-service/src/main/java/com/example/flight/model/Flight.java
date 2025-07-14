package com.example.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    private String id;
    private String fightNumber;
    private String airlineCode;
    private String aircraftType;
    private String registrationNumber;

    private LocalDate flightDate;
    private AirportInfo origin;
    private AirportInfo destination;

    private OOOITimes oooiTimes;
    private ScheduleTimes scheduleTimes;
    private Estimates estimates;
    private Actuals actuals;

    private GateInfo gateInfo;
    private TerminalInfo terminalInfo;

    private RunwayInfo runwayInfo;
    private WeatherInfo departureWeather;
    private WeatherInfo arrivalWeather;

    private FuelInfo fuelInfo;
    private CargoInfo cargoInfo;
    private WeightBalanceInfo weightBalanceInfo;

    private CrewInfo crewInfo;
    private List<Passenger> passengers;
    private FlightPlan flightPlan;
    private ATCInfo atcInfo;
    private DeicingInfo deicing;
    private DelayInfo delayInfo;

    private List<ServiceLog> serviceLogList;
    private MaintenanceInfo maintenance;
    private CabinStatus cabinStatus;

    private WifiUsage wifiUsage;
    private InFlightEntertainment ifeUsage;

    private List<SecurityLog> securityChecks;
    private BoardingInfo boardingInfo;

    private List<GateChange> gateChanges;
    private List<BaggageInfo> baggageDetails;

    private List<Notification> notifications;
    private AuditTrail auditTrail;

    private EmergencyInfo emergencyInfo;
    private DiversionInfo diversionInfo;
    private CustomsDeclaration customsInfo;

    private List<TimestampLog> flightLogs;
    private FlightStatus status;
    private String remarks;
}
