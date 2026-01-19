package com.research.repository;

import com.research.model.GateLane;
import com.research.model.LaneStatus;

import java.util.List;
import java.util.stream.Collectors;

public class GateLaneRepository extends BaseRepository<GateLane> {

    // ---------------- FIND OPEN LANES ----------------
    public List<GateLane> findOpenLanes() {
        return storage.values()
                .stream()
                .filter(lane -> lane.getStatus() == LaneStatus.OPEN)
                .collect(Collectors.toList());
    }

    // ---------------- FIND BY LANE NUMBER ----------------
    public GateLane findByLaneNumber(int laneNumber) {
        return storage.values()
                .stream()
                .filter(lane -> lane.getLaneNumber() == laneNumber)
                .findFirst()
                .orElse(null);
    }
}
