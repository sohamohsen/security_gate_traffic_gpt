package com.research.model;

import com.research.repository.Identifiable;

public class Vehicle implements Identifiable {

    private int id;
    private String plateNumber;
    private Resident owner;
    private VehicleType vehicleType;
    private boolean isAllowed;

    public Vehicle(int id, String plateNumber, Resident owner, VehicleType vehicleType, boolean isAllowed) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.owner = owner;
        this.vehicleType = vehicleType;
        this.isAllowed = isAllowed;
    }

    public String getPlateNumber() { return plateNumber; }
    public Resident getOwner() { return owner; }
    public VehicleType getVehicleType() { return vehicleType; }
    public boolean isAllowed() { return isAllowed; }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }
    @Override
    public int getId() {
        return id;
    }
}
