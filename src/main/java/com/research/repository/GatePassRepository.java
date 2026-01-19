package com.research.repository;


import com.research.model.GatePass;
import com.research.model.PassDirection;
import com.research.model.PassStatus;

import java.util.List;
import java.util.stream.Collectors;

public class GatePassRepository extends BaseRepository<GatePass> {

    // ---------------- FIND BY STATUS ----------------
    public List<GatePass> findByStatus(PassStatus status) {
        return storage.values()
                .stream()
                .filter(pass -> pass.getStatus() == status)
                .collect(Collectors.toList());
    }

    // ---------------- FIND BY DIRECTION ----------------
    public List<GatePass> findByDirection(PassDirection direction) {
        return storage.values()
                .stream()
                .filter(pass -> pass.getDirection() == direction)
                .collect(Collectors.toList());
    }

    // ---------------- FIND BY LANE ID ----------------
    public List<GatePass> findByLaneId(int laneId) {
        return storage.values()
                .stream()
                .filter(pass ->
                        pass.getLane() != null &&
                                pass.getLane().getId() == laneId)
                .collect(Collectors.toList());
    }
}
