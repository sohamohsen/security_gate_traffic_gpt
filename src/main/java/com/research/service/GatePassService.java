package com.research.service;

import com.research.exception.BusinessRuleViolationException;
import com.research.model.*;
import com.research.repository.GateLaneRepository;
import com.research.repository.GatePassRepository;

import java.time.LocalDateTime;

public class GatePassService {

    private final GatePassRepository gatePassRepository;
    private final GateLaneRepository gateLaneRepository;

    public GatePassService(GatePassRepository gatePassRepository,
                           GateLaneRepository gateLaneRepository) {
        this.gatePassRepository = gatePassRepository;
        this.gateLaneRepository = gateLaneRepository;
    }

    public void requestPass(int passId, Vehicle vehicle, int laneId, PassDirection direction) {
        GateLane lane = gateLaneRepository.getById(laneId);

        if (lane.getStatus() != LaneStatus.OPEN) {
            throw new BusinessRuleViolationException("Lane is not open.");
        }

        if (!vehicle.isAllowed()) {
            throw new BusinessRuleViolationException("Vehicle is not allowed.");
        }

        GatePass pass = new GatePass(
                passId,
                vehicle,
                lane,
                direction,
                PassStatus.PENDING,
                LocalDateTime.now()
        );

        gatePassRepository.add(pass);
    }

    public void approvePass(int passId) {
        GatePass pass = gatePassRepository.getById(passId);
        pass.setStatus(PassStatus.APPROVED);
        gatePassRepository.update(pass);
    }

    public void completePass(int passId) {
        GatePass pass = gatePassRepository.getById(passId);
        pass.setStatus(PassStatus.COMPLETED);
        gatePassRepository.update(pass);
    }
}
