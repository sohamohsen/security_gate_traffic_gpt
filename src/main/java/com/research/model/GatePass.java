package com.research.model;

import com.research.repository.Identifiable;

import java.time.LocalDateTime;

public class GatePass implements Identifiable {

    private int id;
    private Vehicle vehicle;
    private GateLane lane;
    private PassDirection direction;
    private PassStatus status;
    private LocalDateTime passTime;

    public GatePass(int id, Vehicle vehicle, GateLane lane,
                    PassDirection direction, PassStatus status, LocalDateTime passTime) {
        this.id = id;
        this.vehicle = vehicle;
        this.lane = lane;
        this.direction = direction;
        this.status = status;
        this.passTime = passTime;
    }

    public Vehicle getVehicle() { return vehicle; }
    public GateLane getLane() { return lane; }
    public PassDirection getDirection() { return direction; }
    public PassStatus getStatus() { return status; }

    public void setStatus(PassStatus status) {
        this.status = status;
    }
    @Override
    public int getId() {
        return id;
    }
}
