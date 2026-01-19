package com.research.model;

import com.research.repository.Identifiable;

public class GateLane implements Identifiable {

    private int id;
    private int laneNumber;
    private int capacityPerMinute;
    private LaneStatus status;

    public GateLane(int id, int laneNumber, int capacityPerMinute, LaneStatus status) {
        this.id = id;
        this.laneNumber = laneNumber;
        this.capacityPerMinute = capacityPerMinute;
        this.status = status;
    }

    public int getLaneNumber() { return laneNumber; }
    public int getCapacityPerMinute() { return capacityPerMinute; }
    public LaneStatus getStatus() { return status; }

    public void setStatus(LaneStatus status) {
        this.status = status;
    }
    @Override
    public int getId() {
        return id;
    }
}
