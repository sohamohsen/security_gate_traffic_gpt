package com.research.service;

import com.research.model.GateLane;
import com.research.model.LaneStatus;
import com.research.repository.GateLaneRepository;

import java.util.List;

public class GateLaneService {

    private final GateLaneRepository gateLaneRepository;
    private final ValidationService validationService;

    public GateLaneService(GateLaneRepository gateLaneRepository,
                           ValidationService validationService) {
        this.gateLaneRepository = gateLaneRepository;
        this.validationService = validationService;
    }

    public void addLane(GateLane lane) {
        validationService.validateId(lane.getId());
        validationService.validatePositiveNumber(lane.getCapacityPerMinute(), "Lane Capacity");

        gateLaneRepository.add(lane);
    }

    public void openLane(int laneId) {
        GateLane lane = gateLaneRepository.getById(laneId);
        lane.setStatus(LaneStatus.OPEN);
        gateLaneRepository.update(lane);
    }

    public void closeLane(int laneId) {
        GateLane lane = gateLaneRepository.getById(laneId);
        lane.setStatus(LaneStatus.CLOSED);
        gateLaneRepository.update(lane);
    }

    public List<GateLane> getOpenLanes() {
        return gateLaneRepository.findOpenLanes();
    }
}
