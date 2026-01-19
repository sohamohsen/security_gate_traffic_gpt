package com.research.model;

import com.research.repository.Identifiable;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitReservation implements Identifiable {

    private int id;
    private String visitorName;
    private String vehiclePlate;
    private LocalDate visitDate;
    private LocalTime visitTime;
    private int numberOfPassengers;

    public VisitReservation(int id, String visitorName, String vehiclePlate,
                            LocalDate visitDate, LocalTime visitTime, int numberOfPassengers) {
        this.id = id;
        this.visitorName = visitorName;
        this.vehiclePlate = vehiclePlate;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public int getId() {
        return id;
    }
    public String getVehiclePlate() { return vehiclePlate; }
    public LocalDate getVisitDate() { return visitDate; }
    public LocalTime getVisitTime() { return visitTime; }
}
